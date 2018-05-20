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

/**
 *
 * @author ciberveliz
 */
public class ListaEstaciones {
    
    private final String pathJson = "/home/ciberveliz/NetBeansProjects/servidor/src/main/java/edd/urban/servidor/estaciones.json";
    
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
            this.ultimo.sig = estacion;
            this.ultimo = estacion;
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
    public String toString() {
        StringBuilder sb = new StringBuilder("[\n");
        NodoEstacion temporal = primero;
        while(temporal != null) {
            sb.append("\t{\n");
            sb.append("\t\t\"codEstacion\": "+"\""+temporal.getCodEstacion()+"\",\n");
            sb.append("\t\t\"nomEstacion\": "+"\""+temporal.getNomEstacion()+"\",\n");
            sb.append("\t\t\"latitud\": "+"\""+temporal.getLatitud()+"\",\n");
            sb.append("\t\t\"longitud\": "+"\""+temporal.getLongitud()+"\"\n");
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
        try{
            File file = new File(pathJson);
            String json = this.toString();
            
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(json);
            }
        }
        catch(IOException e){ }
    }
    
    private void load()
    {
        ObjectMapper mapper = new ObjectMapper();
        try{
            NodoEstacion[] estaciones = mapper.readValue(new File(pathJson),NodoEstacion[].class);
            for(NodoEstacion nE : estaciones){
                this.agregarEstacion(nE);
            }
        }
        catch(IOException e){ }
    }
    //---------------------------GRAPHVIZ---------------------------------------
    private void generarGrafico()
    {
        String path = "/home/ciberveliz/NetBeansProjects/servidor/src/main/webapp/images";
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
        catch(IOException e){  }
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
}
