package com.guptha.springcloud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guptha.springcloud.model.User;
import com.guptha.springcloud.repos.UserRepo;

@RestController
public class UsersRestController {

	@Autowired
	private UserRepo userRepo;

	@GetMapping("/allUsers")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
}
