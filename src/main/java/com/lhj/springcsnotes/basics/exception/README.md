# 자바 예외 처리 정리 – Checked, Unchecked, Error + 트랜잭션 롤백

자바 예외 처리의 기본 구조와 종류, 그리고 Spring에서의 트랜잭션 롤백 기준까지 함께 정리

---

## 1. 자바 예외

- Throwable
  - Error : 시스템 오류
  - Exception
    - Checked Exception : 컴파일 시 예외 처리 강제
    - Unchecked Exception : 컴파일러가 처리 강제하지 않음 (RuntimeException)

---

## 2. Checked Exception (컴파일러가 처리 요구)

- 반드시 try-catch 또는 throws로 처리해야 함
- 대표 예: `IOException`, `SQLException`, `ClassNotFoundException`

### 실습 예제

```java
try {
    throw new ClassNotFoundException("파일을 찾을 수 없습니다.");
} catch (ClassNotFoundException e) {
    System.out.println("Checked 예외 처리됨: " + e.getMessage());
}
```

---

## 3. Unchecked Exception (Runtime 예외)

- 컴파일러가 강제하지 않음
- 대부분 프로그래머의 실수에서 발생
- 대표 예: `NullPointerException`, `IllegalArgumentException`, `ArrayIndexOutOfBoundsException`

### 실습 예제

```java
String value = null;
System.out.println(value.equals("?")); // NullPointerException 발생
```

---

## 4. Error (시스템 오류)

- 복구 불가능한 심각한 문제
- try-catch로 잡을 수는 있으나, 처리보단 시스템 종료/로그 정도만 고려 (heap dump)
- 대표 예: `OutOfMemoryError`, `StackOverflowError`, `NoClassDefFoundError`, `ExceptionInInitializerError`

### 실습 예제

```java
int[] bigArray = new int[Integer.MAX_VALUE]; // OutOfMemoryError
```

---

## 5. Spring @Transactional에서 롤백 여부

| 예외 유형 | 기본 롤백 여부 |
|-----------|----------------|
| `RuntimeException` (Unchecked) | 롤백함 |
| `Checked Exception` | 롤백 안 함 (명시해야 함) |

### Checked 예외에 대해 롤백하려면?

```java
@Transactional(rollbackFor = ClassNotFoundException.class)
public void checkedRollback() throws ClassNotFoundException {
    throw new ClassNotFoundException("롤백됨");
}
```

---

## 📝 정리

| 구분 | 설명 | 예시 |
|------|------|------|
| Checked Exception | 반드시 처리해야 함 | IOException, SQLException |
| Unchecked Exception | 런타임 예외, 선택적 처리 | NPE, IAE, IndexOutOfBounds |
| Error | 치명적 시스템 오류, 처리하지 않음 | OOME, SOE, NoClassDef |

---

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`basics/exception`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/basics/exception) 경로에서 확인할 수 있습니다.
