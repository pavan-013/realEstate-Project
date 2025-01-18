package com.resp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resp.model.Rentposts;

public interface RentpostRepo extends JpaRepository<Rentposts,Long> {

}
