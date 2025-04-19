package com.lhj.springcsnotes.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceSetter {

    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String order() {
        return "[Setter 주입] → " + paymentService.pay();
    }
}
