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
public class NodoAbordaje {
    NodoAbordaje sig;
    public String abordaje;
    
    public NodoAbordaje(){
        this.sig = null;
    }
    
    public NodoAbordaje setValor(String valor){
        this.abordaje = valor;
        return this;
    }
}
