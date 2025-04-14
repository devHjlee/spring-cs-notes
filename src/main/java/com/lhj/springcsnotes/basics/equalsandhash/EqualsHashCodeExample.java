package com.lhj.springcsnotes.basics.equalsandhash;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsHashCodeExample {

    public static void main(String[] args) {
        System.out.println("== , equals 비교");
        String a = new String("hello");
        String b = new String("hello");

        System.out.println("a == b : " + (a == b));           // 동일성 비교 false (주소 비교)
        System.out.println("a.equals(b) : " + a.equals(b));   // 동등성 비교 true (값 비교)

        System.out.println("hashCode 충돌 테스트 (UserWrong)");

        Set<UserWrong> wrongSet = new HashSet<>();
        wrongSet.add(new UserWrong("HJ"));
        wrongSet.add(new UserWrong("HJ"));

        System.out.println("UserWrong size: " + wrongSet.size()); // 2 (equals 미구현 → 중복 허용)

        System.out.println("hashCode, equals 구현 테스트 (UserCorrect)");

        UserCorrect userCorrect = new UserCorrect("HJ");
        UserCorrect userCorrect2 = new UserCorrect("HJ");
        Set<UserCorrect> correctSet = new HashSet<>();
        correctSet.add(new UserCorrect("HJ"));
        correctSet.add(new UserCorrect("HJ"));

        System.out.println("UserCorrect size: " + correctSet.size()); // 1 (중복 제거됨)
    }
}

class UserWrong {
    private String name;

    public UserWrong(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 42; // 해시 충돌 유도
    }

    // equals 미구현
}

class UserCorrect {
    private String name;

    public UserCorrect(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserCorrect)) return false;
        UserCorrect other = (UserCorrect) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}