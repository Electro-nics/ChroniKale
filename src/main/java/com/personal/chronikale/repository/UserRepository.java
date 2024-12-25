package com.personal.chronikale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.personal.chronikale.entity.BlogUser;
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<BlogUser, Integer>{
boolean existsByEmail(String email);
boolean existsByPhoneNumber(String number);
Optional<BlogUser>findByEmail(String email);
}
