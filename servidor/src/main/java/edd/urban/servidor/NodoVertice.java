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
public class NodoVertice implements Serializable{
    public NodoVertice sig;
    public NodoGrafo vertice;
    
    public NodoVertice(NodoGrafo vertice)
    {
        this.vertice = vertice;
        this.sig = null;
    }
}
