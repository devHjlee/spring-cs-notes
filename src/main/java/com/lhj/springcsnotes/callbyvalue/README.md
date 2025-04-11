# 💡 Java에서의 Call by Value (값에 의한 호출)

Java는 모든 메서드 인자 전달을 **Call by Value (값에 의한 호출)** 방식으로 처리합니다.  
하지만 **참조형 타입(Object)**을 전달할 때는, 객체의 **참조값(주소)**이 복사되어 전달되기 때문에  
처음 접할 땐 마치 Call by Reference처럼 느껴질 수 있습니다.

이 문서에서는 **기본형 타입과 참조형 타입이 Call by Value로 어떻게 동작하는지**  
예제와 함께 이해하기 쉽게 정리합니다.

---

## ✅ 기본형 타입 (Primitive Type)

```java
public class PrimitiveExample {

    // Call by Value Primitive(int) 예시
    public static void main(String[] args) {
        int a = 10;  // 스택 변수 a가 10이라는 값을 가짐

        change(a);   // a의 값 10이 복사되어 change(x)에 전달됨
        // x = 10 으로 시작됨 → x만 바뀌고 a는 그대로

        System.out.println("a = " + a);  // 결과: a = 10
    }

    public static void change(int x) {
        // x는 main()에서 넘긴 a의 값을 복사해서 받은 변수임
        // 즉, x = 10 (a의 값이 복사된 것)
        x = 100;  // 복사된 x만 바꾸는 거지 a에는 아무 영향 없음
    }
}
```

---

## ✅ 참조형 타입 (Reference Type)

```java
public class ReferenceExample {

    // Call by Value Reference 예시
    public static void main(String[] args) {
        Book myBook = new Book();     // 스택에 myBook 이 생성되고 힙영역에 Book 객체 생성됨 → 주소 myBook@0x100
        myBook.title = "Java Basics"; // 초기값 설정

        change(myBook);               // myBook의 참조값(0x100)을 복사해서 change()로 전달
        // 즉, change(book)에서 book = 0x100 (Call by Value)

        System.out.println(myBook.title); // 결과: "Clean Code"
        // 이유: book.title = "Clean Code"가 힙의 동일 객체에 반영됨
        // 이후 book = new Book() book@0x200 은 myBook@0x100 과 무관
    }

    public static void change(Book book) {
        // 스택 book → 0x100 (myBook이 가리키는 힙 주소 복사됨)
        // 힙  0x100 → Book 객체(title="Java Basics")

        book.title = "Clean Code"; // 힙에 있는 객체의 필드 변경됨 (title="Clean Code")

        book = new Book();         // 새로운 Book 객체 생성됨 (힙에 새 주소 예: 0x200)
        // 스택 book은 이제 0x200을 가리킴 (스택 myBook과는 별개)

        book.title = "Effective Java"; // 새로 생성된 book@0x200의 필드 수정됨
        // 기존 myBook이 가리키는 객체 myBook@0x100 는 그대로 유지됨
    }
}

class Book {
    String title;
}
```

---

## ✅ 메모리 흐름 요약

| 시점                     | `myBook` (main) | `book` (change) | 힙 상태 |
|--------------------------|------------------|------------------|----------|
| 초기                     | `0x100`          | -                | Book@0x100 (title="Java Basics") |
| change 호출 직후         | `0x100`          | `0x100`          | 같은 객체 |
| book.title 변경 후       | `0x100`          | `0x100`          | title = "Clean Code" |
| book = new Book()        | `0x100`          | `0x200`          | 새 객체 생성됨 |
| book.title = "Effective Java" | `0x100`   | `0x200`          | 기존 객체는 그대로 |
| change 종료 후           | `0x100`          | (스택에서 사라짐) | `myBook`은 그대로 유지됨 |

---

## ✅ 핵심 요약

| 항목               | Call by Value in Java |
|--------------------|------------------------|
| 전달 방식           | 값을 복사해서 전달 |
| 기본형 타입         | 값 자체가 복사됨 |
| 참조형 타입         | 객체의 참조값(주소)이 복사됨 |
| 객체 내부 수정      | ✅ 원본 객체 영향 O |
| 참조 재할당         | ❌ 원본 참조에는 영향 없음 |

---

## 📝 정리

> Java는 항상 Call by Value(값에 의한 호출)를 사용하며,  
> 참조형 객체를 넘길 경우에도 객체 자체가 아닌 **참조값(주소)의 복사본**이 전달됩니다.  
> 따라서 객체 내부를 수정하면 원본 객체에 반영되지만, 객체 자체를 새로 할당해도 원본에는 영향을 주지 않습니다.
