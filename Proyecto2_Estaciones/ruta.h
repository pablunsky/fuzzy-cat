#ifndef RUTA_H
#define RUTA_H
#include <QString>


class Ruta
{
public:
    Ruta(int codigo,QString nombre,QString color);
    int codigo;
    QString nombre;
    QString color;
    //Grafo *recorrido;
};

#endif // RUTA_H
