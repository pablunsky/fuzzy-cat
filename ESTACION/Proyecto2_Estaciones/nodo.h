#ifndef NODO_H
#define NODO_H
#include "ruta.h"

class Nodo
{
public:
    Nodo(Ruta *ruta);
    Nodo *sig;
    Ruta *ruta;
};

#endif // NODO_H
