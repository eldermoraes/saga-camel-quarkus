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
@RegisterRestClient(baseUri = "http://a6559f5187430444e9f11effc4fb758c-2020250950.us-east-2.elb.amazonaws.com/order")
public interface OrderService {
    
    @GET
    @Path("newOrder")
    @Produces(MediaType.TEXT_PLAIN)
    public void newOrder(@QueryParam("id") @Header("id") Long id);

    @GET
    @Path("cancelOrder")
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelOrder(@QueryParam("id") @Header("id") Long id);
}
