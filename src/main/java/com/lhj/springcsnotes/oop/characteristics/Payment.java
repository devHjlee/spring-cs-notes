package com.lhj.springcsnotes.oop.characteristics;

/**
 * - 추상화 : 공통 결제 기능(pay)을 인터페이스로 정의
 * - 어떤 결제 수단인지 몰라도 이 인터페이스만으로 사용 가능
 */
public interface Payment {
    void pay(PayRequest request);
}

