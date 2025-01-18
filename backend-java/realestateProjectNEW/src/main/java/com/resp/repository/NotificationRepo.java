package com.resp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resp.model.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long> {

}
