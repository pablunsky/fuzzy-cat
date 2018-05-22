#include "clientetickets.h"
#include "ui_clientetickets.h"
#include <QDateTime>
#include <QtNetwork>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonValue>

ClienteTickets::ClienteTickets(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ClienteTickets)
{
    ui->setupUi(this);
    ui->label_3->setAlignment(Qt::AlignCenter);
    ui->label_20->setAlignment(Qt::AlignRight);


    QPixmap bkgnd("/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/ClienteTickets/Welcome.jpeg");
    bkgnd = bkgnd.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Background, bkgnd);
    this->setPalette(palette);

    valor = 0;
}

ClienteTickets::~ClienteTickets()
{
    delete ui;
}

void ClienteTickets::on_pushButton_clicked()
{

    QPixmap bkgnd("/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/ClienteTickets/Transaction.jpeg");
    bkgnd = bkgnd.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Background, bkgnd);
    this->setPalette(palette);
    ui->stackedWidget->setCurrentIndex(1);

    ui->label_19->setText(QDateTime::currentDateTime().toString());
}

void ClienteTickets::on_pushButton_2_clicked()
{
    QPixmap bkgnd("/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/ClienteTickets/Devolution.jpg");
    bkgnd = bkgnd.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Background, bkgnd);
    this->setPalette(palette);

    ui->stackedWidget->setCurrentIndex(2);
}


void ClienteTickets::on_pushButton_4_clicked()
{
    QPixmap bkgnd("/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/ClienteTickets/Welcome.jpeg");
    bkgnd = bkgnd.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Background, bkgnd);
    this->setPalette(palette);
    ui->label_17->setText("0000");
    ui->label_18->setText("AAAAAA");

    ui->stackedWidget->setCurrentIndex(0);
}

void ClienteTickets::on_pushButton_6_clicked()
{
    ui->label_20->setText("Q. 3.00");
    valor = 3;
}

void ClienteTickets::on_pushButton_8_clicked()
{
    ui->label_20->setText("Q. 50.00");
    valor = 50;
}

void ClienteTickets::on_pushButton_7_clicked()
{
    ui->label_20->setText("Q. 5.00");
    valor = 5;
}

void ClienteTickets::on_pushButton_9_clicked()
{
    ui->label_20->setText("Q. 100.00");
    valor = 100;
}

void ClienteTickets::on_pushButton_3_clicked()
{
    QString json = tr("{\"valor\":%1,\"codigo\":\"\"}").arg(valor);
    QEventLoop eventLoop;

    QNetworkAccessManager mgr;
    QObject::connect(&mgr, SIGNAL(finished(QNetworkReply*)), &eventLoop, SLOT(quit()));
    QNetworkRequest req( QUrl( BASE + QString("/ticket") ) );
    req.setRawHeader("Content-Type", "application/json");
    QNetworkReply *reply = mgr.post(req, json.toUtf8());
    eventLoop.exec();

    if (reply->error() == QNetworkReply::NoError) {
        QByteArray response = reply->readAll();
        QJsonDocument document = QJsonDocument::fromJson(response);
        QJsonObject obj = document.object();

        ui->label_17->setText(QString::number(obj["codigo"].toInt()));
        ui->label_18->setText(obj["codigo_devolucion"].toString());

        qDebug() << "Success\n" << response;
        delete reply;
    } else {
        qDebug() << "Failure" <<reply->errorString();
        delete reply;
    }
}

void ClienteTickets::on_pushButton_10_clicked()
{
    QPixmap bkgnd("/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/ClienteTickets/Welcome.jpeg");
    bkgnd = bkgnd.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Background, bkgnd);
    this->setPalette(palette);

    ui->label_24->setText("");
    ui->lineEdit->setText("");

    ui->stackedWidget->setCurrentIndex(0);
}

void ClienteTickets::on_pushButton_11_clicked()
{

    QString json = "{\"valor\":0,\"codigo\":\""+ui->lineEdit->text()+"\"}";

    QEventLoop eventLoop;

    QNetworkAccessManager mgr;
    QObject::connect(&mgr, SIGNAL(finished(QNetworkReply*)), &eventLoop, SLOT(quit()));
    QNetworkRequest req( QUrl( BASE+QString("/ticket") ) );
    req.setRawHeader("Content-Type", "application/json");
    QNetworkReply *reply = mgr.put(req, json.toUtf8());
    eventLoop.exec();

    if (reply->error() == QNetworkReply::NoError) {
        QByteArray response = reply->readAll();
        QJsonDocument document = QJsonDocument::fromJson(response);
        QJsonObject obj = document.object();

        ui->label_24->setText(obj["mensaje"].toString());

        qDebug() << "Success\n" << response;
        delete reply;
    } else {
        qDebug() << "Failure" <<reply->errorString();
        delete reply;
    }
}
