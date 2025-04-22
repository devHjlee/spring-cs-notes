package com.lhj.springcsnotes.spring.transaction.repository;

import com.lhj.springcsnotes.spring.transaction.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
