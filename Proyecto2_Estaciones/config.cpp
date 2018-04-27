#include "config.h"
#include "ui_config.h"
#include "ventanaestacion.h"
#include <QDesktopWidget>
#include <QStyle>
#include "jsonreader.h"

Config::Config(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Config)
{
    ui->setupUi(this);
    this->setGeometry(QStyle::alignedRect(Qt::LeftToRight,Qt::AlignCenter,this->size(),qApp->desktop()->availableGeometry()));

    this->rutas = nullptr;
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
        VentanaEstacion *ventana = new VentanaEstacion(codigo,rutas);
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
    if(codEstacion == "GTM01")return true;
    else return false;
}

ListaRutas* Config::getRutas(QString codigo)
{
    //buscarRutas disponibles para el codigo de estacion
    //leer el json obtenido mediante el servidor
    //JsonReader::obtenerRutas(temp,jsonRutas)
    ListaRutas* temp = new ListaRutas();
    temp->insertarRuta(new Ruta("204","USAC","azul"));
    temp->insertarRuta(new Ruta("37","TERMINAL","rojo"));
    temp->insertarRuta(new Ruta("203","USAC","azul"));
    temp->insertarRuta(new Ruta("36","TERMINAL","rojo"));
    temp->insertarRuta(new Ruta("1","TERMINAL","rojo"));
    return temp;
}
