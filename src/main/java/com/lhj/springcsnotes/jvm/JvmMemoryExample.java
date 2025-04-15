package com.lhj.springcsnotes.jvm;

public class JvmMemoryExample {

    // 메서드 영역 (static 변수)
    static String staticValue = "공용 static 데이터";

    public static void main(String[] args) {
        // 스택 영역
        int localVariable = 42;

        // 힙 영역 (new로 만든 객체)
        Person person = new Person("hj");

        System.out.println("localVariable (stack): " + localVariable);
        System.out.println("person.name (heap): " + person.getName());
        System.out.println("staticValue (method area): " + staticValue);

        // 메서드 호출 -> 또 다른 스택 생성됨
        printInfo(person);
    }

    static void printInfo(Person p) {
        String upper = p.getName().toUpperCase(); // 스택 + 힙 혼합 사용
        System.out.println("대문자 이름: " + upper);
    }

    static class Person {
        private String name; // 힙 영역에 위치

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}

