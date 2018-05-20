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
public class NodoHash {
    Ruta ruta;
    NodoHash sig;
    
    public NodoHash(Ruta ruta)
    {
        this.ruta = ruta;
        this.sig = null;
    }
}
