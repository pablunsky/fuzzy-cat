/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            singletonArbolTickets = new ArbolB(3);
        return singletonArbolTickets;
    }
    
    public ArbolB(){}
    
    public ArbolB(int orden){
        this.orden = orden;
        this.raiz = new NodoArbolB(orden);
        this.tamano = 0;
        
        countstd = 0;
        countprm = 0;
        countfll = 0;
        countnls = 0;
    }
    
    public String recorrer(String nodopadre,NodoArbolB actual,int pos,String concat){
        String grafico="";
        
        while(pos<orden*2-1){
            if(actual.getContenidos()[pos]!=null){
                grafico+=actual.getContenidos()[pos].getCodigo();
            }
            grafico+=" | ";
            pos=pos+1;
        }
        
        concat+=actual.getContenidos().hashCode()+" [shape=record,label=\""+grafico+"}\"];\n";
        concat+=nodopadre+" -> "+actual.getContenidos().hashCode()+";\n";
        grafico="";
        
        pos=0;
        while(pos<orden*2){
            if(actual.getHijos()[pos]!=null){
                concat+=recorrer(actual.getContenidos().hashCode()+"",actual.getHijos()[pos],0,"");
            }
            pos=pos+1;
        }
        
        if(concat.contains("| }")){
            concat=concat.replace("| }","");
        }
        return concat;
    }
    
    private void Graficar(){
        String grafo="digraph arbol{\n";
        String aux="";
        grafo+= recorrer("Tickets",raiz,0,aux);
        grafo+="}";
        
        try{
            
            String path = new File("").getCanonicalPath();
            File file = new File(path+"/arbol.dot");
            FileWriter escribir=new FileWriter(file);
            escribir.write(grafo);
            escribir.close();
            
        }catch(IOException e){
        }
    }
    
    public void Add(Ticket t){
        if(raiz.Llena()){
            dividirRaiz();
            tamano++;
        }
        raiz.insertarTicket(t);
        checkout(t);
        Graficar();
    }
    
    private void dividirRaiz(){
        NodoArbolB hijoizq = new NodoArbolB(orden);
        NodoArbolB hijoder = new NodoArbolB(orden);
        
        hijoizq.eshoja = raiz.eshoja;
        hijoder.eshoja = raiz.eshoja;
        
        hijoizq.setLlenos(orden - 1);
        hijoder.setLlenos(orden -1);
        
        int centro = orden - 1;
        
        for(int i = 0; i< orden-1;i++){
            hijoizq.getHijos()[i]=raiz.getHijos()[i];
            hijoizq.getContenidos()[i]=raiz.getContenidos()[i];
        }
        hijoizq.getHijos()[centro] = raiz.getHijos()[centro];
        for(int i = centro+1; i<raiz.getLlenos();i++){
            hijoder.getHijos()[i-centro-1] = raiz.getHijos()[i];
            hijoder.getContenidos()[i-centro-1] = raiz.getContenidos()[i];
        }
        hijoder.getHijos()[centro] = raiz.getHijos()[raiz.getLlenos()];
        Ticket aux = raiz.getContenidos()[centro];
        raiz.setContenidos(new Ticket[2*orden-1]);
        raiz.getContenidos()[0] = aux;
        raiz.setLlenos(1);
        raiz.setHijos(new NodoArbolB[2*orden]);
        raiz.getHijos()[0]=hijoizq;
        raiz.getHijos()[1]=hijoder;
        raiz.eshoja = false;
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
