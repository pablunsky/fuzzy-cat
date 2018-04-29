/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author pablunsky
 */
public class NodoArbolB {
    private NodoArbolB[] enlaces;
    private Object[] contenido;
    private int ticketsllenos;
    private int enlacesllenos;

    public NodoArbolB[] getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(NodoArbolB[] enlaces) {
        this.enlaces = enlaces;
    }

    public Object[] getContenido() {
        return contenido;
    }

    public void setContenido(Object[] contenido) {
        this.contenido = contenido;
    }

    public int getTicketsllenos() {
        return ticketsllenos;
    }

    public void setTicketsllenos(int ticketsllenos) {
        this.ticketsllenos = ticketsllenos;
    }

    public int getEnlacesllenos() {
        return enlacesllenos;
    }

    public void setEnlacesllenos(int enlacesllenos) {
        this.enlacesllenos = enlacesllenos;
    }
    
}
