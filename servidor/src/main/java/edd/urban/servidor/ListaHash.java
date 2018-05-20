/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

/**
 *
 * @author ciberveliz
 */
public class ListaHash 
{
    NodoHash primero;
    NodoHash ultimo;
    int size;
    
    public ListaHash()
    {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public void clean()
    {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public boolean isEmpty()
    {
        return this.primero == null;
    }
    
    public void agregarRuta(Ruta ruta)
    {
        NodoHash nuevo = new NodoHash(ruta);
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
