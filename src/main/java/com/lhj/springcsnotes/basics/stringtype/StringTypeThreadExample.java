package com.lhj.springcsnotes.basics.stringtype;

public class StringTypeThreadExample {

    public static void main(String[] args) {
        stringBuilderThread();
        stringBufferThread();
    }

    public static void stringBuilderThread() {
        System.out.println("stringBuilderThread Start");

        StringBuilder builder = new StringBuilder(); // 비동기 환경에 취약

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                builder.append("A");
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("StringBuilder 길이: " + builder.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stringBufferThread() {
        System.out.println("stringBufferThread Start");

        StringBuffer buffer = new StringBuffer(); // 스레드 안전

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                buffer.append("A");
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("StringBuffer 길이: " + buffer.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
