#include "ticket.h"

Ticket::Ticket()
{
    codigo = 0;
    fecha_emision = QDateTime::currentDateTime();
    saldo_actual = 0;
    valor = 0;
    codigo_devolucion = "";
}

