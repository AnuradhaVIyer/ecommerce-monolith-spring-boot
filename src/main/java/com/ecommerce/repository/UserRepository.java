package com.ecommerce.repository;

import java.util.Optional;

//User Repository
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByName(String name);
	Optional<Users> findByEmail(String email);
}