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
        ui->groupBox->setEnabled(false);
        return;
    }
    QGridLayout *layout = new QGridLayout(ui->groupBox);
    Nodo *temp = rutas->primera;
    int i = 0;
    int j = 0;
    while(temp != nullptr)
    {
        QPushButton *btn = new QPushButton("Ruta "+QString::number(temp->ruta->codigo)+"\nQ. "+QString::number(temp->ruta->precio,'f',2));
        btn->setFixedSize(70,35);
        btn->setStyleSheet("background-color:"+temp->ruta->color);
        connect(btn,SIGNAL(clicked()),this,SLOT(verificarRuta()));
        layout->addWidget(btn,j,i);
        i++;
        if(i == 4)
        {
            i = 0;
            j++;
        }
        temp = temp->sig;
    }
    ui->groupBox->setLayout(layout);
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
