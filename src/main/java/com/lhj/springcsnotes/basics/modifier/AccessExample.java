package com.lhj.springcsnotes.basics.modifier;

public class AccessExample {

    // 필드
    public String publicField = "public field";
    protected String protectedField = "protected field";
    String defaultField = "default field";
    private String privateField = "private field";

    // 생성자
    public AccessExample() {
        System.out.println("public 생성자 호출됨");
    }

    protected AccessExample(String fromProtected) {
        System.out.println("protected 생성자 호출됨: " + fromProtected);
    }

    AccessExample(int fromDefault) {
        System.out.println("default 생성자 호출됨: " + fromDefault);
    }

    private AccessExample(boolean fromPrivate) {
        System.out.println("private 생성자 호출됨: " + fromPrivate);
    }

    // 메서드
    public void publicMethod() {
        System.out.println("public 메서드 호출");
    }

    protected void protectedMethod() {
        System.out.println("protected 메서드 호출");
    }

    void defaultMethod() {
        System.out.println("default 메서드 호출");
    }

    private void privateMethod() {
        System.out.println("private 메서드 호출");
    }

    // private 접근 테스트용 내부 실행
    public void testPrivateAccess() {
        System.out.println("[내부에서 private 포함 전체 접근]");
        System.out.println(privateField);
        privateMethod();
    }
}

