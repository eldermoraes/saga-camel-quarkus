package com.eldermoraes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



@Path("credito")
public class CreditoResource {

    @Inject 
    CreditoService creditoService;
    
    @GET
    @Path("newPedidoValor")
    @Produces(MediaType.TEXT_PLAIN)
    public Response newPedidoValor(@QueryParam("pedidoId") Long pedidoId, @QueryParam("valor") int valor){
        try{
            creditoService.newPedidoValor(pedidoId, valor);
            return Response.ok().build();
        } catch(IllegalStateException e){
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
        
    }

    @GET
    @Path("cancelPedidoValor")
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelPedidoValor(@QueryParam("id") Long id){
        creditoService.cancelPedidoValor(id);
    }
    
    @GET
    @Path("getCreditoTotal")
    @Produces(MediaType.TEXT_PLAIN)
    public int getCreditoTotal(){
        return creditoService.getCreditoTotal();
    }    

}
