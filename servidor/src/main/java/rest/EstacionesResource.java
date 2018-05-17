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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class EstacionesResource {

    private ListaEstaciones estaciones = ListaEstaciones.getListaIncial();
    
    
    @GET
    public ListaEstaciones getEstaciones()
    {
        return this.estaciones;
    }
    
    @POST
    public Response agregarEstacion(String codEstacion, String nomEstacion, Double latitud, Double longitud)
    {
        //VERIFICAR SI EL CODIGO ESTA REPETIDO, SI LO ESTA MANDAR MSG DE ERROR
        NodoEstacion nE = new NodoEstacion(codEstacion, nomEstacion, latitud, longitud);
        this.estaciones.agregarEstacion(nE);
        
        return Response.ok(nE).build();
    }
}
