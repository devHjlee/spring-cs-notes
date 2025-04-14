package com.lhj.springcsnotes.oop.solid;

/**
 * 인터페이스 분리 원칙: 불필요한 메서드를 강요하지 않는다
 */
interface Payable {
    void pay(int amount);
}

interface Refundable {
    void refund(int amount);
}

class Card implements Payable, Refundable {
    public void pay(int amount) {
        System.out.println("카드 결제: " + amount);
    }

    public void refund(int amount) {
        System.out.println("카드 환불: " + amount);
    }
}

class GiftCard implements Payable {
    public void pay(int amount) {
        System.out.println("기프트카드 사용: " + amount);
    }
}

