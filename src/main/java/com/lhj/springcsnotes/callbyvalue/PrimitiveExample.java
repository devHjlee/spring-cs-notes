package com.lhj.springcsnotes.callbyvalue;

public class PrimitiveExample {

    // Call by Value Primitive(int) 예시
    public static void main(String[] args) {
        int a = 10;  // 스택 변수 a가 10이라는 값을 가짐

        change(a);   // a의 값 10이 복사되어 change(x)에 전달됨
        // x = 10 으로 시작됨 → x만 바뀌고 a는 그대로

        System.out.println("a = " + a);  // 결과: a = 10

    }

    public static void change(int x) {
        // x는 main()에서 넘긴 a의 값을 복사해서 받은 변수임
        // 즉, x = 10 (a의 값이 복사된 것)
        x = 100;  // 복사된 x만 바꾸는 거지 a에는 아무 영향 없음
    }
}
