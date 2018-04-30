/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import javax.swing.JOptionPane;

/**
 *
 * @author ciberveliz
 */
public class Grafo {
    ListaVertices vertices;
    
    public Grafo(){
        vertices = new ListaVertices();
    }
    
    public void agregarEstacion(NodoGrafo estacion)
    {
        if(this.vertices.estacionReptida(estacion.getCodigo()))
        {
            JOptionPane.showMessageDialog(null,"Esta estacion ya esta registrada.");
        }
        else
        {
            this.vertices.agregarVertice(estacion);
            JOptionPane.showMessageDialog(null,"Estacion registrada.");
        }
    }
    
    public void agregarRuta(String codEstacionInicial,String codEstacionFinal,Double distancia,Double trafico)
    {
        NodoGrafo origen = this.vertices.getNodoEstacion(codEstacionInicial);
        NodoGrafo destino = this.vertices.getNodoEstacion(codEstacionFinal);
        if(origen == null || destino == null)
        {
            JOptionPane.showMessageDialog(null,"Una o ambas rutas estaciones no estan registradas.");
            return;
        }
        if(origen.aristaRepetida(destino))
        {
            JOptionPane.showMessageDialog(null,"Esta ruta ya ha sido registrada.");
            return;
        }
        Arista ruta = new Arista(origen,destino,distancia,trafico);
        origen.aristas.agregarArista(ruta);
        JOptionPane.showMessageDialog(null,"Ruta registrada.");
    }
}
