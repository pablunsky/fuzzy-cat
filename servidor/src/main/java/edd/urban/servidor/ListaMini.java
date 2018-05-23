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
public class ListaMini 
{
    NodeMini primero;
    NodeMini ultimo;
    int size;
    
    public ListaMini()
    {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public void insertarAlInicio(String codigo)
    {
        NodeMini nuevo = new NodeMini(codigo);
        if(isEmpty())
        {
            this.primero = nuevo;
            this.ultimo = nuevo;
        }
        else
        {
            nuevo.sig = this.primero;
            this.primero = nuevo;
        }
        size++;
    }
    
    public void eliminarAlInicio()
    {
        if(!isEmpty())
        {
            if(this.primero.sig == null)
            {
                this.primero = null;
                this.ultimo = null;
            }
            else
                this.primero = this.primero.sig;
            
            this.size--;
        }
    }
    
    public boolean isEmpty()
    {
        return this.primero == null || this.ultimo == null || this.size == 0;
    }
}
