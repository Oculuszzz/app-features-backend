package com.app.features.service;

import com.app.features.payload.AddUserRequest;

/**
 * * A class where responsible to handle the business logic and requirements.
 * 
 * @author mokht
 *
 */
public interface UserService {

	/**
	 * Add new user handler.
	 * 
	 * @param request
	 */
	public void addUser(AddUserRequest request);

}
