package com.eldermoraes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("order")
public class OrderResource {
    
    @Inject
    OrderService orderService;

    @Path("newOrder")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void newOrder(@QueryParam("id") Long id){
        orderService.newOrder(id);
    }   

    @Path("cancelOrder")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelOrder(@QueryParam("id") Long id){
        orderService.cancelOrder(id);
    }    

}
