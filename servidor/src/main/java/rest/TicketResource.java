/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import edd.urban.servidor.Abordaje;
import edd.urban.servidor.ArbolB;
import edd.urban.servidor.ListaAbordajes;
import edd.urban.servidor.ListaReembolsos;
import edd.urban.servidor.NodoAbordaje;
import edd.urban.servidor.NodoReembolso;
import edd.urban.servidor.Reembolso;
import edd.urban.servidor.SolicitudTicket;
import edd.urban.servidor.Ticket;
import java.text.Format;
import java.text.SimpleDateFormat;
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
    private ListaReembolsos reembolsos = ListaReembolsos.getListaIncial();
    private ListaAbordajes abordajes = ListaAbordajes.getListaIncial();
    
    @GET
    public Response getCount() {
        return Response.ok(tickets.getCounts()).build();
    }
    
    @POST
    public Response crearTicket(SolicitudTicket req){
        Ticket t = new Ticket();
        t.setValor(req.getValor());
        t.setSaldo_actual(req.getValor());
        
        tickets.Add(t);
        tickets.Graficar();
        tickets.Save();
        return Response.ok(t).build();
    }
    
    @Path("abordajes")
    @PUT
    public Response solicitudAbordaje(Abordaje req){
        Ticket t = tickets.Buscar(req.getCod_ticket());
        if(t == null){
            return Response.ok("{\"mensaje\":\"Error, el ticket solicitado no existe.\"}").build();
        }
        else if(t.getSaldo_actual() < req.getValor_abordaje()){
            return Response.ok("{\"mensaje\":\"Lo sentimos, pero no tiene suficiente saldo en el ticket para realizar el abordaje.\nTe invitamos a hacer una recarga en el kiosco mas cercano.\"}").build();
        }
        t.setSaldo_actual(t.getSaldo_actual()-req.getValor_abordaje());
        req.setFecha_abordaje(new Date());
        NodoAbordaje n = new NodoAbordaje().setValor(req.toString());
        abordajes.agregarAbordaje(n);
        tickets.Save();
        return Response.ok("{\"mensaje\":\"Solicitud validada. Saldo actual: "+t.getSaldo_actual()+". Feliz viaje\"}").build();
    }
    
    @Path("abordajes")
    @GET
    public Response getAbordajes(){
        return Response.ok(abordajes.toString()).build();
    }
    
    @PUT
    public Response reembolsoTicket(SolicitudTicket content) {
        Ticket t = tickets.Buscar(content.getCodigo());
        if(t!=null){
            
            t.setFecha_devolucion(new Date());
            Format f = new SimpleDateFormat("yyyy-MM-dd");
            double saldo_debitado = t.getSaldo_actual();
            double valor_original = t.getValor();
            int codigo_t = t.getCodigo();
            Reembolso r = new Reembolso(saldo_debitado, valor_original,codigo_t,f.format(new Date()),t.getCodigo_devolucion());
            reembolsos.agregarReembolso(new NodoReembolso().setValor(r.toString()));
            
            t.setSaldo_actual(0);
            
            return Response.ok("{\"mensaje\":\"El valor del ticket ha sido compensado.\"}").build();
        }
        tickets.Save();
        return Response.ok("{\"mensaje\":\"Error, el ticket solicitado no existe.\"}").build();
    }
    
}
