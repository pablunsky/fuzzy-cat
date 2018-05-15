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
public class ArbolB {
    
    private NodoArbolB raiz;
    private int orden;
    private int tamano;
    
    private int countstd;
    private int countprm;
    private int countfll;
    private int countnls;
    
    public String getCounts(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"countstd\":");
        sb.append(countstd);
        sb.append(",\"countprm\":");
        sb.append(countprm);
        sb.append(",\"countfll\":");
        sb.append(countfll);
        sb.append(",\"countnls\":");
        sb.append(countnls);
        sb.append("}");
        return sb.toString();
    }
    
    private static ArbolB singletonArbolTickets;
    
    public synchronized static ArbolB getArbolTickets(){
        if(singletonArbolTickets == null)
            singletonArbolTickets = new ArbolB();
        return singletonArbolTickets;
    }
    
    public ArbolB(){}
    
    public ArbolB(int orden, int tamano){
        this.orden = orden;
        this.tamano = tamano;
        this.raiz = null;
        countstd = 0;
        countprm = 0;
        countfll = 0;
        countnls = 0;
    }
    
    private void checkout(Ticket t){
        switch((int)t.getValor()){
            case 3:
                countstd++;
                break;
            case 5:
                countprm++;
                break;
            case 50:
                countfll++;
                break;
            default:
                countnls++;
                break;
        }
    }
    
    public void AddTicket(Ticket nuevo){
        checkout(nuevo);
        if(raiz == null){
            raiz = new NodoArbolB(orden);
            raiz.AddValor(nuevo);
            return;
        }
        NodoArbolB aux = raiz;
        if(aux.AddValor(nuevo))
            return;
        
    
    }
    
    public NodoArbolB getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbolB raiz) {
        this.raiz = raiz;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    
    
}
