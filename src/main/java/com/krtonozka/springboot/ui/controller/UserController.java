package com.krtonozka.springboot.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krtonozka.springboot.ui.controller.model.request.UserDetailsRequest;
import com.krtonozka.springboot.ui.controller.model.request.UserDetailsUpdateRequest;
import com.krtonozka.springboot.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	Map<String, UserRest> users;

	@GetMapping
	public String getUsers(
			@RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "50") int limit,
			@RequestParam(value="sort", required = false) String sort
			)	
	{
		return "get users was called";
	}
	
	
	@GetMapping(
			path="/{userId}",
			produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> getUser(
			@PathVariable String userId)	{
	
		if (users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
			)
	public ResponseEntity<UserRest> createUser(
			@Valid @RequestBody UserDetailsRequest userDetails) {
						
		UserRest user = new UserRest();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setUserId(UUID.randomUUID().toString());
		
		if (users == null) users = new HashMap<>();
		users.put(user.getUserId(), user);
		
		return new ResponseEntity<UserRest>(user, HttpStatus.OK);
	}
	
	
	@PutMapping(
			path="/{userId}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
			)
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @RequestBody UserDetailsUpdateRequest userUpdate) {
		if (users == null) users = new HashMap<>();
		
		if (users.containsKey(userId)) {
			UserRest user = users.get(userId);
			user.setFirstName(userUpdate.getFirstName());
			user.setLastName(userUpdate.getLastName());
			users.put(user.getUserId(), user);
			return new ResponseEntity<UserRest>(user, HttpStatus.OK);
		} else {			
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@DeleteMapping(
			path="/{userId}"
			)	
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		if (users == null) users = new HashMap<>();
		
		UserRest result = users.remove(userId);
		return result == null ?
				new ResponseEntity<Void>(HttpStatus.NOT_FOUND) :
				new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
