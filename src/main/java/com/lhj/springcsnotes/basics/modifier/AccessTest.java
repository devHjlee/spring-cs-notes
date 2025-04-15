package com.lhj.springcsnotes.basics.modifier;

public class AccessTest {
    public static void main(String[] args) {
        AccessExample example1 = new AccessExample(); // public 생성자
        // AccessExample ex2 = new AccessExample("hi");   // 같은 패키지  protected도 가능
        // AccessExample ex3 = new AccessExample(123);    // 같은 패키지  default도 가능
        // AccessExample ex4 = new AccessExample(true);   // private  접근 불가

        System.out.println("필드 접근");
        System.out.println(example1.publicField);     //
        System.out.println(example1.protectedField);  // 같은 패키지
        System.out.println(example1.defaultField);    // 같은 패키지
        // System.out.println(example1.privateField);  // 접근 불가

        System.out.println("메서드 호출");
        example1.publicMethod();      //
        example1.protectedMethod();   //
        example1.defaultMethod();     //
        // example1.privateMethod();  //

        example1.testPrivateAccess(); // 내부에서 private 접근은 가능
    }
}

