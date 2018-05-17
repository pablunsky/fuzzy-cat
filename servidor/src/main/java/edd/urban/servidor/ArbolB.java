/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private String pathofJSON = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/java/edd/urban/servidor/Tickets.json";
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
    
    private void Load(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Ticket[] tickets = mapper.readValue(new File(pathofJSON),Ticket[].class);
            for(Ticket t : tickets){
                Add(t);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void Save(){
        try{
            File f = new File(pathofJSON);
            String json = "[";
            
            json += "]";
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static ArbolB singletonArbolTickets;
    
    public synchronized static ArbolB getArbolTickets(){
        if(singletonArbolTickets == null){
            singletonArbolTickets = new ArbolB(3);
            singletonArbolTickets.Load();
        }
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
    
    public String graficar(String padre,NodoArbolB actual,String previo){
        
        String grafico="<<TABLE CELLSPACING=\"0\"><TR>";
        
        for(int i = 0; i<orden*2-1; i++){
            if(actual.getContenidos()[i]!=null){
                Ticket t = actual.getContenidos()[i];
                if(t.getValor() == 3)
                    grafico+= "<TD BGCOLOR =\"#f44336\"><FONT COLOR=\"white\">"+t.getCodigo()+"</FONT></TD>";
                else if(t.getValor()==5)
                    grafico+= "<TD BGCOLOR =\"#2196F3\"><FONT COLOR=\"white\">"+t.getCodigo()+"</FONT></TD>";
                else if(t.getValor() == 50)
                    grafico+= "<TD BGCOLOR =\"#009688\"><FONT COLOR=\"white\">"+t.getCodigo()+"</FONT></TD>";
                else
                    grafico+= "<TD BGCOLOR =\"#ff9800\"><FONT COLOR=\"white\">"+t.getCodigo()+"</FONT></TD>";
            }
            else
                grafico+="<TD></TD>";
        }
        
        previo += actual.getContenidos().hashCode()+" [shape=none, fontname=\"Raleway\",label="+grafico+"</TR></TABLE>>];\n";
        previo += padre+" -> "+actual.getContenidos().hashCode()+";\n";
        grafico="";
        
        for(int i = 0; i<orden*2; i++){
            if(actual.getHijos()[i]!=null){
                previo += graficar(actual.getContenidos().hashCode()+"",actual.getHijos()[i],"");
            }
        }
        
        return previo;
    }
    
    private void Graficar(){
        String grafica="digraph arbol{\nArbolB [shape=record, fontname=\"Raleway\", label=ArbolB]";
        grafica += graficar("ArbolB",raiz,"");
        grafica += "}";
        
        try{
            String path = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/webapp/W3.CSS Template_files/";
            File file = new File(path+"arbol.dot");
            FileWriter fw = new FileWriter(file);
            fw.write(grafica);
            fw.close();
            String[] cmd = {"dot","-Tpng",path+"arbol.dot","-o",path+"arbol.jpg"};
            Runtime.getRuntime().exec(cmd);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public Ticket Buscar(String codigo){
        NodoArbolB actual = raiz;
        
        for(int i = 0; i<orden*2-1; i++){
            if(actual.getContenidos()[i]!=null){
                Ticket t = actual.getContenidos()[i];
                if(t.getCodigo_devolucion().equals(codigo))
                    return t;
            }
        }
        
        for(int i = 0; i<orden*2; i++){
            if(actual.getHijos()[i]!=null){
                Ticket t = Buscar(codigo, actual.getHijos()[i]);
                if(t!=null)
                    return t;
            }
        }
        
        return null;
    }
    
    public Ticket Buscar(String codigo, NodoArbolB hijo){
        NodoArbolB actual = hijo;
        for(int i = 0; i<orden*2-1; i++){
            if(actual.getContenidos()[i]!=null){
                Ticket t = actual.getContenidos()[i];
                if(t.getCodigo_devolucion().equals(codigo))
                    return t;
            }
        }
        
        for(int i = 0; i<orden*2; i++){
            if(actual.getHijos()[i]!=null){
                Ticket t = Buscar(codigo, actual.getHijos()[i]);
                if(t!=null)
                    return t;
            }
        }
        
        return null;
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
