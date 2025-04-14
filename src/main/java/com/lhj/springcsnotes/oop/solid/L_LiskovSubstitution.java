package com.lhj.springcsnotes.oop.solid;

/**
 * 리스코프 치환 원칙: 자식 클래스는 부모 클래스를 대체할 수 있어야 한다
 */
class BasePayment {
    public void pay(int amount) {
        System.out.println("결제: " + amount);
    }
}

class ValidPayment extends BasePayment {
    @Override
    public void pay(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("금액 오류");
        super.pay(amount);
    }
}

