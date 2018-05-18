/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pablunsky
 */
public class Abordaje {

    private String cod_estacion;
    private String cod_ruta;
    private String nombre_ruta;
    private String nombre_estacion;
    private int cod_ticket;
    private double valor_abordaje;
    
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha_abordaje;

    public String getCod_ruta() {
        return cod_ruta;
    }

    public Date getFecha_abordaje() {
        return fecha_abordaje;
    }

    public void setFecha_abordaje(Date fecha_abordaje) {
        this.fecha_abordaje = fecha_abordaje;
    }

    public void setCod_ruta(String cod_ruta) {
        this.cod_ruta = cod_ruta;
    }

    public String getNombre_ruta() {
        return nombre_ruta;
    }

    public void setNombre_ruta(String nombre_ruta) {
        this.nombre_ruta = nombre_ruta;
    }
    
    
    
    public Abordaje(){}

    public String getCod_estacion() {
        return cod_estacion;
    }

    public void setCod_estacion(String cod_estacion) {
        this.cod_estacion = cod_estacion;
    }

    public String getNombre_estacion() {
        return nombre_estacion;
    }

    public void setNombre_estacion(String nombre_estacion) {
        this.nombre_estacion = nombre_estacion;
    }

    public int getCod_ticket() {
        return cod_ticket;
    }

    public void setCod_ticket(int cod_ticket) {
        this.cod_ticket = cod_ticket;
    }

    public double getValor_abordaje() {
        return valor_abordaje;
    }

    public void setValor_abordaje(double valor_abordaje) {
        this.valor_abordaje = valor_abordaje;
    }
    
    @Override
    public String toString(){
        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("{\"cod_estacion\":\"");
        sb.append(cod_estacion);
        sb.append("\",\"cod_ruta\":\"");
        sb.append(cod_ruta);
        sb.append("\",\"nombre_estacion\":\"");
        sb.append(nombre_estacion);
        sb.append("\",\"nombre_ruta\":\"");
        sb.append(nombre_ruta);
        sb.append("\",\"cod_ticket\":\"");
        sb.append(cod_ticket);
        sb.append("\",\"valor_abordaje\":\"");
        sb.append(valor_abordaje);
        sb.append("\",\"fecha_abordaje\":\"");
        sb.append(f.format(fecha_abordaje));
        sb.append("\"}");
        
        return sb.toString();
    }
    
    
}
