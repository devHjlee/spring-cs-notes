package com.lhj.springcsnotes.basics.stringtype;

public class StringTypeExample {
    public static void main(String[] args) {
        compareStrings();
        compareBuilders();
    }

    public static void compareStrings() {
        String s = "hello";
        s += " world"; // 새 객체 생성됨
        System.out.println("String 결과: " + s);
    }

    public static void compareBuilders() {
        StringBuilder sb = new StringBuilder("hello");
        sb.append(" world");
        System.out.println("StringBuilder 결과: " + sb.toString());

        StringBuffer sbf = new StringBuffer("hello");
        sbf.append(" world");
        System.out.println("StringBuffer 결과: " + sbf.toString());
    }
}
