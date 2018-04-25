#include "clientetickets.h"
#include "ui_clientetickets.h"
#include "ticket.h"
#include <QDateTime>

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
    Ticket* ticket = new Ticket();
    ticket->codigo = ui->label_17->text().toInt();
    ticket->codigo_devolucion = ui->label_18->text();
    ticket->fecha_emision = QDateTime::currentDateTime();

    ticket->valor = valor;
    ticket->saldo_actual = ticket->saldo_actual + ticket->valor;

    //ENVIAR TICKET AL SERVIDOR
}

void ClienteTickets::on_pushButton_10_clicked()
{
    QPixmap bkgnd("/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/ClienteTickets/Welcome.jpeg");
    bkgnd = bkgnd.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Background, bkgnd);
    this->setPalette(palette);

    ui->stackedWidget->setCurrentIndex(0);
}

void ClienteTickets::on_pushButton_11_clicked()
{
    //BORRAR TICKET
}
