# 💡 JVM 메모리 구조 정리

자바 프로그램이 실행될 때 JVM(Java Virtual Machine)은 메모리 관리를 위해 메모리를 여러 영역으로 나눠 사용
예제 코드를 통해 각 메모리 영역의 역할과 실제 코드에서 어떤 데이터가 어디에 저장되는지 확인

---

## JVM 메모리 구조 구성도

```
JVM 메모리 구조
  - 메서드 영역 (Method Area)
  - 힙 영역 (Heap)
  - 스택 영역 (Stack)
```

---

## 예제 코드

```java
public class JvmMemoryExample {

    // 메서드 영역 (static 변수)
    static String staticValue = "공용 static 데이터";

    public static void main(String[] args) {
        // 스택 영역 (지역 변수)
        int localVariable = 42;

        // 힙 영역 (new로 만든 객체)
        Person person = new Person("hj");

        System.out.println("localVariable (stack): " + localVariable);
        System.out.println("person.name (heap): " + person.getName());
        System.out.println("staticValue (method area): " + staticValue);

        // 메서드 호출 -> 또 다른 스택 프레임 생성됨
        printInfo(person);
    }

    static void printInfo(Person p) {
        String upper = p.getName().toUpperCase(); // 스택 + 힙 혼합 사용
        System.out.println("대문자 이름: " + upper);
    }

    static class Person {
        private String name; // 힙 영역

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
```

---

## 메모리 영역 (코드 기준)

| 메모리 영역 | 설명 |
|-------------|------|
| 메서드 영역 | 클래스 정보, static 변수(`staticValue`), static 메서드(`printInfo`, `main`) |
| 힙 영역 | `new Person()`으로 생성된 객체, 내부 필드 `name`, `toUpperCase()`로 생성된 `String` 객체 |
| 스택 영역 | `main`의 지역 변수 `localVariable`, `printInfo`의 매개변수 `p`, 지역 변수 `upper` |

---

## 📝 정리

- **메서드 영역**: 클래스 로딩 시 저장되는 공간, static 변수/메서드 저장
- **힙 영역**: `new` 키워드로 생성되는 객체들이 저장되는 공간 (GC 대상)
- **스택 영역**: 메서드 호출 시 생기는 실행 프레임, 지역 변수/매개변수 저장
- **GC** : GC는 힙 메모리에서 참조되지 않는 객체를 정리하며, Young(Minor GC) -> Old(Major GC) 관리, GC 중에는 애플리케이션이 잠시 멈출 수 있다
---

## 전체 예제 코드

모든 예제는 GitHub `spring-cs-notes`의 [`jvm`](https://github.com/devHjlee/spring-cs-notes/tree/main/src/main/java/com/lhj/springcsnotes/jvm) 경로에서 확인할 수 있습니다.
