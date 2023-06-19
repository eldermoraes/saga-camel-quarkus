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
@RegisterRestClient(baseUri = "http://a3ac7799e62934227a7b0b1ce77b5908-1602933862.us-east-2.elb.amazonaws.com/credit")
public interface CreditService {
    
    @GET
    @Path("newOrderValue")
    @Produces(MediaType.TEXT_PLAIN)
    public void newOrderValue(@QueryParam("orderId") @Header("orderId") Long orderId,@QueryParam("value")  @Header("value") int value);

    @GET
    @Path("cancelOrderValue")
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelOrderValue(@QueryParam("id")  @Header("id") Long id);

    @GET
    @Path("getTotalCredit")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTotalCredit();
}
