package com.eldermoraes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("pedido")
public class PedidoResource {
    
    @Inject
    PedidoService pedidoService;

    @Path("newPedido")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void newPedido(@QueryParam("id") Long id){
        pedidoService.newPedido(id);
    }   

    @Path("cancelPedido")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelPedido(@QueryParam("id") Long id){
        pedidoService.cancelPedido(id);
    }    

}
