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
public class NodoReembolso {
    NodoReembolso sig;
    public String reembolso;
    
    public NodoReembolso(){
        this.sig = null;
    }
    
    public NodoReembolso setValor(String valor){
        this.reembolso = valor;
        return this;
    }
    
}
