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
    
    private ArbolB tickets = ArbolB.getArbolTickets();
    
    @GET
    public Response getCount() {
        return Response.ok(tickets.getCounts()).build();
    }
    
    @POST
    public Response crearTicket(SolicitudTicket req){
        Ticket t = new Ticket();
        t.setValor(req.getValor());
        tickets.Add(t);
        return Response.ok(t).build();
    }
    
    @PUT
    public Response reembolsoTicket(SolicitudTicket content) {
        Ticket t = tickets.Buscar(content.getCodigo());
        if(t!=null){
            t.setSaldo_actual(0);
            return Response.ok("{\"mensaje\":\"El valor del ticket ha sido compensado.\"}").build();
        }
        return Response.ok("{\"mensaje\":\"Error, el ticket solicitado no existe.\"}").build();
    }
    
    
    
}
