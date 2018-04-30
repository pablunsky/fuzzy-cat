/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author ciberveliz
 */
public class ListaVertices {
    NodoVertice primero;
    NodoVertice ultimo;
    int size;
    public ListaVertices()
    {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public void clear()
    {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public Boolean isEmpty()
    {
        return this.primero == null || this.size == 0;
    }
    
    public void agregarVertice(NodoGrafo vertice)
    {
        NodoVertice nuevo = new NodoVertice(vertice);
        if(this.isEmpty())
        {
            this.primero = nuevo;
            this.ultimo = nuevo;
        }
        else
        {
            this.ultimo.sig = nuevo;
            this.ultimo = nuevo;
        }
        this.size++;
    }
    
    public Boolean estacionReptida(String codigo)
    {
        NodoVertice aux = this.primero;
        while(aux != null && !aux.vertice.getCodigo().equals(codigo))
        {
            aux = aux.sig;
        }
        return aux != null;
    }
    
    public NodoGrafo getNodoEstacion(String codigo)
    {
        NodoVertice aux = this.primero;
        while(aux != null && !aux.vertice.getCodigo().equals(codigo))
        {
            aux = aux.sig;
        }
        if(aux == null) return null;
        else return aux.vertice;
    }
}
