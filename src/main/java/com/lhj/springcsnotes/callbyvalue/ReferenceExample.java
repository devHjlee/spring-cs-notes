package com.lhj.springcsnotes.callbyvalue;

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