package com.user.controller;

import java.util.List;

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

import com.user.dto.UserDto;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService){
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto saveUser = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
		UserDto userDto = userService.getUserById(id);
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
		UserDto updatedUser = userService.updateUser(id,userDto);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/id/{id}/remove")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.ok("USer Deleted Sucessfully");
	}
	
}
