package com.resp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resp.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long> {

}
