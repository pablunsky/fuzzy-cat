/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author ciberveliz
 */
public class TablaHash implements Serializable
{
    int m;
    ListaHash[] tabla;
    
    private static final String pathTxt = "/home/ciberveliz/NetBeansProjects/servidor/src/main/java/edd/urban/servidor/rutas.dat";
    
    private static TablaHash tablaST;
    private String textDot;
    
    public synchronized static TablaHash getTablaInicial()
    {
        if(tablaST == null)
        {
          tablaST = new TablaHash(20);
          tablaST.load();
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
                sb.append("{");
                sb.append("\"codigo\":").append(nodoT.ruta.getCodigoRuta()).append(",");
                sb.append("\"nombre\":\"").append(nodoT.ruta.getNombreRuta()).append("\",");
                sb.append("\"color\":\"").append(nodoT.ruta.getColorRuta()).append("\",");
                sb.append("\"precio\":").append(nodoT.ruta.getValorRuta()).append(",");
                sb.append("\"tiempo\":").append(nodoT.ruta.getPesoRuta()/100);
                sb.append("}");
                nodoT = nodoT.sig;
                if(nodoT != null) sb.append(",");
            }
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
                sb.append("\"codigo\":").append(nodoT.ruta.getCodigoRuta()).append(",");
                sb.append("\"nombre\":\"").append(nodoT.ruta.getNombreRuta()).append("\",");
                sb.append("\"color\":\"").append(nodoT.ruta.getColorRuta()).append("\",");
                sb.append("\"precio\":").append(nodoT.ruta.getValorRuta());
                sb.append("}");
                empty = false;
                nodoT = nodoT.sig;
            }   
        }
        sb.append("]");
        return sb.toString();
    }
    
    public void save()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathTxt))) 
        {
            oos.writeObject(this);
        } 
        catch (IOException ex) 
        {
            //Logger.getLogger(TablaHash.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void load()
    {
        TablaHash tablaT;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathTxt))) 
        {
            tablaT = (TablaHash)ois.readObject();
            for(int i = 0; i<tablaT.m; i++)
            {   
                ListaHash list = tablaT.tabla[i];
                if(list.isEmpty())
                    continue;

                NodoHash nodoT = list.primero;
                while(nodoT != null)
                {
                    insertar(nodoT.ruta);
                    nodoT = nodoT.sig;
                }
            }
        }
        catch (IOException | ClassNotFoundException ex) 
        {
            //Logger.getLogger(TablaHash.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void graficarTabla()
    {
        String path = "/home/ciberveliz/NetBeansProjects/servidor/src/main/webapp/images";
        String fileIn = path+"/tabla.txt";
        String fileOut = path + "/tabla.png";
        File temp = new File(fileIn);
        try
        {
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(temp));
            bw.write("digraph lista{\nrankdir=LR;\nnodesep=0.05; \nnode[shape=record,fontname=\"Raleway\"];\n");
            this.textDot = "";
            generarTabla();
            bw.write(this.textDot + "\n}");
            bw.close();
            String[] cmd = {"dot","-Tpng",fileIn,"-o",fileOut};
            Runtime.getRuntime().exec(cmd);
        }
        catch(IOException e)
        { 
        }
    }
    
    private void generarTabla()
    {
        String barra = "node[label=\"";
        for(int i = 0; i < this.m; i++)
        {
            barra += i+"|";
        }
        barra += "\", height=2.0];";
        
    }
}
