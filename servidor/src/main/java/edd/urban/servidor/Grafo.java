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
public class Grafo implements Serializable
{
    public ListaVertices vertices;
    
    public Grafo()
    {
        vertices = new ListaVertices();
    }
    
    public void agregarEstacion(NodoGrafo estacion)
    {
        if(this.vertices.estacionReptida(estacion.getCodigo()))
        {
            //No se agrega porque la estacion ya esta registrada
        }
        else
        {
            this.vertices.agregarVertice(estacion);
        }
    }
    
    public String agregarRecorrido(String codEstacionInicial,String codEstacionFinal,Double distancia,Double trafico)
    {
        NodoGrafo origen = this.vertices.getNodoEstacion(codEstacionInicial);
        NodoGrafo destino = this.vertices.getNodoEstacion(codEstacionFinal);
        if(origen == null || destino == null)
        {
            return "";
        }
        if(origen.aristaRepetida(destino))
        {
            return "\"Este recorrido ya fue definido\"";
        }
        
        Arista ruta = new Arista(origen, destino, distancia, trafico);
        origen.aristas.agregarArista(ruta);
        return "\"Recorrido registrado\"";
    }
    
    public void agregarRecorridoMapa(String codEstacionInicial,String codEstacionFinal,Double distancia,Double trafico)
    {
        NodoGrafo origen = this.vertices.getNodoEstacion(codEstacionInicial);
        NodoGrafo destino = this.vertices.getNodoEstacion(codEstacionFinal);
        if(origen == null || destino == null)
        {
            return;
        }
        if(origen.aristaRepetida(destino))
        {
            Arista aristaT = origen.getAristaRepetida(destino);
            double val = trafico * distancia;
            double valA = aristaT.getDistancia() * aristaT.getTrafico();
            if(val > valA)
                return;
            
            aristaT.setTrafico(trafico);
            aristaT.setDistancia(distancia);
            return;
        }
        
        Arista ruta = new Arista(origen, destino, distancia, trafico);
        origen.aristas.agregarArista(ruta);
    }
}
