package com.eldermoraes;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Header;

@ApplicationScoped
public class PedidoService {
    

    private Set<Long> pedidos = new HashSet<>();

    public void newPedido(@Header("id") Long id){
        pedidos.add(id);
    }

    public void cancelPedido(@Header("id") Long id){
        pedidos.remove(id);
    }
}
