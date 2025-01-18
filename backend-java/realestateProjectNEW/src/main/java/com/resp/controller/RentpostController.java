package com.resp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resp.model.Rentposts;
import com.resp.service.RentpostService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rentpost")
public class RentpostController {

    @Autowired
    private RentpostService service;

    @PostMapping("/Rinsert")
    public ResponseEntity<Rentposts> createRentpost(@RequestBody Rentposts rentpost) {
        Rentposts createdRentpost = service.createRentpost(rentpost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRentpost);
    }

    @GetMapping("/RgetAll")
    public ResponseEntity<List<Rentposts>> getAllRentposts() {
        List<Rentposts> rentposts = service.getAllRentposts();
        return ResponseEntity.ok(rentposts);
    }

    @GetMapping("/RgetById/{id}")
    public ResponseEntity<Rentposts> getRentpostById(@PathVariable long id) {
        Optional<Rentposts> rentpost = service.getRentpostById(id);
        return rentpost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/RdeleteById/{id}")
    public ResponseEntity<Void> deleteRentpostById(@PathVariable long id) {
        service.deleteRentpostById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Rupdate/{id}")
    public ResponseEntity<Rentposts> updateRentpost(@PathVariable long id, @RequestBody Rentposts rentpost) {
        Optional<Rentposts> updatedRentpost = service.updateRentpost(id, rentpost);
        return updatedRentpost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
