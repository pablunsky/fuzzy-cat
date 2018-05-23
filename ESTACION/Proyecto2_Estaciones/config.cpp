#include "config.h"
#include "ui_config.h"
#include "ventanaestacion.h"
#include <QDesktopWidget>
#include <QStyle>
#include "jsonreader.h"
#include <QtNetwork>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonValue>
#include <QMessageBox>

Config::Config(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Config)
{
    ui->setupUi(this);
    this->setGeometry(QStyle::alignedRect(Qt::LeftToRight,Qt::AlignCenter,this->size(),qApp->desktop()->availableGeometry()));

    this->rutas = nullptr;

    infoTransbordo = new Transbordo();
}

Config::~Config()
{
    delete ui;
}

void Config::on_pushButton_clicked()
{
    this->ui->label_msg->setText("");
    QString codigo = this->ui->lineEdit->text();
    if(codigo.isEmpty()) return;

    if(exist(codigo))
    {
        rutas = getRutas(codigo);

        VentanaEstacion *ventana = new VentanaEstacion(infoTransbordo,rutas);
        ventana->show();
        this->close();
    }
    else
    {
        this->ui->label_msg->setText("La estación "+codigo+" no existe.");
    }
}

bool Config::exist(QString codEstacion)
{
    bool res = false;
    QString json = "{\"codEstacion\":\""+codEstacion+"\",\"nomEstacion\":\"sin\",\"latitud\":0.0,\"longitud\":0.0}";

    QEventLoop eventLoop;

    QNetworkAccessManager mgr;
    QObject::connect(&mgr, SIGNAL(finished(QNetworkReply*)), &eventLoop, SLOT(quit()));
    QNetworkRequest req( QUrl( BASE + QString("/rutas/transbordoValido") ) );
    req.setRawHeader("Content-Type", "application/json");
    QNetworkReply *reply = mgr.post(req, json.toUtf8());
    eventLoop.exec();

    if (reply->error() == QNetworkReply::NoError)
    {
        QByteArray response = reply->readAll();
        QString temp = QString::fromUtf8(response);
        QStringList list = temp.split("$$");
        delete reply;
        if(list.at(0) == "true")
        {
            res = true;
            this->infoTransbordo->setCodEstacion(codEstacion); //set del codigo_estacion
            this->infoTransbordo->setNombre_estacion(list.at(1)); //set del codigo_estacion
        }
    }
    else
    {
        res = false;
        delete reply;
    }
    return res;
}

ListaRutas* Config::getRutas(QString codigoEstacion)
{
    ListaRutas* temp = new ListaRutas();
    QString json = "{\"codEstacion\":\""+codigoEstacion+"\",\"nomEstacion\":\"sin\",\"latitud\":0.0,\"longitud\":0.0}";

    QEventLoop eventLoop;

    QNetworkAccessManager mgr;
    QObject::connect(&mgr, SIGNAL(finished(QNetworkReply*)), &eventLoop, SLOT(quit()));
    QNetworkRequest req( QUrl( BASE + QString("/rutas/disponibles") ) );
    req.setRawHeader("Content-Type", "application/json");
    QNetworkReply *reply = mgr.post(req, json.toUtf8());
    eventLoop.exec();

    if (reply->error() == QNetworkReply::NoError)
    {
        QByteArray response = reply->readAll();
        JsonReader::obtenerRutas(temp,response);
    }
    else
    {
        delete reply;
    }

    return temp;
}
