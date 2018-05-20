#ifndef RUTA_H
#define RUTA_H
#include <QString>


class Ruta
{
public:
    Ruta(int codigo,QString nombre,QString color, double precio);
    int codigo;
    QString nombre;
    QString color;
    double precio;
    //Grafo *recorrido;
};

#endif // RUTA_H
