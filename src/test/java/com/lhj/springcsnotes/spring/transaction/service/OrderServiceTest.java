package com.lhj.springcsnotes.spring.transaction.service;

import com.lhj.springcsnotes.spring.transaction.repository.NotificationRepository;
import org.junit.jupiter.api.Test;

import com.lhj.springcsnotes.spring.transaction.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @BeforeEach
    void clearDB() {
        orderRepository.deleteAll();
    }

    @Test
    void 트랜잭션_없는_주문_실패시_주문은_남음() {
        try {
            orderService.orderWithoutTx("item-fail");
        } catch (Exception e) {
            System.out.println("예외 발생 (트랜잭션 없음): " + e.getMessage());
        }

        assertThat(orderRepository.findAll()).hasSize(1); // 주문은 저장됨
    }

    @Test
    void 트랜잭션_있는_주문_실패시_롤백됨() {
        try {
            orderService.orderWithTx("item-fail");
        } catch (Exception e) {
            System.out.println("예외 발생 (트랜잭션 있음): " + e.getMessage());
        }

        assertThat(orderRepository.findAll()).isEmpty(); // 롤백됨
    }

    @Test
    void 런타임예외는_기본적으로_롤백된다() {
        try {
            orderService.orderWithRuntimeEx("런타임");
        } catch (Exception e) {
            System.out.println("런타임 예외 발생: " + e.getMessage());
        }

        assertThat(orderRepository.findAll()).isEmpty(); // 롤백됨
    }

    @Test
    void 체크예외는_기본적으로_롤백되지_않는다() {
        try {
            orderService.orderWithCheckedEx("체크예외");
        } catch (Exception e) {
            System.out.println("체크 예외 발생: " + e.getMessage());
        }

        assertThat(orderRepository.findAll()).hasSize(1); // 롤백 안됨
    }

    @Test
    void 체크예외도_rollbackFor_설정시_롤백된다() {
        try {
            orderService.orderWithCheckedExRollback("체크예외-롤백");
        } catch (Exception e) {
            System.out.println("체크 예외 롤백 발생: " + e.getMessage());
        }

        assertThat(orderRepository.findAll()).isEmpty(); // 롤백됨
    }

    @Test
    void noRollbackFor_설정시_런타임예외도_롤백되지_않는다() {
        try {
            orderService.orderWithNoRollbackRuntime("런타임-롤백X");
        } catch (Exception e) {
            System.out.println("런타임 예외 발생 (noRollbackFor): " + e.getMessage());
        }

        assertThat(orderRepository.findAll()).hasSize(1); // 롤백 안됨
    }

    @Test
    void REQUIRES_NEW_전파_테스트() {
        orderService.orderWithRequiresNew("item-fail");

        // 주문은 저장되어야 한다
        assertThat(orderRepository.findAll()).hasSize(1);

        // 알림은 저장 안 됨 (REQUIRES_NEW 트랜잭션 롤백)
        assertThat(notificationRepository.findAll()).isEmpty();
    }
}
