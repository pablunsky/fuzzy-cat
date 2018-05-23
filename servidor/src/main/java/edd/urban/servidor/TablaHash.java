/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedInputStream;
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
    public int m;
    ListaHash[] tabla;
    
    private static final String pathTxt = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/java/edd/urban/servidor/rutas.dat";
    
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
    
    public void graficarTabla()
    {
        String path = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/webapp/images";
        //String path = "/home/ciberveliz/Escritorio/[EDD]UltimaMod/fuzzy-cat/servidor/src/main/webapp/images";
        String fileIn = path+"/tabla.txt";
        String fileOut = path + "/tabla.png";
        File temp = new File(fileIn);
        try
        {
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(temp));
            bw.write("digraph G {\nrankdir=LR;\nnodesep=0.05; \nnode[shape=\"record\",fontname=\"Raleway\", width=\".1\",height=\"0.1\"];\n");
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
        String barra = "nodeP[label=\"";
        for(int i = 0; i < this.m; i++)
        {
            barra += "<f"+i+"> " + i;
            if(i+1 < this.m)
                barra += "|";
        }
        barra += "\", height=3.0];";
        
        barra += "\nnode [width = 1.5];";
        for(int i = 0; i < this.m; i++)
        {
            String temp;
            ListaHash listaT = this.tabla[i];
            if(listaT.isEmpty())
                continue;
            
            temp = "node"+i+"[label = \"{<n>";
            
            NodoHash nodeT = listaT.primero;
            while(nodeT != null)
            {
                temp += "Cod: "+nodeT.ruta.getCodigoRuta()+" -- Nom: "+nodeT.ruta.getNombreRuta();
                nodeT = nodeT.sig;
                if(nodeT != null)
                    temp += " | ";
            }
            temp += "}\"];\n";
           
            barra +="\n"+temp;
            barra += "nodeP:f"+i+"-> node"+i+":n;\n";
        }
        this.textDot = barra;
    }
    
    
    public String graficarRuta(Ruta ruta) 
    {        
        String path = "/home/pablunsky/Downloads/wildfly-12.0.0.Final/standalone/deployments/servidor-1.0-SNAPSHOT.war/CLIENTE/assets/img";
        String fileIn = path + "/Ruta-"+ruta.getCodigoRuta()+".txt";
        String fileOut = path + "/Ruta-"+ruta.getCodigoRuta()+".png";
        File temp = new File(fileIn);
        try
        {
            BufferedWriter bw;

            try {
                bw = new BufferedWriter(new FileWriter(temp));
                bw.write("digraph lista{ rankdir=LR; ratio=fill; size=\"3,2\"; dpi=100; concentrate=false; node [shape = cricle, style=\"filled\",fillcolor=\"gray\",fontsize=\"8\",margin=\"0\",fontname=\"Raleway\"];");
                this.textDot = "";
                if(!ruta.getGrafo().vertices.isEmpty())
                    generarGrafo(ruta.getGrafo().vertices.primero, ruta.getColorRuta());
                bw.write(this.textDot + "}");
                bw.close();
                String[] cmd = {"dot","-Tpng",fileIn,"-o",fileOut};
                Runtime.getRuntime().exec(cmd);
                
                String[] cmd2 = {"convert",fileOut,"-gravity","center","-background","white","-extent","300x200",fileOut};
                Runtime.getRuntime().exec(cmd2);
                
                return "{\"R\":\"CORRECTO\"}";
            } 
            catch (IOException ex) 
            {
                //Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(Exception e)
        {
            
        }
        return "{\"R\":\"ERROR\"}";
    }
    
    private void generarGrafo(NodoVertice vertice, String color)
    {
        this.textDot += "nodo" + vertice.vertice.hashCode() + "[label=\"" + vertice.vertice.getNombre()+"\", shape=\"circle\",pos=\""+(vertice.vertice.getLongitud()/10)+","+(vertice.vertice.getLatitud()/10)+"!\"]; \n";
        NodoArista nodeA = vertice.vertice.aristas.primero;
        while(nodeA != null)
        {
            textDot += "\"nodo" + vertice.vertice.hashCode() + "\"-> \"nodo" + nodeA.arista.getDestino().hashCode() + "\" [ label= \""+nodeA.arista.getDistancia()+" m\",style=\"vee\",color =\""+color+"\",fontsize=\"6\"] \n";
            
            nodeA = nodeA.sig;
        }
        if(vertice.sig != null)
            generarGrafo(vertice.sig, color);
    }
    
    public String getJsonRutas()
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
                String path = "assets/img/Ruta-";
                sb.append("{\n");
                sb.append("\"nombre\":\"").append(nodoT.ruta.getNombreRuta()).append("\",\n");
                sb.append("\"precio\":").append(nodoT.ruta.getValorRuta()).append(",\n");
                sb.append("\"codigo\":").append(nodoT.ruta.getCodigoRuta()).append(",\n");
                sb.append("\"img\":\"").append(path+nodoT.ruta.getCodigoRuta()).append(".png\"\n");
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
    
    
    public void graficarMapa() 
    {        
        String path = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/webapp/images";
        String fileIn = path + "/mapa.txt";
        String fileOut = path + "/mapa.png";
        File temp = new File(fileIn);
        try
        {
            BufferedWriter bw;

            try {
                bw = new BufferedWriter(new FileWriter(temp));
                bw.write("digraph G {\nconcentrate=false;\nsplines=\"spline\";\nsplines=\"true\";\nnode [shape = cricle, style=\"filled\",fillcolor=\"gray\",fontsize=\"8\",margin=\"0\",fontname=\"Raleway\"];");
                this.textDot = "";
                for(int i = 0; i<this.m; i++)
                {   
                    ListaHash list = this.tabla[i];
                    if(list.isEmpty())
                        continue;
            
                    NodoHash nodoT = list.primero;
                    while(nodoT != null)
                    {
                        Ruta rutaT = nodoT.ruta;
                        if(!rutaT.getGrafo().vertices.isEmpty())
                            generarMapa(rutaT.getGrafo().vertices.primero, rutaT.getColorRuta());
                        nodoT = nodoT.sig;
                    }   
                }
                bw.write(this.textDot + "}");
                bw.close();
                String[] cmd = {"neato","-Tpng",fileIn,"-o",fileOut};
                Runtime.getRuntime().exec(cmd);
                fileOut = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/webapp/CLIENTE/assets/img/mapa.png";
                String[] cmd2 = {"neato","-Tpng",fileIn,"-o",fileOut};
                Runtime.getRuntime().exec(cmd2);
            } 
            catch (IOException ex) 
            {
                //Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(Exception e)
        {
        }
    }
    
    private void generarMapa(NodoVertice vertice, String color)
    {
        this.textDot += "\nnodo" + vertice.vertice.getCodigo() + "[label=\"" + vertice.vertice.getNombre()+"\", shape=\"circle\",pos=\""+(vertice.vertice.getLongitud()/10)+","+(vertice.vertice.getLatitud()/10)+"!\"];";
        NodoArista nodeA = vertice.vertice.aristas.primero;
        while(nodeA != null)
        {
            textDot += "\n\"nodo" + vertice.vertice.getCodigo() + "\"-> \"nodo" + nodeA.arista.getDestino().getCodigo() + "\" [ label= \""+nodeA.arista.getDistancia()+" m\",color =\""+color+"\",fontsize=\"8\"] \n";
            
            nodeA = nodeA.sig;
        }
        if(vertice.sig != null)
            generarMapa(vertice.sig, color);
    }
    
    
    
    public void graficarRutaMinima(String codOrigen,String codDestino, Grafo g)
    {
        String path = "/home/pablunsky/Documents/TAREAS/ESTRUCTURAS DE DATOS/Proyecto2/servidor/src/main/webapp/CLIENTE/assets/img";
        
        String fileIn = path + "/mapa.txt";
        String fileOut = path + "/mapa.png";
        File temp = new File(fileIn);
        try
        {
            BufferedWriter bw;

            try {
                bw = new BufferedWriter(new FileWriter(temp));
                bw.write("digraph G {\nconcentrate=false;\nsplines=\"spline\";\nsplines=\"true\";\nnode [shape = cricle, style=\"filled\",fillcolor=\"gray\",fontsize=\"8\",margin=\"0\",fontname=\"Raleway\"];");
                this.textDot = "";
                for(int i = 0; i<this.m; i++)
                {   
                    ListaHash list = this.tabla[i];
                    if(list.isEmpty())
                        continue;
            
                    NodoHash nodoT = list.primero;
                    while(nodoT != null)
                    {
                        Ruta rutaT = nodoT.ruta;
                        if(!rutaT.getGrafo().vertices.isEmpty())
                            generarMapa(rutaT.getGrafo().vertices.primero, rutaT.getColorRuta());
                        nodoT = nodoT.sig;
                    }   
                }
                bw.write(this.textDot+"\n");
                this.textDot = "";
                

                if(!g.vertices.isEmpty())
                {
                    Dijkstra d = new Dijkstra(codOrigen,codDestino,g);
                    ListaMini lista = d.obtenerRutaMinima();
                    generarRutaMinima(lista);
                    bw.write(this.textDot);
                }
                bw.write("\n}");

                bw.close();
                String[] cmd = {"neato","-Tpng",fileIn,"-o",fileOut};
                Runtime.getRuntime().exec(cmd);
            } 
            catch (IOException ex) {
                //Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(Exception e)
        {
        }
    }
    
    private void generarRutaMinima(ListaMini lista)
    {
        while(!lista.isEmpty())
        {
            textDot += "\n\"nodo" + lista.primero.codEstacion + "\"-> \"nodo" + lista.primero.sig.codEstacion + "\" [color =\"#58D039\", penwidth=\"5\", style=\"dotted\"] \n";
            lista.eliminarAlInicio();
            if(lista.size == 1)
                lista = new ListaMini();
        }
    }
    
    
    public Grafo getGrafoGeneral()
    {
        Grafo g = new Grafo();
        for(int i = 0; i<this.m; i++)
        {   
            ListaHash list = this.tabla[i];
            if(list.isEmpty())
                continue;

            NodoHash nodoT = list.primero;
            while(nodoT != null)
            {
                Ruta rutaT = nodoT.ruta;
                if(!rutaT.getGrafo().vertices.isEmpty())
                {
                    NodoVertice nodeV = rutaT.getGrafo().vertices.primero;
                    while(nodeV != null)
                    {
                        String nombre = nodeV.vertice.getNombre();
                        String codigo = nodeV.vertice.getCodigo();
                        double latitud = nodeV.vertice.getLatitud();
                        double longitud = nodeV.vertice.getLongitud();
                        if(!g.vertices.estacionReptida(codigo))
                        {
                            NodoGrafo nodoG = new NodoGrafo(nombre,codigo,latitud,longitud);
                            g.agregarEstacion(nodoG);
                        }

                        
                        NodoArista nodeA = nodeV.vertice.aristas.primero;
                        
                        while(nodeA != null)
                        {
                            String codOrigen = nodeA.arista.getOrigen().getCodigo();
                            String codDestino = nodeA.arista.getDestino().getCodigo();
                            double trafico = nodeA.arista.getTrafico();
                            double distancia = nodeA.arista.getDistancia();
                            
                            g.agregarRecorridoMapa(codigo, codDestino, distancia, trafico);
                                
                            nodeA = nodeA.sig;
                        }
                        
                        nodeV = nodeV.sig;
                    }
                }
                nodoT = nodoT.sig;
            }   
        }
        return g;
    }
    
}
