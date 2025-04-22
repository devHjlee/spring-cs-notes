package com.lhj.springcsnotes.spring.transaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private String message;

    @Builder
    public Notification(String message) {
        this.message = message;
    }
}
