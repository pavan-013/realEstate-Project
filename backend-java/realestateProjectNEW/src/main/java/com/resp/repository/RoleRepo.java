package com.resp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resp.model.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String name);
	

}