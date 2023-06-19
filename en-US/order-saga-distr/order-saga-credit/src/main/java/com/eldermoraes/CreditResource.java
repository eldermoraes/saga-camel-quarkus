package com.eldermoraes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



@Path("credit")
public class CreditResource {

    @Inject 
    CreditService creditoService;
    
    @GET
    @Path("newOrderValue")
    @Produces(MediaType.TEXT_PLAIN)
    public Response newOrderValue(@QueryParam("orderId") Long orderId, @QueryParam("value") int value){
        try{
            creditoService.newOrderValue(orderId, value);
            return Response.ok().entity("All good").build();
        } catch(IllegalStateException e){
            return Response.status(Status.NOT_ACCEPTABLE).entity("Something went wrong").build();
        }
    }

    @GET
    @Path("cancelOrderValue")
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelOrderValue(@QueryParam("id") Long id){
        creditoService.cancelOrderValue(id);
    }
    
    @GET
    @Path("getTotalCredit")
    @Produces(MediaType.TEXT_PLAIN)
    public int getCreditoTotal(){
        return creditoService.getTotalCredit();
    }    

}
