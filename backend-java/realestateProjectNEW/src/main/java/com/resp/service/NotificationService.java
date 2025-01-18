package com.resp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resp.model.Notification;
import com.resp.repository.NotificationRepo;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo repo;

    public Notification createNotification(Notification notification) {
        return repo.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return repo.findAll();
    }

    public void deleteNotificationById(long id) {
        repo.deleteById(id);
    }
}
