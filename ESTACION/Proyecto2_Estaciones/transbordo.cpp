#include "transbordo.h"

Transbordo::Transbordo()
{
    this->codEstacion = "";
    this->codRuta = "";
    this->nombre_ruta = "";
    this->nombre_estacion = "";
    this->cod_ticket = 0;
    this->valor_abordaje = 0.0;
}

QString Transbordo::getCodEstacion() const
{
    return codEstacion;
}

void Transbordo::setCodEstacion(const QString &value)
{
    codEstacion = value;
}

QString Transbordo::getCodRuta() const
{
    return codRuta;
}

void Transbordo::setCodRuta(const QString &value)
{
    codRuta = value;
}

QString Transbordo::json(){
    QString json = "{\"cod_estacion\":\"";
    json += this->codEstacion + "\",\"cod_ruta\":\"";
    json += this->codRuta + "\",\"nombre_ruta\":\"";
    json += this->nombre_ruta + "\",\"nombre_estacion\":\"";
    json += this->nombre_estacion + "\",\"cod_ticket\":\"";
    json += QString::number(cod_ticket) + "\",\"valor_abordaje\":\"";
    json += QString::number(valor_abordaje) + "\"}";
    return json;
}


QString Transbordo::getNombre_ruta() const
{
    return nombre_ruta;
}

void Transbordo::setNombre_ruta(const QString &value)
{
    nombre_ruta = value;
}

QString Transbordo::getNombre_estacion() const
{
    return nombre_estacion;
}

void Transbordo::setNombre_estacion(const QString &value)
{
    nombre_estacion = value;
}

int Transbordo::getCod_ticket() const
{
    return cod_ticket;
}

void Transbordo::setCod_ticket(int value)
{
    cod_ticket = value;
}

double Transbordo::getValor_abordaje() const
{
    return valor_abordaje;
}

void Transbordo::setValor_abordaje(double value)
{
    valor_abordaje = value;
}

void Transbordo::clean()
{
    this->codEstacion = "";
    this->codRuta = "";
    this->nombre_ruta = "";
    this->nombre_estacion = "";
    this->cod_ticket = 0;
    this->valor_abordaje = 0.0;
}
