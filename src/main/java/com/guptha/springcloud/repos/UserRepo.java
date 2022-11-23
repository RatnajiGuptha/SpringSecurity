package com.guptha.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guptha.springcloud.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
