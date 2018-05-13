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
public class ArbolB {
    private NodoArbolB raiz;
    private int orden;
    private int tamano;

    public ArbolB(int orden, int tamano){
        this.orden = orden;
        this.tamano = tamano;
        this.raiz = null;
    }
    
    public void AddNodo(Object valor){
        if(raiz == null){
            raiz = new NodoArbolB(orden);
            raiz.AddValor(valor);
            return;
        }
        NodoArbolB aux = raiz;
        if(aux.AddValor(valor))
            return;
        
        
    
    }
    
    public NodoArbolB getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbolB raiz) {
        this.raiz = raiz;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    
    
}
