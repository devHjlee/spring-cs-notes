package com.lhj.springcsnotes.basics.wrapper;

/**
 * Wrapper 클래스 사용, 비교 방식 차이
 */
public class WrapperExample {

    public static void main(String[] args) {
        comparePrimitiveAndWrapperEquality();
        compareBoxingUnboxingPerformance();
        unboxingNullNPE();
    }

    // Primitive vs Wrapper 동등성 비교
    public static void comparePrimitiveAndWrapperEquality() {
        System.out.println("comparePrimitiveAndWrapperEquality Start");

        int a = 10;
        int b = 10;
        System.out.println("Primitive == 비교: " + (a == b)); // true

        // Integer x = new Integer(10); deprecated
        Integer x = Integer.valueOf(10);
        Integer y = Integer.valueOf(10);
        System.out.println("Wrapper == 비교: " + (x == y));       // true (캐싱)
        System.out.println("Wrapper equals 비교: " + x.equals(y)); // true

        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println("127 오토박싱 == 비교: " + (i1 == i2)); // true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println("128 오토박싱 == 비교: " + (i3 == i4)); // false

    }

    // Primitive vs Wrapper 성능 비교
    public static void compareBoxingUnboxingPerformance() {
        System.out.println("compareBoxingUnboxingPerformance Start");

        final int LOOP = 100000000;
        long start, end;

        // Primitive 연산
        start = System.currentTimeMillis();
        int sum1 = 0;
        for (int i = 0; i < LOOP; i++) {
            sum1 += i;
        }
        end = System.currentTimeMillis();
        System.out.println("Primitive int 합계: " + sum1);
        System.out.println("Primitive 소요 시간: " + (end - start) + "ms\n");

        // Wrapper 연산
        start = System.currentTimeMillis();
        Integer sum2 = 0;
        for (int i = 0; i < LOOP; i++) {
            sum2 += i; // 오토박싱 + 언박싱 발생
        }
        end = System.currentTimeMillis();
        System.out.println("Wrapper Integer 합계: " + sum2);
        System.out.println("Wrapper 소요 시간: " + (end - start) + "ms");

    }

    // NPE 발생 주의 예제
    public static void unboxingNullNPE() {
        System.out.println("demonstrateUnboxingNullNPE Start");

        Integer wrapper = null;

        try {
            int value = wrapper; // 언박싱 중 NPE 발생
            System.out.println("값: " + value);
        } catch (NullPointerException e) {
            System.out.println("언박싱 중 NullPointerException 발생: " + e.getMessage());
        }

    }
}

