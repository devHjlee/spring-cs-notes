# String vs StringBuilder vs StringBuffer 예제 정리

Java에서 문자열을 다루는 대표적인 세 클래스인 `String`, `StringBuilder`, `StringBuffer`의 차이를 비교 정리

---

## String – 불변(Immutable) 클래스

```java
String s = "hello";
s += " world"; // 새로운 String 객체가 생성됨
System.out.println("String 결과: " + s); // hello world
```

- `String`은 불변이기 때문에 문자열을 변경할 때마다 새로운 객체가 생성됨
- 문자열 변경이 빈번할 경우 성능 저하 가능

---

## StringBuilder – 가변(Mutable), 비동기 환경에서는 주의

```java
StringBuilder sb = new StringBuilder("hello");
sb.append(" world");
System.out.println("StringBuilder 결과: " + sb.toString()); // hello world
```

- 내부 버퍼에서 직접 문자열을 조작하므로 속도가 빠름
- **멀티스레드 환경에서는 동기화가 없기 때문에 안전하지 않음**

---

## StringBuffer – 가변(Mutable), 멀티스레드 환경에서도 안전

```java
StringBuffer sbf = new StringBuffer("hello");
sbf.append(" world");
System.out.println("StringBuffer 결과: " + sbf.toString()); // hello world
```

- `StringBuilder`와 기능은 유사하지만, 모든 메서드가 `synchronized` 처리되어 있어 스레드에 안전함
- 다만 그만큼 성능은 `StringBuilder`보다 약간 느릴 수 있음

---

## 멀티스레드 환경 비교 예제

### StringBuilder 멀티스레드

```java
StringBuilder builder = new StringBuilder();

Runnable task = () -> {
    for (int i = 0; i < 1000; i++) {
        builder.append("A");
    }
};
```

- 결과: `builder.length()`가 항상 2000이 아닐 수 있음 → 비정상 동작 가능성

### StringBuffer 멀티스레드

```java
StringBuffer buffer = new StringBuffer();

Runnable task = () -> {
    for (int i = 0; i < 1000; i++) {
        buffer.append("A");
    }
};
```

- 결과: `builder.length()`는 항상 2000 → 정상 동작 보장

---

## 📝 정리

| 항목 | String | StringBuilder | StringBuffer |
|------|--------|---------------|--------------|
| 변경 가능 | 불변 | 변경 가능 | 변경 가능 |
| 성능 | 느림 | 빠름 | 보통 |
| 스레드 안전성 | 안전 | 안전하지 않음 | 안전 |
| 사용 환경 | 변경 없는 문자열 | 단일 스레드 환경 | 멀티스레드 환경 |

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`StringType`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/basics/stringtype) 경로에서 확인할 수 있습니다.
