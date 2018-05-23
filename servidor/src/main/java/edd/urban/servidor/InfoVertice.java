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
public class InfoVertice 
{
    private String codActual;
    private boolean finalizado;
    private String codPredecesor;
    private double pesoAcumulado;
    
    public InfoVertice()
    {
        codActual = "";
        finalizado = false;
        codPredecesor = "";
        pesoAcumulado = 0;
    }

    public boolean isFinalizado() 
    {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) 
    {
        this.finalizado = finalizado;
    }

    public String getCodPredecesor() 
    {
        return codPredecesor;
    }

    public void setCodPredecesor(String codPredecesor) 
    {
        this.codPredecesor = codPredecesor;
    }

    public double getPesoAcumulado() 
    {
        return pesoAcumulado;
    }

    public void setPesoAcumulado(double pesoAcumulado) 
    {
        this.pesoAcumulado = pesoAcumulado;
    }

    public String getCodActual() 
    {
        return codActual;
    }

    public void setCodActual(String codActual) 
    {
        this.codActual = codActual;
    }
}
