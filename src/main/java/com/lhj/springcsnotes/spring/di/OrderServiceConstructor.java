package com.lhj.springcsnotes.spring.di;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceConstructor {
    private final PaymentService paymentService;

    public OrderServiceConstructor(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String order() {
        return "Constructor 주입 : " + paymentService.pay();
    }
}
