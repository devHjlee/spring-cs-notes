package com.lhj.springcsnotes.basics.modifier;

public final class KakaoPay extends Payment implements Payable {

    private static final String BRAND = "KAKAO";

    public static void greet() {
        System.out.println("[" + BRAND + "] 카카오페이에 오신 걸 환영합니다!");
    }

    @Override
    public void validate() {
        System.out.println("카카오페이 검증 완료");
    }

    @Override
    public void pay(int amount) {
        System.out.println("카카오페이로 " + amount + "원 결제됨");
    }

    public final void complete() {
        System.out.println("결제 완료 - 더 이상 오버라이드 불가");
    }
}

