package com.personal.chronikale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.chronikale.entity.Role;

public interface BlogUserRoleRepository extends JpaRepository<Role, Integer> {
	
	
}
