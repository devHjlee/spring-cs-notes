# 💡 의존성 주입 방식 비교 (Spring DI)

Spring에서 사용하는 3가지 주요 의존성 주입 방식에 대해 설명하고, 각각의 장단점을 비교

---

## 1. 생성자 주입 (Constructor Injection)

```java
@Service
public class OrderServiceConstructor {
    private final PaymentService paymentService;

    public OrderServiceConstructor(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String order() {
        return "[Constructor 주입] → " + paymentService.pay();
    }
}
```

### 특징
- `final` 키워드 사용 가능 → 불변성 유지
- 테스트 용이성 👍 (생성자로 주입 가능)
- 의존성 누락 시 컴파일 타임에 오류 발견 가능
- 스프링이 공식 권장하는 방식

---

## 2. 필드 주입 (Field Injection)

```java
@Service
public class OrderServiceField {

    @Autowired
    private PaymentService paymentService;

    public String order() {
        return "[Field 주입] → " + paymentService.pay();
    }
}
```

### 특징
- 코드가 간결하지만…
- 테스트 어려움 (Mock 주입 불편)
- 불변성 보장 불가
- Spring 컨테이너가 없으면 작동하지 않음 → **지양하는 방식**

---

## 3. 세터 주입 (Setter Injection)

```java
@Service
public class OrderServiceSetter {

    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String order() {
        return "[Setter 주입] → " + paymentService.pay();
    }
}
```

### 특징
- 선택적 의존성 주입에 적합
- 외부에서 주입을 바꿀 수 있어 불변성 없음
- 유연하지만, 무분별하게 사용 시 설계가 약해짐

---

## 방식별 비교

| 구분 | 생성자 주입 | 필드 주입 | 세터 주입 |
|------|-------------|-----------|------------|
| 스프링 권장 여부 | 권장 | 비권장 | 제한적 사용 |
| 불변성 보장 | 가능 | 불가 | 불가 |
| 테스트 용이성 | 쉬움 | 어려움 | 중간 |
| 선택적 주입 | 불가 | 가능 | 가능 |

---

## 결론

- 스프링에서는 **생성자 주입이 가장 안전하고 권장되는 방식**입니다.
- 세터 주입은 **선택적 의존성**에 적합합니다.
- 필드 주입은 편리하지만 **테스트와 유지보수 측면에서 불리**하므로 지양하는 것이 좋습니다.

---

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`spring/di`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/spring/di) 경로에서 확인할 수 있습니다.
