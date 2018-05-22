/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author ciberveliz
 */
public class ListaEstaciones 
{
    
    private final String pathJson = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/java/edd/urban/servidor/estaciones.json";
    
    private String textDot = "";
    
    public NodoEstacion primero;
    public NodoEstacion ultimo;
    int size;
    private static ListaEstaciones listaST;
    public synchronized static ListaEstaciones getListaInicial()
    {
        if(listaST == null){
            listaST = new ListaEstaciones();
            listaST.load();
            listaST.generarGrafico();
        }

        return listaST;
    }
    
    public ListaEstaciones()
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
    
    public void agregarEstacion(NodoEstacion estacion)
    {
        if(this.isEmpty())
        {
            this.primero = estacion;
            this.ultimo = estacion;
        }
        else
        {
            int res = estacion.getCodEstacion().compareTo(this.primero.getCodEstacion());
        
            if(res < 0)
                agregarAlInicio(estacion);
            else 
            {
                res = this.ultimo.getCodEstacion().compareTo(estacion.getCodEstacion());
                if(res < 0 )
                    agregarAlFinal(estacion);
                else
                    insercionB(this.primero.sig,this.primero,estacion);
            }
        }
        save();
        generarGrafico();
        this.size++;
    }
    
    public boolean estacionRepetida(String codE)
    {
        NodoEstacion nodoT = this.primero;
        while(nodoT != null && !nodoT.getCodEstacion().equals(codE))
        {
            nodoT = nodoT.sig;
        }
        return nodoT != null;
    }
    
    public NodoEstacion getEstacion (String codE)
    {
        NodoEstacion nodoT = this.primero;
        while(nodoT != null && !nodoT.getCodEstacion().equals(codE))
        {
            nodoT = nodoT.sig;
        }
        if(nodoT == null)
            return null;
        return nodoT;
    }
    
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder("[\n");
        NodoEstacion temporal = primero;
        while(temporal != null) 
        {
            sb.append("\t{\n");
            sb.append("\t\t\"codEstacion\": \"").append(temporal.getCodEstacion()).append("\",\n");
            sb.append("\t\t\"nomEstacion\": \"").append(temporal.getNomEstacion()).append("\",\n");
            sb.append("\t\t\"latitud\": \"").append(temporal.getLatitud()).append("\",\n");
            sb.append("\t\t\"longitud\": \"").append(temporal.getLongitud()).append("\"\n");
            sb.append("\t}");
            temporal = temporal.sig;
            if(temporal!=null) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");
        return sb.toString();
    }
    
    private void save()
    {
        try
        {
            File file = new File(pathJson);
            String json = this.toString();
            
            json = Cifrador.cifrar(json);
            
            try (FileWriter fw = new FileWriter(file)) 
            {
                fw.write(json);
            }
        }
        catch(IOException e){ }
    }
    
    private void load()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String file = new String(Files.readAllBytes(Paths.get(pathJson)));
            String json = Cifrador.cifrar(file);
            
            NodoEstacion[] estaciones = mapper.readValue(json,NodoEstacion[].class);
            for(NodoEstacion nE : estaciones)
            {
                this.agregarEstacion(nE);
            }
        }
        catch(IOException e)
        { 
        }
    }
    //---------------------------GRAPHVIZ---------------------------------------
    private void generarGrafico()
    {
        String path = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/webapp/images";
        String fileIn = path+"/estaciones.txt";
        String fileOut = path + "/estaciones.png";
        File temp = new File(fileIn);
        try
        {
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(temp));
            bw.write("digraph lista{\nrankdir=LR;\nnode [shape=record,fontname=\"Raleway\"];\n");
            this.textDot = "";
            generarLista();
            bw.write(this.textDot + "\n}");
            bw.close();
            String[] cmd = {"dot","-Tpng",fileIn,"-o",fileOut};
            Runtime.getRuntime().exec(cmd);
        }
        catch(IOException e)
        { 
        }
    }
    
    private void generarLista()
    {
        NodoEstacion aux = this.primero;
        int contador = 1;
        while(aux != null)
        {
            this.textDot += "node" + contador + "[label = \"{<ant>|" + "Nombre: "+aux.getNomEstacion()+"\\nCodigo: "+aux.getCodEstacion()+"|<sig>}\"]; \n";
            aux = aux.sig;
            if(aux != null)
                contador++;
        }
        int t = 1;
        while(t <= contador-1)
        {
            this.textDot += "\"node"+t+"\"->"+"\"node"+(t+1)+"\";";
            t++;
        }
    }
    
    private void agregarAlInicio(NodoEstacion estacion)
    {
        estacion.sig = this.primero;
        this.primero = estacion;
    }
    
    private void agregarAlFinal(NodoEstacion estacion)
    {
        this.ultimo.sig = estacion;
        this.ultimo = estacion;
    }
    
    private void agregarDespuesDe(NodoEstacion pivote,NodoEstacion estacion)
    {
        estacion.sig = pivote.sig;
        pivote.sig = estacion;
    }
    private void insercionB(NodoEstacion inicio,NodoEstacion aux,NodoEstacion estacion)
    {
        int res = estacion.getCodEstacion().compareTo(inicio.getCodEstacion());
        
        if(res > 0)
            insercionB(inicio.sig,inicio,estacion);
        else
            agregarDespuesDe(aux,estacion);
    }
}
