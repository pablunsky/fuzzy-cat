#include "clientetickets.h"
#include "ui_clientetickets.h"

ClienteTickets::ClienteTickets(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ClienteTickets)
{
    ui->setupUi(this);
    ui->label_3->setAlignment(Qt::AlignCenter);

}

ClienteTickets::~ClienteTickets()
{
    delete ui;
}
