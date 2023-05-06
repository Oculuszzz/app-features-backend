/**
 * 
 */
package com.app.features.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.features.payload.AddUserRequest;
import com.app.features.payload.MessageResponse;
import com.app.features.service.UserServiceImpl;

/**
 * A controller class which specific to User API.
 * 
 * @author mokht
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/feature/user")
public class UserController {

	/**
	 * The User Service object.
	 */
	private final UserServiceImpl userService;

	/**
	 * Class constructor.
	 * 
	 * @param userService
	 */
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	/**
	 * Return the ResponseEntity object that consist information of create new user
	 * response.
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "add-new-user")
	public ResponseEntity<MessageResponse> addNewUser(@RequestBody AddUserRequest request) {

		userService.addUser(request);

		return ResponseEntity
				.ok(new MessageResponse(String.format("Successfully created new user - %s", request.getEmail())));

	}

}
