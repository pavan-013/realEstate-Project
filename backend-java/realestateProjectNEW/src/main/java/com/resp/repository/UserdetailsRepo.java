package com.resp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resp.model.UserDetails;

public interface UserdetailsRepo extends JpaRepository<UserDetails, Long> {

	public Optional<UserDetails> findById(Long id);
}
