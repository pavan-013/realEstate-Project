package com.resp.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resp.model.Saleposts;

@Repository
public interface SalepostRepo extends JpaRepository<Saleposts, Long> {

	public Optional<Saleposts> findById(Long id);

}
