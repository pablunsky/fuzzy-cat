#ifndef LISTARUTAS_H
#define LISTARUTAS_H
#include "nodo.h"

class ListaRutas
{
public:
    ListaRutas();
    Nodo *primera;
    Nodo *ultima;
    int length;

    void clear();
    bool isEmpty();
    void insertarRuta(Ruta *ruta);
    Ruta* getRuta(QString codigo);
};

#endif // LISTARUTAS_H
