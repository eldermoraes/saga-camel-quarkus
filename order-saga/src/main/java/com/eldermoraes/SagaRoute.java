package com.eldermoraes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.saga.CamelSagaService;
import org.apache.camel.saga.InMemorySagaService;

@ApplicationScoped
public class SagaRoute extends RouteBuilder {

    @Inject
    OrderService orderService;

    @Inject
    CreditService creditService;

    @Override
    public void configure() throws Exception {

        CamelSagaService sagaService = new InMemorySagaService();
        getContext().addService(sagaService);

        //Saga
        from("direct:saga").saga().propagation(SagaPropagation.REQUIRES_NEW).log("Transaction started")
            .to("direct:newOrder").log("Order ${header.id} created. Saga ${body}.")
            .to("direct:newOrderValue").log("Credit for order ${header.id} with value ${header.valor} reserved for the saga ${body}")
            .to("direct:end").log("Done");

        //Order service
        from("direct:newOrder").saga().propagation(SagaPropagation.MANDATORY)
            .compensation("direct:cancelOrder")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(orderService,"newOrder").log("Creating new order with id ${header.id}");

        from("direct:cancelOrder")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(orderService,"cancelOrder").log("Order ${body} compensated");

        //Credit service
        from("direct:newOrderValue").saga().propagation(SagaPropagation.MANDATORY)
            .compensation("direct:cancelOrderValue")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(creditService,"newOrderValue").log("Credit reserved");

        from("direct:cancelOrderValue")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(creditService,"cancelOrderValue").log("Compensated credit for saga ${body}");
        
        
        //End
        from("direct:end").saga().propagation(SagaPropagation.MANDATORY)
            .choice()
            .end();
    }
    
}
