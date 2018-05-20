/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import edd.urban.servidor.Grafo;
import edd.urban.servidor.ListaEstaciones;
import edd.urban.servidor.NodoArista;
import edd.urban.servidor.NodoEstacion;
import edd.urban.servidor.NodoGrafo;
import edd.urban.servidor.NodoVertice;
import edd.urban.servidor.Ruta;
import edd.urban.servidor.TablaHash;
import edd.urban.servidor.VerificacionRecorrido;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ciberveliz
 */

@Path("rutas")

public class RutasResource {
    
    private TablaHash tablaHash = TablaHash.getTablaInicial();
    private String textDot = "";
    private ListaEstaciones estaciones = ListaEstaciones.getListaInicial();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRutas()
    {
        return Response.ok(this.tablaHash.toString()).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregarRuta(Ruta ruta)
    {
        if(this.tablaHash.getRuta(ruta.getCodigoRuta()) != null)
            return "\"Código de ruta repetido.\"";
        ruta.setGrafo(new Grafo());
        this.tablaHash.insertar(ruta);
        return "\"Ruta agregada!\"";
    }

    
    @POST
    @Path("recorrido")
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregarRecorrido(VerificacionRecorrido v)
    {
        Ruta rutaT = this.tablaHash.getRuta(v.getCodRuta());
        if(rutaT == null)
            return "\"Código de ruta inválido.\"";
        NodoEstacion nE1 = estaciones.getEstacion(v.getCodOrigen());
        NodoEstacion nE2 = estaciones.getEstacion(v.getCodDestino());
        if(nE1 == null)
            return "\"No se a registrado una estación con este codigo "+v.getCodOrigen()+"\"";
        if(nE2 == null)
            return "\"No se a registrado una estación con este codigo "+v.getCodDestino()+"\"";
        if(v.getCodOrigen().equals(v.getCodDestino()))
            return "\"No se puede agregar un recorrido hacia el mismo punto\"";
        
        rutaT.getGrafo().agregarEstacion(new NodoGrafo(nE1.getNomEstacion(),nE1.getCodEstacion(),nE1.getLatitud(),nE1.getLongitud()));
        rutaT.getGrafo().agregarEstacion(new NodoGrafo(nE2.getNomEstacion(),nE2.getCodEstacion(),nE2.getLatitud(),nE2.getLongitud()));
        return rutaT.getGrafo().agregarRecorrido(v.getCodOrigen(), v.getCodDestino(), v.getDistancia(),v.getTrafico());
    }
    
    @POST
    @Path("generarGrafo")
    public String generarGrafo(Ruta rutaT)
    {
        Ruta rutaO = this.tablaHash.getRuta(rutaT.getCodigoRuta());
        graficarRuta(rutaO);
        return "\"images/grafo.png\"";

    }
    
    @POST 
    @Path("transbordoValido")
    public Response estacionValida(NodoEstacion nE)
    {
        NodoEstacion nodoT = this.estaciones.getEstacion(nE.getCodEstacion());
        if(nodoT == null)
        {
            return Response.ok("false$$").build();
        }
        return Response.ok("true$$"+nodoT.getNomEstacion()).build();
    } 
    
    @POST 
    @Path("disponibles")
    public Response getRutasDisponibles(NodoEstacion nE)
    {   
        return Response.ok(this.tablaHash.getJsonRutasDisponibles(nE.getCodEstacion())).build();
    } 
    //--------------------------GRAFICA------------------------------
    
    private void graficarRuta(Ruta ruta) 
    {        
        String path = "/home/ciberveliz/NetBeansProjects/servidor/src/main/webapp/images";
        String fileIn = path + "/grafo.txt";
        String fileOut = path + "/grafo.png";
        File temp = new File(fileIn);
        try
        {
            BufferedWriter bw;

            try {
                bw = new BufferedWriter(new FileWriter(temp));
                bw.write("digraph lista{ rankdir=TB; concentrate=true; node [shape = cricle, style=\"filled\",fillcolor=\"gray\",fontsize=\"8\",margin=\"0\",fontname=\"Raleway\"];");
                this.textDot = "";
                if(!ruta.getGrafo().vertices.isEmpty())
                    generarGrafo(ruta.getGrafo().vertices.primero, ruta.getColorRuta());
                bw.write(this.textDot + "}");
                bw.close();
                String[] cmd = {"dot","-Tpng",fileIn,"-o",fileOut};
                Runtime.getRuntime().exec(cmd);
            } catch (IOException ex) {
                //Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(Exception e){
        }
        
    }
    
    private void generarGrafo(NodoVertice vertice, String color)
    {
        this.textDot += "nodo" + vertice.vertice.hashCode() + "[label=\"" + vertice.vertice.getNombre()+"\", shape=\"circle\"]; \n";
        NodoArista nodeA = vertice.vertice.aristas.primero;
        while(nodeA != null)
        {
            textDot += "\"nodo" + vertice.vertice.hashCode() + "\"-> \"nodo" + nodeA.arista.getDestino().hashCode() + "\" [ label= \""+nodeA.arista.getDistancia()+" m\",style=\"vee\",color =\""+color+"\",fontsize=\"6\"] \n";
            
            nodeA = nodeA.sig;
        }
        if(vertice.sig != null)
            generarGrafo(vertice.sig, color);
    }
}
