package com.lhj.springcsnotes.basics.modifier;

public class ModifierRunner {
    public static void main(String[] args) {
        KakaoPay.greet(); // static 메서드 호출

        KakaoPay kakaoPay = new KakaoPay();
        kakaoPay.validate();       // abstract 구현
        kakaoPay.pay(15000);       // interface 구현
        kakaoPay.printReceipt();   // 추상 클래스 구현된 메서드
        kakaoPay.complete();       // final 메서드
    }
}
