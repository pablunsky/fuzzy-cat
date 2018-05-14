/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import edd.urban.servidor.ArbolB;
import edd.urban.servidor.SolicitudTicket;
import edd.urban.servidor.Ticket;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author pablunsky
 */
@Path("ticket")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class TicketResource {
    
    private ArbolB tickets = new ArbolB(5,6);
    
    
    @GET
    public Response getJson() {
        return Response.ok().build();
    }
    
    @POST
    public Response crearTicket(SolicitudTicket req){
        Ticket t = new Ticket();
        t.setValor(req.getValor());
        return Response.ok(t).build();
    }
    
    @PUT
    public Response reembolsoTicket(SolicitudTicket content) {
        //ELIMINAR SALDO DE TICKET
        return Response.ok("{\"mensaje\":\"ok\"}").build();
    }
    
    
    
}
