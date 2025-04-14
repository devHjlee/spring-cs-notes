# 💡 객체지향 설계 5원칙 - SOLID 예제 (Pay 도메인)

`Pay` 결제 도메인을 활용하여 객체지향 설계의 5대 원칙인 **SOLID** 원칙을 간단한 코드로 정리

---

## 🔸 SOLID란?

| 원칙 | 설명 |
|------|------|
| **S - 단일 책임 원칙**<br>(Single Responsibility Principle) | 클래스는 하나의 책임만 가져야 한다 |
| **O - 개방/폐쇄 원칙**<br>(Open/Closed Principle) | 확장에는 열려 있고, 수정에는 닫혀 있어야 한다 |
| **L - 리스코프 치환 원칙**<br>(Liskov Substitution Principle) | 부모 클래스를 사용하는 곳에 자식 클래스를 넣어도 문제 없어야 한다 |
| **I - 인터페이스 분리 원칙**<br>(Interface Segregation Principle) | 불필요한 메서드를 강요하지 않도록 인터페이스를 분리해야 한다 |
| **D - 의존 역전 원칙**<br>(Dependency Inversion Principle) | 고수준 모듈이 저수준 모듈에 의존하지 않고, 추상화에 의존해야 한다 |

---

## ✅ 각 원칙별 간단 예제

### 1. 단일 책임 원칙 (SRP)

```java
class PayProcessor {
    public void process(PayRequest req) {
        System.out.println("[" + req.userId + "] 결제: " + req.amount + "원");
    }
}

class Logger {
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
```

> 결제 처리와 로깅 책임을 분리하여, 변경 이유가 명확하게 하나씩만 존재하도록 구성합니다.

---

### 2. 개방/폐쇄 원칙 (OCP)

```java
interface Payment { void pay(int amount); }

class CardPayment implements Payment {
    public void pay(int amount) { System.out.println("카드 결제: " + amount); }
}

class PaymentService {
    private final Payment payment;
    public PaymentService(Payment payment) { this.payment = payment; }
    public void process(int amount) { payment.pay(amount); }
}
```

> 새로운 결제 수단을 추가할 때 기존 클래스를 수정하지 않고, 인터페이스를 구현하여 확장합니다.

---

### 3. 리스코프 치환 원칙 (LSP)

```java
class BasePayment {
    public void pay(int amount) { System.out.println("결제: " + amount); }
}

class ValidPayment extends BasePayment {
    @Override
    public void pay(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("금액 오류");
        super.pay(amount);
    }
}
```

> 부모 클래스가 사용되는 자리에 자식 클래스가 대체되어도 기능이 정상 동작해야 합니다.

---

### 4. 인터페이스 분리 원칙 (ISP)

```java
interface Payable { void pay(int amount); }
interface Refundable { void refund(int amount); }

class Card implements Payable, Refundable {
    public void pay(int amount) { System.out.println("카드 결제: " + amount); }
    public void refund(int amount) { System.out.println("카드 환불: " + amount); }
}

class GiftCard implements Payable {
    public void pay(int amount) { System.out.println("기프트카드 사용: " + amount); }
}
```

> 불필요한 메서드 구현을 강요하지 않도록 역할을 분리된 인터페이스로 나눕니다.

---

### 5. 의존 역전 원칙 (DIP)

```java
interface PaymentGateway { void pay(int amount); }

class NaverPay implements PaymentGateway {
    public void pay(int amount) { System.out.println("네이버페이 결제: " + amount); }
}

class PayManager {
    private final PaymentGateway gateway;
    // private final NaverPay gateway = new NaverPay(); // X 직접 생성하고 사용
    // PaymentGateway gateway = new NaverPay(); // 또는 TossPay, KakaoPay도 가능
    public PayManager(PaymentGateway gateway) { this.gateway = gateway; }
    public void execute(int amount) { gateway.pay(amount); }
}
```

> 고수준 클래스(PayManager)가 추상화(PaymentGateway)에 의존하고, 구체 구현은 외부에서 주입받습니다.

---

## ✅ 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`oop/solid`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/oop/solid) 경로에서 확인할 수 있습니다.

