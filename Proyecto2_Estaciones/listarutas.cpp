#include "listarutas.h"

ListaRutas::ListaRutas()
{
    this->primera = nullptr;
    this->ultima = nullptr;
    this->length = 0;
}

void ListaRutas::clear()
{
    this->primera = nullptr;
    this->ultima = nullptr;
    this->length = 0;
}

bool ListaRutas::isEmpty()
{
    return this->primera == nullptr || this->ultima == nullptr || this->length == 0;
}

void ListaRutas::insertarRuta(Ruta *ruta)
{
    Nodo *nuevo = new Nodo(ruta);
    if(this->isEmpty())
    {
        this->primera = nuevo;
        this->ultima = nuevo;
    }
    else
    {
        this->ultima->sig = nuevo;
        this->ultima = nuevo;
    }
    length++;
}

Ruta* ListaRutas::getRuta(QString codigo)
{
    Nodo *temp = this->primera;
    while(temp != nullptr && temp->ruta->codigo != codigo){
        temp = temp->sig;
    }
    if(temp == nullptr) return nullptr;
    return temp->ruta;
}
