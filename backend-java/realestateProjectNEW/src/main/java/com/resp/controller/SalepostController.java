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

import com.resp.model.Saleposts;
import com.resp.service.SalepostService;

@RestController
@CrossOrigin("*")
@RequestMapping("/salepost")
public class SalepostController {

    @Autowired
    private SalepostService service;

    @PostMapping("/Sinsert")
    public ResponseEntity<Saleposts> createProjects(@RequestBody Saleposts salepost) {
    	Saleposts createdSalepost = service.createProject(salepost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSalepost);
    }

    @GetMapping("/SgetAll")
    public List<Saleposts> getProjects() {
        return service.getAllProjects();
    }

    @GetMapping("/SgetById/{id}")
    public Optional<Saleposts> getProjectById(@PathVariable Long id) {
        return service.getProjectById(id);
    }

    @DeleteMapping("/SdeleteById/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        service.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Supdate/{id}")
    public Saleposts updateProject(@PathVariable long id, @RequestBody Saleposts salepost) {
        // Ensure the ID in the path matches the ID in the request body
        salepost.setId(id);
        return service.updateProject(salepost);
    }
}