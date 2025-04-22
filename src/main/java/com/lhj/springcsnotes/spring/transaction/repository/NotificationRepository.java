package com.lhj.springcsnotes.spring.transaction.repository;

import com.lhj.springcsnotes.spring.transaction.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
