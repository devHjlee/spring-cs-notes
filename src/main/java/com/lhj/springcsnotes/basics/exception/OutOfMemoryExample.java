package com.lhj.springcsnotes.basics.exception;

public class OutOfMemoryExample {
    public static void main(String[] args) {
        try {
            int[] arrayOOM = new int[Integer.MAX_VALUE];
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError 발생: " + e.getMessage());
        }
    }
}
