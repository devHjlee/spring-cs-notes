# equals() vs == 그리고 hashCode() 정리

---

## 1. == vs equals()

| 비교 방식 | 의미 |
|-----------|------|
| `==` | 참조(주소) 비교. 객체가 같은 인스턴스인지 |
| `.equals()` | 값(내용) 비교. 오버라이딩으로 내용 비교 가능 |

```java
String a = new String("hello");
String b = new String("hello");

System.out.println(a == b);       // false (주소 다름)
System.out.println(a.equals(b));  // true  (내용 같음)
```

---

## 2. hashCode()

- 객체를 빠르게 찾기 위한 정수값
- `HashMap`, `HashSet`에서 equals()와 함께 사용됨
- **equals()가 같으면 hashCode()도 같아야 함**

---

##️ 3. 해시 충돌 시 문제 예제

```java
class UserWrong {
    String name;
    public int hashCode() { return 42; } // 항상 같은 값
    // equals() 없음
}
```

```java
Set<UserWrong> set = new HashSet<>();
set.add(new UserWrong("HJ"));
set.add(new UserWrong("HJ"));
System.out.println(set.size()); // 2 (중복 저장됨!)
```

---

## 올바른 equals() + hashCode()

```java
class UserCorrect {
    String name;

    public boolean equals(Object obj) {
        return ((UserCorrect) obj).name.equals(this.name);
    }

    public int hashCode() {
        return name.hashCode();
    }
}
```

```java
Set<UserCorrect> set = new HashSet<>();
set.add(new UserCorrect("HJ"));
set.add(new UserCorrect("HJ"));
System.out.println(set.size()); // 1 (중복 제거됨)
```

---

## 결론 요약

- `==` : 참조(주소) 비교
- `.equals()` : 내용 비교
- `hashCode()` : 빠른 탐색을 위한 해시값
- Hash 자료구조에서는 반드시 equals + hashCode 같이 구현 필요

---

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`basics/equalsandhash`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/basics/equalsandhash) 경로에서 확인할 수 있습니다.
