/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

/**
 *
 * @author pablunsky
 */
public class NodoArbolB {
    private NodoArbolB[] enlaces;
    private Ticket[] contenidos;
    private int contenidosllenos;
    private int enlacesllenos;
    
    public NodoArbolB(int orden){
        contenidos = new Ticket[orden];
        enlaces = new NodoArbolB[orden];
        contenidosllenos = 0;
        enlacesllenos = 0;
    }
    
    private void increaseLLenos(){
        enlacesllenos++;
        contenidosllenos++;
    }
    
    public boolean AddValor(Ticket valor){
        for(Ticket o : contenidos){
            if(o==null){
                o = valor;
                return true;
            }    
        }
        return false;
    }
    
    public NodoArbolB[] getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(NodoArbolB[] enlaces) {
        this.enlaces = enlaces;
    }

    public Object[] getContenidos() {
        return contenidos;
    }

    public void setContenidos(Ticket[] contenidos) {
        this.contenidos = contenidos;
    }

    public int getContenidosllenos() {
        return contenidosllenos;
    }

    public void setContenidosllenos(int contenidosllenos) {
        this.contenidosllenos = contenidosllenos;
    }

    public int getEnlacesllenos() {
        return enlacesllenos;
    }

    public void setEnlacesllenos(int enlacesllenos) {
        this.enlacesllenos = enlacesllenos;
    }
    
}
