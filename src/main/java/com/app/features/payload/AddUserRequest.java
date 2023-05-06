/**
 * 
 */
package com.app.features.payload;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * A Add User request data class.
 * 
 * @author mokht
 *
 */
public class AddUserRequest {

	/**
	 * User email/username.
	 */
	@NotBlank
	String email;

	/**
	 * List of accessibility features.
	 */
	@NotEmpty
	List<AccessFeatureRequest> accessFeatures;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the accessFeatures
	 */
	public List<AccessFeatureRequest> getAccessFeatures() {
		return accessFeatures;
	}

	/**
	 * @param accessFeatures the accessFeatures to set
	 */
	public void setAccessFeatures(List<AccessFeatureRequest> accessFeatures) {
		this.accessFeatures = accessFeatures;
	}

}
