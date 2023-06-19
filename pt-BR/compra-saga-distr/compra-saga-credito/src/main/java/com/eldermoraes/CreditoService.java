package com.eldermoraes;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class CreditoService {
    
    private int creditoTotal;
    private Map<Long, Integer> pedido_valor = new HashMap<>();

    public CreditoService(){
        this.creditoTotal = 100;
    }

    public void newPedidoValor(Long pedidoId, int valor){
        if (valor > creditoTotal){
            this.creditoTotal = 100; //apenas para que possamos testar vez após vez
            throw new IllegalStateException("saldo insuficiente");
        }

        creditoTotal = creditoTotal - valor;
        pedido_valor.put(pedidoId, valor);
    }

    public void cancelPedidoValor(Long id){
        System.out.println("PedidoValor falhou. Iniciando cancelamento do pedido.");
    }

    public int getCreditoTotal(){
        return creditoTotal;
    }    
}
