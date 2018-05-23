#ifndef JSONREADER_H
#define JSONREADER_H
#include "listarutas.h"

class JsonReader
{
public:
    JsonReader();
    static void obtenerRutas(ListaRutas *rutas,QByteArray json);
};

#endif // JSONREADER_H
