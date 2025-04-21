# 💡 Spring AOP 핵심 정리 및 실습 예제

Spring AOP(Aspect-Oriented Programming)는 핵심 로직과 부가 로직(공통 관심사)을 분리해 가독성, 유지보수성을 향상시키는 기법

---

## AOP 용어 정리

| 용어 | 설명 |
|------|------|
| **Aspect** | 부가기능 모음 클래스 |
| **Advice** | 실제 부가기능 (@Before, @Around 등) |
| **JoinPoint** | Advice가 적용될 수 있는 지점 (메서드 실행 등) |
| **Pointcut** | Advice를 어디에 적용할지 정의 |
| **Weaving** | 실제 객체에 부가기능을 삽입하는 과정 (Spring은 프록시 기반) |

---

## 코드 예제

- `MemberService` AOP 적용 코드

```java
@Service
public class MemberService {
    public String hello(String name) {
        log.info("비즈니스 로직 실행: hello(" + name + ")");
        return "Hello, " + name;
    }

    public void fail(String name) {
        log.info("예외 발생 메서드 실행: fail(" + name + ")");
        throw new RuntimeException("예외 테스트용 오류 발생");
    }
}
```

---

## AOP 클래스

```java
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.lhj.springcsnotes.spring.aop.MemberService.*(..))")
    public void memberServiceMethods() {}

    @Before("memberServiceMethods()")
    public void before(JoinPoint jp) {
        log.info("@Before 메서드 실행 전: {}", jp.getSignature());
    }

    @AfterReturning(pointcut = "memberServiceMethods()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        log.info("@AfterReturning 정상 종료, 반환값 = {}", result);
    }

    @AfterThrowing(pointcut = "memberServiceMethods()", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Throwable ex) {
        log.info("@AfterThrowing 예외 발생: {}", ex.getMessage());
    }

    @After("memberServiceMethods()")
    public void after(JoinPoint jp) {
        log.info("@After 무조건 실행됨 (finally 개념)");
    }

    @Around("memberServiceMethods()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("@Around 실행 전");
        Object result = null;
        try {
            result = pjp.proceed();
        } finally {
            log.info("@Around 실행 후");
        }
        return result;
    }
}
```

---

## 실행 흐름 예시

```java
@Service
public class MemberService {
    public String hello(String name) {
        log.info("비즈니스 로직 실행: hello(" + name + ")");
        return "Hello, " + name;
    }

    public void fail(String name) {
        log.info("예외 발생 메서드 실행: fail(" + name + ")");
        throw new RuntimeException("예외 테스트용 오류 발생");
    }
}
```

실행 로그:
```
AopExample -- 정상 흐름 테스트
LoggingAspect -- @Around 실행 전
LoggingAspect -- @Before 메서드 실행 전: String com.lhj.springcsnotes.spring.aop.MemberService.hello(String)
MemberService -- 비즈니스 로직 실행: hello(hj)
LoggingAspect -- @AfterReturning 정상 종료, 반환값 = Hello, hj
LoggingAspect -- @After 무조건 실행됨 (finally 개념)
LoggingAspect -- @Around 실행 후
AopExample -- 예외 흐름 테스트
LoggingAspect -- @Around 실행 전
LoggingAspect -- @Before 메서드 실행 전: void com.lhj.springcsnotes.spring.aop.MemberService.fail(String)
MemberService -- 예외 발생 메서드 실행: fail(hj)
LoggingAspect -- @AfterThrowing 예외 발생: 예외 테스트용 오류 발생
LoggingAspect -- @After 무조건 실행됨 (finally 개념)
LoggingAspect -- @Around 실행 후
AopExample -- main에서 예외 잡힘: 예외 테스트용 오류 발생
```

---

## 어드바이스 실행 시점 요약

| 어드바이스 | 실행 시점 | 예 |
|------------|------------|----|
| `@Before` | 메서드 실행 전 | 인증, 로깅 |
| `@Around` | 실행 전/후 | 트랜잭션, 타이머 |
| `@AfterReturning` | 정상 종료 후 | 응답 로깅 |
| `@AfterThrowing` | 예외 발생 시 | 예외 알림, rollback |
| `@After` | 항상 실행됨 | 리소스 정리 |

---

## AOP 설정을 위한 필수 요소

- `@Aspect`: 부가기능 클래스임을 표시
- `@Component`: 빈 등록
- `@EnableAspectJAutoProxy`: AOP 프록시 기능 활성화
- 의존성: `spring-boot-starter-aop` 필요

```groovy
implementation 'org.springframework.boot:spring-boot-starter-aop'
```

---

## AOP 요약

- **Spring AOP는 프록시 기반** (JDK 동적 프록시 or CGLIB)
- 트랜잭션, 로깅, 예외처리, 인증 같은 **공통 관심사를 모듈화**함
- `@Around`는 `@Before + @After`를 감쌈. `proceed()` 호출 필수
- `@AfterThrowing`은 예외 발생 시에만 실행
- 실무에서는 AOP로 **트랜잭션, 로그 수집, 모니터링** 등을 구현
- `@EnableAspectJAutoProxy`를 통해 AOP 기능 활성화 필요
---

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`spring/aop`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/spring/aop) 경로에서 확인할 수 있습니다.
