package com.eldermoraes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;

@Path("order-camel")
public class OrderCamelResource {
    
    @Inject
    CamelContext context;

    @Path("test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response saga(){

        try{
            Long id = 0L;

            comprar(++id, 20);
            comprar(++id, 30);
            comprar(++id, 30);
            comprar(++id, 25);
    
            return Response.ok().entity("All good").build();
    
        } catch (Exception e){
            return Response.status(500).entity("Something went wrong").build();
        }
    }

    private void comprar(Long id, int value){
        System.out.println("Order: " + id + " value: " + value);

        try{
            context.createFluentProducerTemplate()
            .to("direct:saga")
            .withHeader("id", id)
            .withHeader("value", value)
            .request();
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }


    }

}
