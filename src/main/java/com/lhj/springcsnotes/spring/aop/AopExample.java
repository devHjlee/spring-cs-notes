package com.lhj.springcsnotes.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;

@Slf4j
@Configuration
@ComponentScan("com.lhj.springcsnotes.spring.aop")
@EnableAspectJAutoProxy
public class AopExample {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AopExample.class);
        var memberService = context.getBean(MemberService.class);

        log.info("정상 흐름 테스트");
        memberService.hello("hj");

        log.info("예외 흐름 테스트");
        try {
            memberService.fail("hj");
        } catch (Exception e) {
            log.info("main에서 예외 잡힘: {}", e.getMessage());
        }
    }
}