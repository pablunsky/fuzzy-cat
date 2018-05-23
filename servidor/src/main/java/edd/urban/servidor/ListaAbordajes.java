/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author pablunsky
 */
public class ListaAbordajes {
    
    private NodoAbordaje primero;
    private NodoAbordaje ultimo;
    int size;
    private String pathofJSON = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/java/edd/urban/servidor/Abordajes.json";
    private String pathofCSV = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/webapp/images/Abordajes.csv";
    
    
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
            String file = new String(Files.readAllBytes(Paths.get(pathofJSON)));
            String json = Cifrador.cifrar(file);
            
            Abordaje[] abordajes = mapper.readValue(json,Abordaje[].class);
            
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
            
            String json = Cifrador.cifrar(toString());
            fw.write(json);
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
    
    public String genCSV(){
        
        ObjectMapper mapper = new ObjectMapper();
        String json = toString();
        try{
            Abordaje[] abordajes = mapper.readValue(json,Abordaje[].class);
            StringBuilder sb = new StringBuilder();
            sb.append("Reporte de abordajes\n");
            sb.append("Codigo de estacion,Nombre de estacion,Codigo de ruta,Nombre de ruta,Codigo de ticket,Valor de abordaje,Fecha\n");
            
            double total = 0;
            
            for(Abordaje a : abordajes){
                sb.append(a.getCod_estacion()).append(","+a.getNombre_estacion()).append(","+a.getCod_ruta()).append(","+a.getNombre_ruta()).append(","+a.getCod_ticket()).append(","+a.getValor_abordaje()).append(","+a.getFecha_abordaje()).append("\n");
                total+=a.getValor_abordaje();
            }
            
            sb.append("Total: ").append(total).append("\n");
            
            File f = new File(pathofCSV);
            
            FileWriter fw = new FileWriter(f);
            fw.write(sb.toString());
            fw.close();
            
            return "{\"mensaje\":\"CORRECTO\"}";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "{\"mensaje\":\"ERROR\"}";
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
