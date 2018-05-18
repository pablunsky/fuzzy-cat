/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

/**
 *
 * @author pablunsky
 */
public class Reembolso {
    
    double saldo_debitado;
    double valor_original;
    int codigo;
    String fecha_devolucion;
    String codigo_devolucion;

    public Reembolso(double saldo_debitado, double valor_original, int codigo, String fecha_devolucion, String codigo_devolucion) {
        this.saldo_debitado = saldo_debitado;
        this.valor_original = valor_original;
        this.codigo = codigo;
        this.fecha_devolucion = fecha_devolucion;
        this.codigo_devolucion = codigo_devolucion;
    }

    public Reembolso(){}
    
    @Override
    public String toString(){
        return "{\"saldo_debitado\":\""+saldo_debitado+"\",\"valor_original\":\""+valor_original+"\",\"codigo\":\""+codigo+"\",\"fecha_devolucion\":\""+fecha_devolucion+"\",\"codigo_devolucion\":\""+codigo_devolucion+"\"}";
    }   
    
    public double getSaldo_debitado() {
        return saldo_debitado;
    }

    public void setSaldo_debitado(double saldo_debitado) {
        this.saldo_debitado = saldo_debitado;
    }

    public double getValor_original() {
        return valor_original;
    }

    public void setValor_original(double valor_original) {
        this.valor_original = valor_original;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getCodigo_devolucion() {
        return codigo_devolucion;
    }

    public void setCodigo_devolucion(String codigo_devolucion) {
        this.codigo_devolucion = codigo_devolucion;
    }
    
    
    
}
