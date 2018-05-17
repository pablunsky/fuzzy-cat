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
public class ListaEstaciones {
    NodoEstacion primero;
    NodoEstacion ultimo;
    int size;
    private static ListaEstaciones listaST;
    public synchronized static ListaEstaciones getListaIncial()
    {
        if(listaST == null)
            listaST = new ListaEstaciones();
            listaST.agregarEstacion(new NodoEstacion("12","Terminal",10.2,100.0));
        return listaST;
    }
    
    private ListaEstaciones()
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
    
    public void agregarEstacion(NodoEstacion estacion)
    {
        if(this.isEmpty())
        {
            this.primero = estacion;
            this.ultimo = estacion;
        }
        else
        {
            this.ultimo.sig = estacion;
            this.ultimo = estacion;
        }
        this.size++;
    }
}
