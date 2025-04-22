package com.lhj.springcsnotes.spring.transaction.service;

import com.lhj.springcsnotes.spring.transaction.entity.Notification;
import com.lhj.springcsnotes.spring.transaction.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void send(String message) {
        notificationRepository.save(Notification.builder()
                .message(message)
                .build());

        if (message.contains("fail")) {
            throw new RuntimeException("알림 전송 실패");
        }

    }
}
