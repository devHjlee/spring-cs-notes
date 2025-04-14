package com.lhj.springcsnotes.oop.characteristics;

/**
 * 상속 + 구현체 2
 * kakaopay 결제를 위해 Payment 인터페이스를 구현
 */
public class KakaoPay implements Payment {

    @Override
    public void pay(PayRequest request) {
        System.out.println("[카카오페이] " + request.getUserId() + "님 " + request.getAmount() + "원 결제 완료");
    }
}
