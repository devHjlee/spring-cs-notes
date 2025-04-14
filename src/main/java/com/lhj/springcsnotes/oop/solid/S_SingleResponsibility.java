package com.lhj.springcsnotes.oop.solid;

/**
 * 단일 책임 원칙: 결제 처리와 로깅을 분리한다
 */
class PayRequest {
    String userId;
    int amount;
}

class PayProcessor {
    public void process(PayRequest req) {
        // 결제 처리
        System.out.println("[" + req.userId + "] 결제: " + req.amount + "원");
    }
}

class Logger {
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}

