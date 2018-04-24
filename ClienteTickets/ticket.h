#ifndef TICKET_H
#define TICKET_H
#include <QString>
#include <QDate>

class Ticket
{
public:
    Ticket();

    int codigo;
    QString codigo_devolucion;
    QDate fecha_emision;
    QDate fecha_devolucion;
    double valor;
    double saldo_actual;

};

#endif // TICKET_H
