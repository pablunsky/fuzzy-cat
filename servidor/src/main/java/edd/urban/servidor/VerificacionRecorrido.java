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
public class VerificacionRecorrido {
    private int codRuta;
    private String codOrigen;
    private String codDestino;
    private Double distancia;
    private Double trafico;

    public VerificacionRecorrido(int codRuta, String codOrigen, String codDestino, Double distancia, Double trafico) {
        this.codRuta = codRuta;
        this.codOrigen = codOrigen;
        this.codDestino = codDestino;
        this.distancia = distancia;
        this.trafico = trafico;
    }

    public VerificacionRecorrido(){}
    
    public int getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(int codRuta) {
        this.codRuta = codRuta;
    }

    public String getCodOrigen() {
        return codOrigen;
    }

    public void setCodOrigen(String codOrigen) {
        this.codOrigen = codOrigen;
    }

    public String getCodDestino() {
        return codDestino;
    }

    public void setCodDestino(String codDestino) {
        this.codDestino = codDestino;
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
