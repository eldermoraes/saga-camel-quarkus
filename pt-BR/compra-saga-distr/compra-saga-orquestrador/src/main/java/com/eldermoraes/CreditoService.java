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
@RegisterRestClient(baseUri = "http://a546a76f95c8c4552a46df9db86ce4a2-306009174.us-east-2.elb.amazonaws.com/credito")
public interface CreditoService {
    
    @GET
    @Path("newPedidoValor")
    @Produces(MediaType.TEXT_PLAIN)
    public void newPedidoValor(@QueryParam("pedidoId") @Header("pedidoId") Long pedidoId,@QueryParam("valor")  @Header("valor") int valor);

    @GET
    @Path("cancelPedidoValor")
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelPedidoValor(@QueryParam("id")  @Header("id") Long id);

    @GET
    @Path("getCreditoTotal")
    @Produces(MediaType.TEXT_PLAIN)
    public int getCreditoTotal();
}
