package org.mma.training.java.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mma.training.java.spring.model.User;
import org.mma.training.java.spring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			userRepository.findAll().forEach(users::add);
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUsersById(@PathVariable("id") long id) {
		Optional<User> usersData = userRepository.findById(id);

		if (usersData.isPresent()) {
			return new ResponseEntity<>(usersData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    @PostMapping(value = "/add")
	public ResponseEntity<User> postUser(@RequestBody User user) {
    	
		try {
			User userData = userRepository.save(user);
			return new ResponseEntity<>(userData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
    
    @PutMapping("/update")
    public ResponseEntity<User> updateUserById(@RequestBody User userData) {
    	try {
    		User user = new User();
    		BeanUtils.copyProperties(userData, user);
    		System.out.println(user.getUserId());
    		System.out.println(user.getFirstName());
    		System.out.println(user.getLastName());
    		System.out.println(user.getPhone());
    		System.out.println(user.getEmail());
    		System.out.println(user.getAddress().getAddressId());
    		System.out.println(user.getAddress().getCity());
    		userRepository.save(user);
    		return new ResponseEntity<User>(user, HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    	}
    }
    
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
    	System.out.println("Deleting id -> " + id);
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
    
    @DeleteMapping("/delete-all")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
    	System.out.println("Deleting all users");
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
