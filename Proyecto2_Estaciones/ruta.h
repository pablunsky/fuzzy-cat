#ifndef RUTA_H
#define RUTA_H
#include <QString>


class Ruta
{
public:
    Ruta(QString codigo,QString nombre,QString color);
    QString codigo;
    QString nombre;
    QString color;
    //Grafo *recorrido;
};

#endif // RUTA_H
