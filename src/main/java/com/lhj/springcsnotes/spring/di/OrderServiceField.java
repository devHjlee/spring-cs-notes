package com.lhj.springcsnotes.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceField {

    @Autowired
    private PaymentService paymentService;

    public String order() {
        return "[Field 주입] → " + paymentService.pay();
    }
}
