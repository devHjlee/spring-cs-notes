package com.lhj.springcsnotes.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
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
