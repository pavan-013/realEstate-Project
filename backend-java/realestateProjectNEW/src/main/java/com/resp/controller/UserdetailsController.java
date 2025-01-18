package com.resp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.resp.model.UserDetails;
//import com.resp.repository.UserRepo;
import com.resp.service.UserdetailsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/userdetails")
public class UserdetailsController {

	@Autowired
    private UserdetailsService service;

    @PostMapping("/UDinsert")
    public UserDetails createUserdetails(@RequestBody UserDetails userdetails) {
        return service.createUserdetails(userdetails);
    }

    @GetMapping("/UDgetById/{id}")
    public Optional<UserDetails> getUserdetailsById(@PathVariable long id) {
        return service.getUserdetailsById(id);
    }

    @PutMapping("/UDupdate/{id}")
    public ResponseEntity<UserDetails> updateUserdetails(@PathVariable long id, @RequestBody UserDetails userdetails) {
        UserDetails updatedDetails = service.updateUserdetails(id, userdetails);
        return ResponseEntity.ok(updatedDetails);
    }
}
