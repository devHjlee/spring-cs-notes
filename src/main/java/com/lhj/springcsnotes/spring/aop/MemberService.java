package com.lhj.springcsnotes.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
