 package com.resp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resp.model.Notification;
import com.resp.service.NotificationService;

@RestController
@RequestMapping("/notifpost")
@CrossOrigin("*")
public class NotifController {

	@Autowired
    private NotificationService service;

    @PostMapping("/Ninsert")
    public Notification createNotification(@RequestBody Notification notification) {
        return service.createNotification(notification);
    }

    @GetMapping("/NgetAll")
    public List<Notification> getAllNotifications() {
        return service.getAllNotifications();
    }

    @DeleteMapping("/NdeleteById/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable long id) {
        service.deleteNotificationById(id);
        return ResponseEntity.noContent().build();
    }

}
