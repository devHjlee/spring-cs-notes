package com.lhj.springcsnotes.basics.modifier;

public abstract class Payment {

    public abstract void validate();

    public void printReceipt() {
        System.out.println("공통 영수증 출력");
    }
}

