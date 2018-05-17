/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author pablunsky
 */
public class ListaReembolsos {
    
    private NodoReembolso primero;
    private NodoReembolso ultimo;
    int size;
    private String pathofJSON = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/java/edd/urban/servidor/Reembolsos.json";
    
    
    private static ListaReembolsos listaST;
    
    public synchronized static ListaReembolsos getListaIncial()
    {
        if(listaST == null){
            listaST = new ListaReembolsos();
            listaST.Load();
        }
        return listaST;
    }
    
    private void Load(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Reembolso[] reembolsos = mapper.readValue(new File(pathofJSON),Reembolso[].class);
            
            for(Reembolso r : reembolsos){
                agregarReembolso(new NodoReembolso().setValor(r.toString()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void Save(){
        try{
            File f = new File(pathofJSON);
            
            FileWriter fw = new FileWriter(f);
            fw.write(toString());
            fw.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private ListaReembolsos()
    {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public void clear()
    {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    
    public Boolean isEmpty()
    {
        return this.primero == null || this.size == 0;
    }
    
    public void agregarReembolso(NodoReembolso reembolso)
    {
        if(this.isEmpty())
        {
            this.primero = reembolso;
            this.ultimo = reembolso;
        }
        else
        {
            this.ultimo.sig = reembolso;
            this.ultimo = reembolso;
        }
        Save();
        this.size++;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        NodoReembolso aux = primero;
        while(aux!=null){
            sb.append(aux.reembolso);
            aux = aux.sig;
            if(aux!=null)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
