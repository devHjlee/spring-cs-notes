# 💡 Java Thread & Race Condition 실습 예제

`Thread`, `Runnable`, 그리고 `Race Condition`과 이를 방지하기 위한 `synchronized` 키워드에 대해 정리

---

## Thread 사용 방법

### 1. `Thread` 클래스 상속

```java
static class MyThread extends Thread {
    public void run() {
        System.out.println("MyThread 실행 중 - Thread 상속");
    }
}

Thread t1 = new MyThread();
t1.start(); // start() 호출로 새 스레드 생성
```

### 2. `Runnable` 인터페이스 구현

```java
static class MyRunnable implements Runnable {
    public void run() {
        System.out.println("MyRunnable 실행 중 - Runnable 구현");
    }
}

Thread t2 = new Thread(new MyRunnable());
t2.start();
```

### 3. 람다식으로 Runnable 작성

```java
Thread t3 = new Thread(() -> {
    System.out.println("Lambda 실행 중 - 람다식 Runnable");
});
t3.start();
```

---

## Race Condition 예제

멀티스레드 환경에서 여러 스레드가 동시에 공유 자원에 접근할 경우, 의도치 않은 결과가 발생할 수 있습니다. 이를 `Race Condition`이라고 합니다.

### 동기화 안 된 예제 (`UnsafeCounter`)

```java
class UnsafeCounter {
    private int count = 0;
    public void increment() {
        count++; // 동기화 없음
    }
    public int get() {
        return count;
    }
}
```

### 동기화 된 예제 (`SafeCounter`)

```java
class SafeCounter {
    private int count = 0;
    public synchronized void increment() {
        count++; // synchronized로 동기화
    }
    public int get() {
        return count;
    }
}
```

### 결과 비교

```java
동기화 없는 결과 (Race Condition 발생): 948
동기화된 결과: 1000
```

> 동기화를 하지 않으면 예상보다 작은 값이 나올 수 있습니다. 이는 여러 스레드가 동시에 `count++` 연산을 수행하면서 중간 결과가 덮어써지는 현상 때문입니다.

---

## 정리

| 개념 | 설명 |
|------|------|
| Thread | 자바에서 실행 흐름(작업 단위)을 표현 |
| Runnable | 작업 내용을 정의하는 인터페이스 |
| Race Condition | 여러 스레드가 공유 자원에 동시에 접근해 생기는 오류 현상 |
| synchronized | Race Condition을 방지하기 위한 동기화 키워드 |

---


