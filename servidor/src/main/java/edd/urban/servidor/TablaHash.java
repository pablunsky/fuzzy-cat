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
 * @author ciberveliz
 */
public class TablaHash 
{
    int m;
    ListaHash[] tabla;
    
    private final String pathJson = "/home/ciberveliz/NetBeansProjects/servidor/src/main/java/edd/urban/servidor/rutas.json";
    
    private static TablaHash tablaST;
    public synchronized static TablaHash getTablaInicial()
    {
        if(tablaST == null){
            tablaST = new TablaHash(20);
            //tablaST.load();
            tablaST.insertar(new Ruta(12,"203-USAC","#236B7F",2.0));
            tablaST.insertar(new Ruta(22,"204-USAC","#D82B2B",2.0));
        }

        return tablaST;
    }
    
    private TablaHash(int k)
    {
        this.m = k;
        tabla = new ListaHash[k];
        for(int i = 0;i < k;i++)
        {
            tabla[i] = new ListaHash();
        }
    }
    
    public void insertar(Ruta ruta)
    {
        int indice = funcionHash(ruta.getCodigoRuta());
        this.tabla[indice].agregarRuta(ruta);
        this.save();
    }
    
    public Ruta getRuta(int codigo)
    {
        int indice = funcionHash(codigo);
        NodoHash nodoT = tabla[indice].primero;
        while(nodoT != null && codigo != nodoT.ruta.getCodigoRuta())
        {
            nodoT = nodoT.sig;
        }
        if(nodoT == null)
            return null;
        return nodoT.ruta;
    }
    
    private int funcionHash(int v)
    {
        return v%this.m;
    }
    
    @Override
    public String toString()
    {
        boolean add = false;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i<this.m; i++)
        {   
            ListaHash list = this.tabla[i];
            if(list.isEmpty())
            {
                if(i+1 < this.m && !tabla[i+1].isEmpty() && add)
                sb.append(",");
                continue;
            }
            
            NodoHash nodoT = list.primero;
            while(nodoT != null)
            {
                sb.append("{");
                sb.append("\"codigo\":"+nodoT.ruta.getCodigoRuta()+",");
                sb.append("\"nombre\":"+"\""+nodoT.ruta.getNombreRuta()+"\",");
                sb.append("\"color\":"+"\""+nodoT.ruta.getColorRuta()+"\",");
                sb.append("\"precio\":"+nodoT.ruta.getValorRuta()+",");
                sb.append("\"tiempo\":"+(nodoT.ruta.getPesoRuta()/100));
                sb.append("}");
                nodoT = nodoT.sig;
                if(nodoT != null) sb.append(",");
            }
            add = true;
            if(i+1 < this.m && !tabla[i+1].isEmpty())
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String getJsonRutasDisponibles(String codigoEstacion)
    {
        boolean empty = true;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i<this.m; i++)
        {   
            ListaHash list = this.tabla[i];
            if(list.isEmpty())
                continue;
            
            NodoHash nodoT = list.primero;
            while(nodoT != null)
            {
                if(nodoT.ruta.getGrafo().vertices.getNodoEstacion(codigoEstacion) == null)
                {
                    nodoT = nodoT.sig;
                    continue;                    
                }
                if(!empty)
                    sb.append(",");
                
                sb.append("{");
                sb.append("\"codigo\":"+nodoT.ruta.getCodigoRuta()+",");
                sb.append("\"nombre\":"+"\""+nodoT.ruta.getNombreRuta()+"\",");
                sb.append("\"color\":"+"\""+nodoT.ruta.getColorRuta()+"\",");
                sb.append("\"precio\":"+nodoT.ruta.getValorRuta());
                sb.append("}");
                empty = false;
                nodoT = nodoT.sig;
            }   
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String toJson()
    {
        boolean add = false;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i<this.m; i++)
        {   
            ListaHash list = this.tabla[i];
            if(list.isEmpty())
            {
                if(i+1 < this.m && !tabla[i+1].isEmpty() && add)
                sb.append(",");
                continue;
            }
            
            NodoHash nodoT = list.primero;
            while(nodoT != null)
            {
                sb.append("{");
                sb.append("\"codigo\":"+nodoT.ruta.getCodigoRuta()+",");
                sb.append("\"nombre\":"+"\""+nodoT.ruta.getNombreRuta()+"\",");
                sb.append("\"color\":"+"\""+nodoT.ruta.getColorRuta()+"\",");
                sb.append("\"precio\":"+nodoT.ruta.getValorRuta()+",");
                sb.append("\"grafo\":"+nodoT.ruta.getGrafo()+",");
                sb.append("}");
                nodoT = nodoT.sig;
                if(nodoT != null) sb.append(",");
            }
            add = true;
            if(i+1 < this.m && !tabla[i+1].isEmpty())
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    
    private void save()
    {
        try{
            File file = new File(pathJson);
            String json = this.toJson();
            
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
            Ruta[] rutasT = mapper.readValue(new File(pathJson),Ruta[].class);
            for(Ruta rT : rutasT){
                this.insertar(rT);
            }
        }
        catch(IOException e){ }
    }
}
