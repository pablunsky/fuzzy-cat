#include "clientetickets.h"
#include "ui_clientetickets.h"

ClienteTickets::ClienteTickets(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ClienteTickets)
{
    ui->setupUi(this);
}

ClienteTickets::~ClienteTickets()
{
    delete ui;
}
