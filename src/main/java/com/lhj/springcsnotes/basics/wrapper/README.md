# 💡 Wrapper 클래스와 Primitive 비교: equals, 오토박싱, 언박싱, NPE

기본 타입(primitive)과 Wrapper 클래스(Integer 등)의 차이, 성능비교, NPE 예제를 통해 정리

---

## ✅ 1. Primitive vs Wrapper 비교

```java
int a = 10;
int b = 10;
System.out.println(a == b); // true

Integer x = Integer.valueOf(10);
Integer y = Integer.valueOf(10);
System.out.println(x == y);         // true (캐싱된 객체)
System.out.println(x.equals(y));    // true (값 비교)

Integer i1 = 127;
Integer i2 = 127;
System.out.println(i1 == i2);       // true (JVM 캐싱 범위)

Integer i3 = 128;
Integer i4 = 128;
System.out.println(i3 == i4);       // false (다른 객체)
```

- `==`은 참조(주소) 비교
- `.equals()`는 값 비교
- `Integer.valueOf()`를 쓰면 JVM 내부적으로 -128~127 값은 캐싱됨

---

## ✅ 2. 오토박싱/언박싱 성능 비교

```java
final int LOOP = 100000000;

// Primitive
int sum1 = 0;
for (int i = 0; i < LOOP; i++) {
    sum1 += i;
}

// Wrapper
Integer sum2 = 0;
for (int i = 0; i < LOOP; i++) {
    sum2 += i; // 오토박싱 + 언박싱 반복
}
```

### 예상 결과

- `int`가 훨씬 빠름
- `Integer`는 매번 객체를 생성하거나 캐싱 → 성능 저하

---

## ⚠️ 3. 언박싱 시 NullPointerException 주의

```java
Integer wrapper = null;

try {
    int value = wrapper; // 언박싱 과정에서 NPE 발생
} catch (NullPointerException e) {
    System.out.println("언박싱 중 NPE 발생: " + e.getMessage());
}
```

- Wrapper 클래스는 `null`을 가질 수 있음
- `int value = wrapper;`로 언박싱 시 JVM은 `wrapper.intValue()` 호출 → `null.intValue()` = NPE

---

## 📌 요약

| 항목 | 설명 |
|------|------|
| `==` | 참조 비교 (주소) |
| `equals()` | 값 비교 |
| 캐싱 범위 | Integer -128 ~ 127 |
| 성능 | `int` > `Integer` |
| 주의 | Wrapper → Primitive 언박싱 시 null → NPE |

---

## ✅ 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`Wrapper`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/basics/wrapper) 경로에서 확인할 수 있습니다.
