package com.eldermoraes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;

@Path("compra-camel")
public class CompraCamelResource {
    
    @Inject
    CamelContext context;

    @Path("teste")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response saga(){

        try{
            Long id = 0L;

            comprar(++id, 20);
            comprar(++id, 30);
            comprar(++id, 30);
            comprar(++id, 25);
    
            return Response.ok().build();
    
        } catch (Exception e){
            return Response.status(500).build();
        }
    }

    private void comprar(Long id, int valor){
        System.out.println("Pedido: " + id + " valor: " + valor);

        try{
            context.createFluentProducerTemplate()
            .to("direct:saga")
            .withHeader("id", id)
            .withHeader("valor", valor)
            .request();
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }


    }

}
