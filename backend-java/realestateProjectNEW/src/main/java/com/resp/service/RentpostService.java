package com.resp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resp.model.Rentposts;
import com.resp.repository.RentpostRepo;

@Service
public class RentpostService {

    @Autowired
    private RentpostRepo repo;

    public Rentposts createRentpost(Rentposts rentpost) {
        return repo.save(rentpost);
    }

    public List<Rentposts> getAllRentposts() {
        return repo.findAll();
    }

    public Optional<Rentposts> getRentpostById(long id) {
        return repo.findById(id);
    }

    public void deleteRentpostById(long id) {
        repo.deleteById(id);
    }

    public Optional<Rentposts> updateRentpost(long id, Rentposts rentpost) {
        if (repo.existsById(id)) {
            rentpost.setId(id); // Ensure the correct ID is set
            return Optional.of(repo.save(rentpost));
        }
        return Optional.empty();
    }
}
