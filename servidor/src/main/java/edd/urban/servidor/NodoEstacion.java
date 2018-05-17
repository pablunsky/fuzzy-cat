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
public class NodoEstacion {
    private String codEstacion;
    private String nomEstacion;
    private Double latitud;
    private Double longitud;
    NodoEstacion sig;        
    
    public NodoEstacion(String codEstacion, String nomEstacion, Double latitud, Double longitud) {
        this.codEstacion = codEstacion;
        this.nomEstacion = nomEstacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.sig = null;
    }
    

    public String getCodEstacion() {
        return codEstacion;
    }

    public void setCodEstacion(String codEstacion) {
        this.codEstacion = codEstacion;
    }

    public String getNomEstacion() {
        return nomEstacion;
    }

    public void setNomEstacion(String nomEstacion) {
        this.nomEstacion = nomEstacion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
