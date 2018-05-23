#ifndef TRANSBORDO_H
#define TRANSBORDO_H

#include <QString>

class Transbordo
{
public:
    Transbordo();

    QString getCodEstacion() const;
    void setCodEstacion(const QString &value);

    QString getCodRuta() const;
    void setCodRuta(const QString &value);

    QString getNombre_ruta() const;
    void setNombre_ruta(const QString &value);

    QString getNombre_estacion() const;
    void setNombre_estacion(const QString &value);

    int getCod_ticket() const;
    void setCod_ticket(int value);

    double getValor_abordaje() const;
    void setValor_abordaje(double value);
    QString json();
    void clean();

private:
    QString codEstacion;
    QString codRuta;
    QString nombre_ruta;
    QString nombre_estacion;
    int cod_ticket;
    double valor_abordaje;
};

#endif // TRANSBORDO_H
