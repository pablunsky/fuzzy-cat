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
public class Arista {
    private NodoGrafo origen;
    private NodoGrafo destino;
    private Double distancia;
    private Double trafico;

    public Arista(NodoGrafo origen, NodoGrafo destino, Double distancia, Double trafico) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.trafico = trafico;
    }
    

    public NodoGrafo getOrigen() {
        return origen;
    }

    public void setOrigen(NodoGrafo origen) {
        this.origen = origen;
    }

    public NodoGrafo getDestino() {
        return destino;
    }

    public void setDestino(NodoGrafo destino) {
        this.destino = destino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Double getTrafico() {
        return trafico;
    }

    public void setTrafico(Double trafico) {
        this.trafico = trafico;
    }
}
