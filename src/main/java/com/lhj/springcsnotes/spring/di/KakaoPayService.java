package com.lhj.springcsnotes.spring.di;

import org.springframework.stereotype.Component;

@Component
public class KakaoPayService implements PaymentService {
    @Override
    public String pay() {
        return "KakaoPay 결제 완료";
    }
}
