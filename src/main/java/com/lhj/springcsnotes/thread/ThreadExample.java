package com.lhj.springcsnotes.thread;

public class ThreadExample {

    // 방법 1: Thread 클래스 상속
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread 실행 중 - Thread 상속");
        }
    }

    // 방법 2: Runnable 인터페이스 구현
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnable 실행 중 - Runnable 구현");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main 메인 스레드 시작");

        // Thread 상속 방식
        Thread t1 = new MyThread();
        t1.start(); // start() 호출해야 새로운 스레드 생성됨

        // Runnable 구현 방식
        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        // 익명 Runnable (람다식)
        Thread t3 = new Thread(() -> {
            System.out.println("Lambda 실행 중 - 람다식 Runnable");
        });
        t3.start();

        System.out.println("Main 메인 스레드 종료");
    }
}
