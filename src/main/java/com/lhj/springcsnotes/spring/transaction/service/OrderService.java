package com.lhj.springcsnotes.spring.transaction.service;

import com.lhj.springcsnotes.spring.transaction.entity.Notification;
import com.lhj.springcsnotes.spring.transaction.entity.Order;
import com.lhj.springcsnotes.spring.transaction.repository.NotificationRepository;
import com.lhj.springcsnotes.spring.transaction.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;


    @Transactional
    public void orderWithTx(String item) {
        orderRepository.save(Order.builder()
                .item(item)
                .build());
        if (item.contains("fail")) throw new RuntimeException("알림 실패!");
        notificationRepository.save(Notification.builder()
                .message("주문 성공: " + item)
                .build());
    }

    public void orderWithoutTx(String item) {
        orderRepository.save(Order.builder()
                .item(item)
                .build());
        if (item.contains("fail")) throw new RuntimeException("알림 실패!");
        notificationRepository.save(Notification.builder()
                .message("주문 성공: " + item)
                .build());
    }

    @Transactional
    public void orderWithRuntimeEx(String item) {
        orderRepository.save(Order.builder()
                .item(item)
                .build());
        throw new RuntimeException("런타임 예외 발생");
    }

    @Transactional
    public void orderWithCheckedEx(String item) throws Exception {
        orderRepository.save(Order.builder()
                .item(item)
                .build());
        throw new Exception("체크 예외 발생");
    }

    @Transactional(rollbackFor = Exception.class)
    public void orderWithCheckedExRollback(String item) throws Exception {
        orderRepository.save(Order.builder()
                .item(item)
                .build());
        throw new Exception("체크 예외 롤백 테스트");
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public void orderWithNoRollbackRuntime(String item) {
        orderRepository.save(Order.builder()
                .item(item)
                .build());
        throw new RuntimeException("롤백되지 않도록 설정된 런타임 예외");
    }

    @Transactional
    public void orderWithRequiresNew(String item) {
        orderRepository.save(Order.builder()
                .item(item)
                .build());

        try {
            notificationService.send("주문 성공 알림: " + item);
        } catch (Exception e) {
            log.warn("알림 전송 실패 주문은 롤백 안 함: {}", e.getMessage());
        }

    }
}
