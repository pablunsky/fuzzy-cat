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
public class NodoArista implements Serializable{
    public Arista arista;
    public NodoArista sig;
    
    public NodoArista(Arista arista)
    {
        this.arista = arista;
        this.sig = null;
    }
}
