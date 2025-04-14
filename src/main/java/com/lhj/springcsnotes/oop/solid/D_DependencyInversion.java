package com.lhj.springcsnotes.oop.solid;

/**
 * 의존 역전 원칙: 고수준 모듈이 구현 클래스가 아닌 추상에 의존해야 한다
 */
interface PaymentGateway {
    void pay(int amount);
}

class NaverPay implements PaymentGateway {
    public void pay(int amount) {
        System.out.println("네이버페이 결제: " + amount);
    }
}

class PayManager {
    private final PaymentGateway gateway;
    // private final NaverPay gateway = new NaverPay(); // X 직접 생성하고 사용
    // PaymentGateway gateway = new NaverPay(); // 또는 TossPay, KakaoPay도 가능

    public PayManager(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(int amount) {
        gateway.pay(amount);
    }
}

