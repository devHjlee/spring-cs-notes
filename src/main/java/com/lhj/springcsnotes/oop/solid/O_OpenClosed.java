package com.lhj.springcsnotes.oop.solid;

/**
 * 개방-폐쇄 원칙: 기존 코드를 수정하지 않고 새로운 결제 수단을 추가할 수 있다
 */
interface Payment {
    void pay(int amount);
}

class CardPayment implements Payment {
    public void pay(int amount) {
        System.out.println("카드 결제: " + amount);
    }
}

class KakaoPay implements Payment {
    public void pay(int amount) {
        System.out.println("카카오페이 결제: " + amount);
    }
}

class PaymentService {
    private final Payment payment;
    public PaymentService(Payment payment) {
        this.payment = payment;
    }

    public void process(int amount) {
        payment.pay(amount);
    }
}

