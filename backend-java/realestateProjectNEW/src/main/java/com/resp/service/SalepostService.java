package com.resp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resp.model.Saleposts;
import com.resp.repository.SalepostRepo;

@Service
public class SalepostService {

    @Autowired
    private SalepostRepo repo;

    public Saleposts createProject(Saleposts projects) {
        return repo.save(projects);
    }

    public List<Saleposts> getAllProjects() {
        return repo.findAll();
    }

    public Optional<Saleposts> getProjectById(Long id) {
        return repo.findById(id);
    }

    public void deleteProjectById(Long id) {
        repo.deleteById(id);
    }

    public Saleposts updateProject(Saleposts salepost) {
        return repo.save(salepost);
    }
}
