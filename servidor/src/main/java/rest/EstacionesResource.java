/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import edd.urban.servidor.ListaEstaciones;
import edd.urban.servidor.NodoEstacion;
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
@Path("estaciones")


public class EstacionesResource {

    ListaEstaciones estaciones = ListaEstaciones.getListaInicial();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEstaciones()
    {
        return Response.ok(this.estaciones.toString()).build();
    }
    
       
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregarEstacion(NodoEstacion nE)
    {
        if(this.estaciones.estacionRepetida(nE.getCodEstacion()))
            return "\"Estación repetida\"";
        this.estaciones.agregarEstacion(nE);
        return "\"Estación agregada!\"";
    }
    
   
}
