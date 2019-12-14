package com.prodapt.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.app.model.dto.ApiResponse;
import com.prodapt.app.model.prodapt.Users;
import com.prodapt.app.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * @author arunkumar.a
 *
 */
@RestController
public class UsersController {
	
	@Autowired
	UserService userService;
	
	
	@ApiOperation(value = "Find all users",notes = "Retrieves list of users from prodapt.users table")
	@GetMapping("/v1/getAllUsers")
	public List<Users> getAllUsers() {
		return userService.getAllUsersFromRepo();
	}
	
	@ApiOperation(value = "Find a user",notes = "Retrieve a user from prodapt.users table")
	@GetMapping("/v1/getUser/{id}")
	public ResponseEntity<?> getUsersId(
			@PathVariable("id") Integer id) 
	{
		return new ResponseEntity<>(userService.getUsersByIdFromRepo(id),HttpStatus.OK);
	}
	
	
	/**
	 * @author arunkumar.a
	 *
	 * Using PUT method since the id of the user is not generated by server or database and it is passed by client application
	 */
	@ApiOperation(value = "Save a list of users",notes = "Saves a list of users in prodapt.users table")
	@PutMapping("/v1/SaveUsers")
	public ResponseEntity<?> saveUsers(
			@RequestBody List<Users> users) 
	{
		ResponseEntity<ApiResponse> apiResp = userService.saveUsersInRepo(users);
		
		return apiResp;
		
	}
	
	@ApiOperation(value = "Deletes a user",notes = "Deletes a user from prodapt.users table")
	@DeleteMapping("/v1/deleteUser/{id}")
	public ResponseEntity<?> deleteUserById(
			@PathVariable("id") Integer id) 
	{		
		ResponseEntity<ApiResponse> apiResp = userService.deleteUserByIdFromRepo(id);
		
		return apiResp;
	}
	

}
