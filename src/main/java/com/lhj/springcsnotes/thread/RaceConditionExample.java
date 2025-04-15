package com.lhj.springcsnotes.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceConditionExample {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_TASKS = 1000;
        final int NUM_THREADS = 10;

        UnsafeCounter unsafe = new UnsafeCounter();
        SafeCounter safe = new SafeCounter();

        // Executor 생성
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // 각 카운터에 동시에 increment 수행
        for (int i = 0; i < NUM_TASKS; i++) {
            executor.submit(() -> {
                unsafe.increment();
                safe.increment();
                // System.out.println("Thread: " + Thread.currentThread().getName());
            });
        }

        // 종료 대기
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("동기화 없는 결과 (Race Condition 발생): " + unsafe.get());
        System.out.println("동기화된 결과: " + safe.get());
    }
}

// 동기화 안 된 Counter(Race Condition 발생 가능)
class UnsafeCounter {
    private int count = 0;

    public void increment() {
        count++; // 동기화 없음
    }

    public int get() {
        return count;
    }
}

// 동기화 된 Counter
class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int get() {
        return count;
    }
}