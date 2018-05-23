#include "ventanaestacion.h"
#include "ui_ventanaestacion.h"
#include "config.h"
#include <QIcon>
#include <QStyle>
#include <QDesktopWidget>
#include <QDebug>
#include <QDir>
#include <QtNetwork>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonValue>
#include <QMessageBox>
#include <QListWidget>
#include <QListWidgetItem>

VentanaEstacion::VentanaEstacion(Transbordo *infoTransbordo, ListaRutas *rutas, QWidget *parent) :
    QWidget(parent),
    ui(new Ui::VentanaEstacion)
{
    this->infoTransbordo  = infoTransbordo;
    this->rutas = rutas;

    ui->setupUi(this);
    this->setWindowTitle("Estación " + infoTransbordo->getNombre_estacion());
    this->setGeometry(QStyle::alignedRect(Qt::LeftToRight,Qt::AlignCenter,this->size(),qApp->desktop()->availableGeometry()));

    QString path = QDir::currentPath();
    //****************botones**************************************
    QIcon iconCorrect = QIcon::fromTheme(path + "/images/correct.png");
    ui->label_correct->setPixmap(iconCorrect.pixmap(50,50));
    ui->label_correct->setEnabled(false);

    QIcon iconIncorrect = QIcon::fromTheme(path + "/images/incorrect.png");
    ui->label_incorrect->setPixmap(iconIncorrect.pixmap(50,50));
    ui->label_incorrect->setEnabled(false);

    //**************************************************************
    ui->label_error->setText("");

    ui->pushButton_back->setIcon(QIcon(path + "/images/back.png"));
    ui->pushButton_back->setIconSize(QSize(30,30));

    agregarRutas(rutas);
}

VentanaEstacion::~VentanaEstacion()
{
    delete ui;
}

void VentanaEstacion::agregarRutas(ListaRutas *rutas)
{
    if(rutas->isEmpty())
    {
        ui->label_error->setText("Rutas no disponibles");
        ui->listaWidget->setEnabled(false);
        return;
    }
    Nodo *temp = rutas->primera;
    while(temp != nullptr)
    {
        QString text = QString(temp->ruta->nombre+" - "+QString::number(temp->ruta->precio,'f',2)+" - "+QString::number(temp->ruta->codigo));
        QListWidgetItem* item = new QListWidgetItem(text);
        item->setBackgroundColor(temp->ruta->color);

        ui->listaWidget->addItem(item);
        temp = temp->sig;
    }
}


void VentanaEstacion::on_pushButton_back_clicked()
{
    Config *config = new Config();
    this->infoTransbordo->clean();
    config->show();
    this->close();
}

void VentanaEstacion::verificarRuta()
{

}

void VentanaEstacion::on_listaWidget_itemDoubleClicked(QListWidgetItem *item)
{
    ui->label_incorrect->setEnabled(false);
    QString codTicket = ui->lineEdit_ticket->text();
    if(codTicket.isEmpty()) return;

    infoTransbordo->setCod_ticket(codTicket.toInt()); //set del cod_ticket

    QStringList datos = item->text().split(" - ");
    QString valor = datos.at(1);

    this->infoTransbordo->setCodRuta(datos.at(2)); //set del cod_ruta
    this->infoTransbordo->setValor_abordaje(valor.toDouble()); //set del valor_abordaje
    this->infoTransbordo->setNombre_ruta(datos.at(0)); //set del nombre_ruta
    QString json = infoTransbordo->json();

    QEventLoop eventLoop;

    QNetworkAccessManager mgr;
    QObject::connect(&mgr, SIGNAL(finished(QNetworkReply*)), &eventLoop, SLOT(quit()));
    QNetworkRequest req( QUrl( BASE + QString("/ticket/abordajes") ) );
    req.setRawHeader("Content-Type", "application/json");
    QNetworkReply *reply = mgr.put(req, json.toUtf8());
    eventLoop.exec();

    if (reply->error() == QNetworkReply::NoError)
    {
        QByteArray response = reply->readAll();
        QJsonDocument doc = QJsonDocument::fromJson(response);
        QJsonObject obj = doc.object();

        QString msj = obj["mensaje"].toString();
        int tipo = obj["tipo"].toInt();
        switch(tipo){
            case 0:
                ui->label_incorrect->setEnabled(true);
                ui->label_correct->setEnabled(false);
            break;
            case 1:
                ui->label_incorrect->setEnabled(true);
                ui->label_correct->setEnabled(false);
            break;
            case 2:
                ui->label_incorrect->setEnabled(false);
                ui->label_correct->setEnabled(true);
            break;
            default:
                ui->label_incorrect->setEnabled(true);
                ui->label_correct->setEnabled(false);
            break;
        }
        ui->label_error->setText(msj);
    }
    else
    {
        delete reply;
    }


}
