package com.resp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resp.model.Admin;
import com.resp.repository.AdminRepo;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    public Admin addAdmin(Admin admin) {
        return repo.save(admin);
    }
    
    public List<Admin> getAllAdmins() {
        return repo.findAll();
    }
   
    public String deleteAdmin(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Admin with ID " + id + " deleted successfully.";
        } else {
            return "Admin with ID " + id + " not found.";
        }
    }
}

