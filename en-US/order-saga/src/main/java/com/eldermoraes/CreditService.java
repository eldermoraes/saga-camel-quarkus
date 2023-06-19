package com.eldermoraes;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Header;

@ApplicationScoped
public class CreditService{
    
    private int totalCredit;
    private Map<Long, Integer> order_value = new HashMap<>();

    public CreditService(){
        this.totalCredit = 100;
    }

    public void newOrderValue(@Header("id") Long orderId, @Header("value") int value){
        if (value > totalCredit){
            throw new IllegalStateException("insufficient funds");
        }

        totalCredit = totalCredit - value;
        order_value.put(orderId, value);
    }

    public void cancelOrderValue(@Header("id") Long id){
        System.out.println("OrderValue failed. Starting order cancel...");
    }

    public int getTotalCredit(){
        return totalCredit;
    }    
}
