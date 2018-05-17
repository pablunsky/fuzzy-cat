/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author ciberveliz
 */
public class NodoArista {
    Arista arista;
    NodoArista sig;
    
    public NodoArista(Arista arista)
    {
        this.arista = arista;
        this.sig = null;
    }
}
