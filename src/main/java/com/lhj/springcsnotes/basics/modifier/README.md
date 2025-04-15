# 자바 제어자 정리 – static / final / abstract / interface / 접근 제어자

자바에서 사용되는 클래스/메서드 수준의 제어자들을 예제 중심으로 정리  
`static`, `final`, `abstract`, `interface` 같은 키워드와  
`public`, `protected`, `default`, `private` 접근 제어자의 차이점을 코드 기반으로 비교

---

## static / final / abstract / interface

### 구조
- `Payable` → interface
- `Payment` → abstract class
- `KakaoPay` → final class (static, final 포함)
- `ModifierRunner` → main 실행 클래스

### 핵심 포인트
- `interface`: 메서드 시그니처만 정의
- `abstract`: 일부 구현 포함 가능, 직접 객체 생성 불가
- `final`: 클래스나 메서드의 확장/재정의 차단
- `static`: 클래스 단위로 공유, 객체 없이 접근

### 실행 예시

```java
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
```

---

## 접근 제어자

### 클래스: `AccessExample`
- 필드, 생성자, 메서드에 각각 `public`, `protected`, `default`, `private` 적용
- 내부/외부 클래스에서 접근 테스트

### 외부 클래스: `AccessTest`
- 같은 패키지 기준에서 접근 가능 여부 실습

### 핵심 포인트

| 제어자 | 설명 | 접근 범위 |
|--------|------|-----------|
| `public` | 어디서나 접근 가능 |  모든 클래스 |
| `protected` | 같은 패키지 + 하위 클래스 |  패키지 내부,  상속 |
| `default` | 같은 패키지 내에서만 접근 |  동일 패키지 |
| `private` | 해당 클래스 내부에서만 접근 가능 |  외부 접근 불가 |

### 필드/메서드 접근 예시

```java
example.publicField;      // 
example.protectedField;   // 같은 패키지
example.defaultField;     // 같은 패키지
// example.privateField;  // 접근 불가

example.publicMethod();      // 
example.protectedMethod();   // 
example.defaultMethod();     // 
```

---

## 📝 정리

### Modifier 키워드

| 키워드 | 설명 |
|--------|------|
| `static` | 클래스 단위 공유, 객체 없이 사용 |
| `final` | 상속/재정의 금지 |
| `abstract` | 추상화된 구조, 반드시 구현 필요 |
| `interface` | 기능 명세, 다중 구현 가능 |

### 접근 제어자

| 접근 제어자 | 클래스 외부 접근 | 같은 패키지 | 하위 클래스 |
|--------------|------------------|----------------|----------------|
| `public`     |  가능          |  가능        |  가능        |
| `protected`  |  직접 접근 불가 |  가능        |  상속 시 가능 |
| `default`    |  불가          |  가능        |              |
| `private`    |  불가          |  불가        |              |

---

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`basics/modifier`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/basics/modifier) 경로에서 확인할 수 있습니다.

