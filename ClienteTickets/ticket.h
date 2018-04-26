#ifndef TICKET_H
#define TICKET_H
#include <QString>
#include <QDateTime>

class Ticket
{
public:
    Ticket();

    int codigo;
    QString codigo_devolucion;
    QDateTime fecha_emision;
    QDateTime fecha_devolucion;
    double valor;
    double saldo_actual;

};

#endif // TICKET_H
