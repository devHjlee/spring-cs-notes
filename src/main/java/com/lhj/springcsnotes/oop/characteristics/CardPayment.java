package com.lhj.springcsnotes.oop.characteristics;

/**
 * 상속 + 구현체 1
 * Card 결제를 위해 Payment 인터페이스를 구현
 */
public class CardPayment implements Payment {

    @Override
    public void pay(PayRequest request) {
        System.out.println("[카드결제] " + request.getUserId() + "님 " + request.getAmount() + "원 결제 완료");
    }
}
