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
public class NodeMini 
{
    String codEstacion;
    NodeMini sig;
    
    public NodeMini(String codigo)
    {
        this.codEstacion = codigo;
        this.sig = null;
    }
}
