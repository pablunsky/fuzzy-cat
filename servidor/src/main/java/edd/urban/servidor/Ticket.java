/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 *
 * @author pablunsky
 */
public class Ticket {
    
    private int codigo;
    private String codigo_devolucion;
    private double valor;
    private double saldo_actual;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_emision;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_devolucion;
    
    private static int count = 0;
    
    public Ticket(){
        int cod = (int)(Math.random()*1000);
        this.codigo_devolucion = rndChar()+""+rndChar()+""+rndChar()+""+cod;
        this.fecha_emision = new Date();
        //this.codigo = (int) (Math.random() * 5000);
        codigo = count;
        count ++;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigo_devolucion() {
        return codigo_devolucion;
    }

    public void setCodigo_devolucion(String codigo_devolucion) {
        this.codigo_devolucion = codigo_devolucion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }
    
    private static char rndChar () {
        int rnd = (int) (Math.random() * 52);
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }
    
    @Override
    public String toString(){
        String json = "{\"codigo\":\""+codigo+"\",\"codigo_devolucion\":\""+codigo_devolucion+"\",\"valor\":\""+valor+"\",\"saldo_actual\":\""+saldo_actual+"\",\"fecha_emision\":\""+fecha_emision+"\",\"fecha_devolucion\":\"\"}";
        return json;
    }
    
}
