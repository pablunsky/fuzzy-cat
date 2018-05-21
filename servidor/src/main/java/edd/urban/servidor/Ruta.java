/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;
//import org.hibernate.validator.constraints.Empty;

import java.io.Serializable;


/**
 *
 * @author ciberveliz
 */
public class Ruta implements Serializable
{
    
    private int codigoRuta;
    private String nombreRuta;
    private String colorRuta;
    private Double valorRuta;
    private Grafo grafo;

    public Ruta(int codigoRuta, String nombreRuta, String colorRuta, Double valorRuta) 
    {
        this.codigoRuta = codigoRuta;
        this.nombreRuta = nombreRuta;
        this.colorRuta = colorRuta;
        this.valorRuta = valorRuta;
        this.grafo = new Grafo();
    }
    
    public Ruta()
    {    
    }
    
    public int getCodigoRuta() 
    {
        return codigoRuta;
    }

    public void setCodigoRuta(int codigoRuta) 
    {
        this.codigoRuta = codigoRuta;
    }

    public String getNombreRuta() 
    {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) 
    {
        this.nombreRuta = nombreRuta;
    }

    public String getColorRuta() 
    {
        return colorRuta;
    }

    public void setColorRuta(String colorRuta) 
    {
        this.colorRuta = colorRuta;
    }

    public Double getValorRuta() 
    {
        return valorRuta;
    }

    public void setValorRuta(Double valorRuta) 
    {
        this.valorRuta = valorRuta;
    }
    
    public Grafo getGrafo() 
    {
        return grafo;
    }

    public void setGrafo(Grafo grafo) 
    {
        this.grafo = grafo;
    }
    
    public Double getPesoRuta()
    {
        Double peso = 0.0;
        NodoVertice nodoT = this.grafo.vertices.primero;
        while(nodoT != null)
        {
            NodoArista no2T = nodoT.vertice.aristas.primero;
            while(no2T != null)
            {
                peso += no2T.arista.getDistancia()*no2T.arista.getTrafico();
                no2T = no2T.sig;
            }
            nodoT = nodoT.sig;
        }
        return peso;
    }
}