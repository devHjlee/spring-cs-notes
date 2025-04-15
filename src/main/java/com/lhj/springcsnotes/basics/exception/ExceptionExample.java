package com.lhj.springcsnotes.basics.exception;

public class ExceptionExample {
    public static void main(String[] args) {
        checkedExample();
        uncheckedExample();
    }

    // Checked Exception 처리 예시 try-catch 강제,
    public static void checkedExample() {
        try {
            throw new ClassNotFoundException("파일을 찾을 수 없습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("Checked 예외 처리됨: " + e.getMessage());
        }
    }

    // Unchecked Exception 처리 예시
    public static void uncheckedExample() {
        String value = null;

        try {
            System.out.println(value.equals("?")); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Unchecked 예외 처리됨: " + e.getMessage());
        }
    }
}
