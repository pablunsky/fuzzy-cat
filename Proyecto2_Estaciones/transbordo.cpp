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

QString Transbordo::json(){
    QString json = "{";
    json += "\"cod_estacion\":\""+this->codEstacion+"\"";
    json += ",\"cod_ruta\":\""+this->codRuta+"\"";
    json += ",\"nombre_estacion\":\""+this->nombre_estacion+"\"";
    json += ",\"nombre_ruta\":\""+this->nombre_ruta+"\"";
    json += ",\"cod_ticket\":"+QString::number(this->cod_ticket);
    json += ",\"valor_abordaje\":"+QString::number(this->valor_abordaje);
    json += "}";
    return json;
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
