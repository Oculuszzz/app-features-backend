/**
 * 
 */
package com.app.features.payload;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * A Update User Feature data request.
 * 
 * @author mokht
 *
 */
public class UpdateUserFeatureRequest {

	/**
	 * Feature name.
	 */
	@NotBlank
	private String featureName;

	/**
	 * User email.
	 */
	@NotBlank
	private String email;

	/**
	 * The accessibility feature enable/disable.
	 */
	@NotNull
	private Boolean enable;

	/**
	 * @return the featureName
	 */
	public String getFeatureName() {
		return featureName;
	}

	/**
	 * @param featureName the featureName to set
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

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
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enable, featureName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateUserFeatureRequest other = (UpdateUserFeatureRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(enable, other.enable)
				&& Objects.equals(featureName, other.featureName);
	}

	@Override
	public String toString() {
		return "UserFeatureUpdateRequest [featureName=" + featureName + ", email=" + email + ", enable=" + enable + "]";
	}

}
