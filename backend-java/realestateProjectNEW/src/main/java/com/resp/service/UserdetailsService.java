package com.resp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resp.model.UserDetails;
import com.resp.repository.UserdetailsRepo;

@Service
public class UserdetailsService {

    @Autowired
    private UserdetailsRepo repo;

    public UserDetails createUserdetails(UserDetails userdetails) {
        return repo.save(userdetails);
    }

    public Optional<UserDetails> getUserdetailsById(long id) {
        return repo.findById(id);
    }

    public UserDetails updateUserdetails(long id, UserDetails userdetails) {
        // Business logic can be added here for validation or other checks
        return repo.save(userdetails);
    }
}
