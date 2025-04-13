# 💡 객체지향 4대 특성 - 결제(Pay) 예제로 정리하기

결제 도메인을 예시로 객체지향 프로그래밍의 4대 특성 **캡슐화, 상속, 추상화, 다형성**을 한 번에 정리

---

## 📝 객체지향 4대 특성이란?

| 특성 | 설명 |
|------|------|
| **캡슐화** | 데이터(필드)를 직접 접근하지 못하도록 숨기고, 메서드를 통해 제어 |
| **상속** | 공통 기능을 부모 클래스에 정의하고, 자식 클래스가 이를 재사용 |
| **추상화** | 복잡한 구현을 숨기고 필요한 기능만 인터페이스/추상 클래스로 정의 |
| **다형성** | 하나의 타입(부모/인터페이스)으로 다양한 구현체를 동일하게 사용 |

---

## 예제 시나리오

```java
// PayRequest.java
public class PayRequest {
    private String userId;
    private int amount;

    public PayRequest(String userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }

    // 외부에서 userId 읽기만 가능 (직접 변경은 불가)
    public String getUserId() {
        return userId;
    }

    // 유효성 검사 포함 (0원 이하 금액은 허용되지 않음)
    public void setAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("결제 금액은 0보다 커야 합니다.");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
```

→ `userId`, `amount` 필드를 `private`으로 숨기고,  
getter/setter로만 접근하게 하여 **캡슐화**를 구현

---

## 추상화 + 상속 + 다형성

```java
// Payment.java
public interface Payment {
    void pay(PayRequest request);
}

// CardPayment.java
/**
 * 상속 + 구현체 1
 * - Card 결제를 위해 Payment 인터페이스를 구현
 */
public class CardPayment implements Payment {

    @Override
    public void pay(PayRequest request) {
        System.out.println("[카드결제] " + request.getUserId() + "님 " + request.getAmount() + "원 결제 완료");
    }
}
// KakaoPay.java
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
```

- `Payment`는 결제라는 **동작만 정의**한 **추상화**
- `CardPayment`는 이를 **상속받아 구현 1**
- `KakaoPayment`는 이를 **상속받아 구현 2**
- 다양한 구현체가 있으므로 **다형성 적용 가능**

---

## 다형성 활용 예시

```java
// PayService.java
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
```

```java
// Main.java
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
```

---

## ✅ 핵심 요약

- **캡슐화** → `PayRequest`: 필드 은닉 + 유효성 체크
- **추상화** → `Payment`: 인터페이스 설계
- **상속** → `CardPayment`, `KakaoPay`: 인터페이스 구현
- **다형성** → `PayService`: 다양한 결제 수단을 유연하게 처리

---

## ✅ 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`oop/characteristics`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/oop/characteristics) 경로에서 확인할 수 있습니다.
