package com.eldermoraes;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.camel.Header;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://a6a2c8d6e394443a0bda1e5728548277-781765138.us-east-2.elb.amazonaws.com/pedido")
public interface PedidoService {
    
    @GET
    @Path("newPedido")
    @Produces(MediaType.TEXT_PLAIN)
    public void newPedido(@QueryParam("id") @Header("id") Long id);

    @GET
    @Path("cancelPedido")
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelPedido(@QueryParam("id") @Header("id") Long id);
}
