# @Transactional 트랜잭션 처리 정리

Spring에서는 `@Transactional`을 통해 AOP 기반 트랜잭션을 선언적으로 제어

---

## 테스트 시나리오 요약

| 시나리오 | 트랜잭션 | 결과 요약 |
|----------|----------|------------|
| 트랜잭션 없는 경우 예외 발생 | ❌ | 주문 저장은 커밋됨 |
| 트랜잭션 있는 경우 예외 발생 | ✅ | 전체 롤백됨 |
| `RuntimeException` 발생 | ✅ | 기본적으로 롤백됨 |
| `CheckedException` 발생 | ✅ | 기본적으로 롤백되지 않음 |
| `CheckedException + rollbackFor` 설정 | ✅ | 명시적으로 롤백됨 |
| `RuntimeException + noRollbackFor` 설정 | ✅ | 롤백되지 않음 |
| 내부 알림 메서드에 `REQUIRES_NEW` | ✅ + NEW | 알림만 롤백, 주문은 커밋됨 |

---

## 테스트 대상 서비스 (OrderServiceTest 참고)

```java
@Transactional
public void orderWithTx(String item) { ... }

public void orderWithoutTx(String item) { ... }

@Transactional
public void orderWithRuntimeEx(String item) { ... }

@Transactional
public void orderWithCheckedEx(String item) throws Exception { ... }

@Transactional(rollbackFor = Exception.class)
public void orderWithCheckedExRollback(String item) throws Exception { ... }

@Transactional(noRollbackFor = RuntimeException.class)
public void orderWithNoRollbackRuntime(String item) { ... }

@Transactional
public void placeOrderWithNotification(String item) {
    orderRepository.save(...);
    try {
        notificationService.send(...); // REQUIRES_NEW
    } catch (Exception e) {
        log.warn("알림 전송 실패");
    }
}
```

---

## 전파(Propagation.REQUIRES_NEW) 테스트

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void send(String message) {
    notificationRepository.save(...);
    if (message.contains("fail")) throw new RuntimeException();
}
```

- 주문 저장은 기존 트랜잭션(REQUIRED)
- 알림 전송은 새로운 트랜잭션(REQUIRES_NEW)
- 알림 실패 시 주문은 커밋되고, 알림은 롤백됨

---

## 주요 개념 정리

| 항목 | 설명 |
|------|------|
| REQUIRED | (기본값) 기존 트랜잭션 참여 또는 새로 시작 |
| REQUIRES_NEW | 항상 새 트랜잭션 생성, 기존 트랜잭션은 잠시 중단 |
| rollbackFor | 특정 예외에 대해 명시적 롤백 지정 |
| noRollbackFor | 특정 예외에 대해 롤백 방지 |

- `@Transactional`은 기본적으로 RuntimeException에서만 롤백
- 전파 옵션은 트랜잭션 분리/유지에 중요
- `REQUIRES_NEW`는 부분 커밋 처리에 유용

---

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`spring/transaction`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/spring/transaction) 경로에서 확인할 수 있습니다.
