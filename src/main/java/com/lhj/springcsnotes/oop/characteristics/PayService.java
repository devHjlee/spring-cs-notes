package com.lhj.springcsnotes.oop.characteristics;

/**
 * - 다형성 : Payment 타입으로 다양한 구현체(CardPayment, KakaoPay)를 처리
 * - 코드 변경 없이 새로운 결제 수단 추가 가능
 */
public class PayService {

    private final Payment payment;

    // 어떤 Payment 구현체가 들어오든 PayService는 동일하게 작동함
    public PayService(Payment payment) {
        this.payment = payment;
    }

    public void execute(PayRequest request) {
        payment.pay(request);
    }
}
