package com.lhj.springcsnotes.oop.characteristics;

/**
 * 캡슐화 : 필드를 private으로 감추고
 * public 메서드를 통해서만 접근할 수 있도록 제한
 */
public class PayRequest {
    private String userId;
    private int amount;

    public PayRequest(String userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }

    // 외부에서 userId 읽기만 가능 (직접 변경은 불가)
    public String getUserId() {
        return userId;
    }

    // 유효성 검사 포함 (0원 이하 금액은 허용되지 않음)
    public void setAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("결제 금액은 0보다 커야 합니다.");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
