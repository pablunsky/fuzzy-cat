/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import java.io.Serializable;

/**
 *
 * @author ciberveliz
 */
public class ListaAristas implements Serializable{
    public NodoArista primero;
    public NodoArista ultimo;
    int size;
    
    public ListaAristas()
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
    
    public void agregarArista(Arista arista)
    {
        NodoArista nuevo = new NodoArista(arista);
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
}
