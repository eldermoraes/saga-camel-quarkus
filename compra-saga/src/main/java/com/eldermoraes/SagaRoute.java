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
    PedidoService pedidoService;

    @Inject
    CreditoService creditoService;

    @Override
    public void configure() throws Exception {

        CamelSagaService sagaService = new InMemorySagaService();
        getContext().addService(sagaService);

        //Saga
        from("direct:saga").saga().propagation(SagaPropagation.REQUIRES_NEW).log("Iniciando a transação")
            .to("direct:newPedido").log("Pedido ${header.id} criado. Saga ${body}.")
            .to("direct:newPedidoValor").log("Credito do pedido ${header.id} no valor de BRL ${header.valor} reservado para a saga ${body}")
            .to("direct:finaliza").log("Feito!");

        //Pedido service
        from("direct:newPedido").saga().propagation(SagaPropagation.MANDATORY)
            .compensation("direct:cancelPedido")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(pedidoService,"newPedido").log("Criando novo pedido com id ${header.id}");

        from("direct:cancelPedido")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(pedidoService,"cancelPedido").log("Pedido ${body} compensado");

        //Credito service

        from("direct:newPedidoValor").saga().propagation(SagaPropagation.MANDATORY)
            .compensation("direct:cancelPedidoValor")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(creditoService,"newPedidoValor").log("Reservando o crédito");

        from("direct:cancelPedidoValor")
            .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
            .bean(creditoService,"cancelPedidoValor").log("Credito compensado para a saga ${body}");
        
        
        //Finaliza
        from("direct:finaliza").saga().propagation(SagaPropagation.MANDATORY)
            .choice()
            .end();
    }
    
}
