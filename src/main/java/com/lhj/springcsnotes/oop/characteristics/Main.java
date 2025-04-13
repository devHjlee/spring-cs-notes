package com.lhj.springcsnotes.oop.characteristics;

/**
 * Pay 실행 클래스
 * - 객체지향 4대 특성을 모두 사용한 통합 예제
 */
public class Main {
    public static void main(String[] args) {

        // 캡슐화: setter를 통해 유효한 금액만 설정
        PayRequest request = new PayRequest("hj", 10000);
        request.setAmount(15000);

        // 다형성: 결제 수단을 쉽게 바꿀 수 있음
        Payment payment = new CardPayment(); // 또는 new KakaoPay();
        PayService service = new PayService(payment);

        // card 서비스 실행
        service.execute(request);

        // KakaoPay로도 결제 (다형성)
        Payment kakao = new KakaoPay();
        PayService kakaoService = new PayService(kakao);
        // kakao 서비스 실행
        kakaoService.execute(new PayRequest("hj2", 3000));
    }
}
