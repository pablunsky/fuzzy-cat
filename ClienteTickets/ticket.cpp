#include "ticket.h"

Ticket::Ticket()
{
    codigo = 0;
    fecha_emision = QDateTime::currentDateTime();
    fecha_devolucion = NULL;
    saldo_actual = 0;
    valor = 0;
    codigo_devolucion = "";
}

