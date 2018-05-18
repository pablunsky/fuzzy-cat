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
public class ListaAbordajes {
    
    private NodoAbordaje primero;
    private NodoAbordaje ultimo;
    int size;
    private String pathofJSON = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/java/edd/urban/servidor/Abordajes.json";
    
    
    private static ListaAbordajes listaST;
    
    public synchronized static ListaAbordajes getListaIncial()
    {
        if(listaST == null){
            listaST = new ListaAbordajes();
            listaST.Load();
        }
        return listaST;
    }
    
    private void Load(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Abordaje[] abordajes = mapper.readValue(new File(pathofJSON),Abordaje[].class);
            
            for(Abordaje r : abordajes){
                agregarAbordaje(new NodoAbordaje().setValor(r.toString()));
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
    
    private ListaAbordajes()
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
    
    public void agregarAbordaje(NodoAbordaje abordaje)
    {
        if(this.isEmpty())
        {
            this.primero = abordaje;
            this.ultimo = abordaje;
        }
        else
        {
            this.ultimo.sig = abordaje;
            this.ultimo = abordaje;
        }
        Save();
        this.size++;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        NodoAbordaje aux = primero;
        while(aux!=null){
            sb.append(aux.abordaje);
            aux = aux.sig;
            if(aux!=null)
                sb.append(",\n");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
