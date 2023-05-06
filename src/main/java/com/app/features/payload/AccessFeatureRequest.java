/**
 * 
 */
package com.app.features.payload;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * A Access Feature request data class.
 * 
 * @author mokht
 *
 */
public class AccessFeatureRequest {

	/**
	 * Feature name.
	 */
	@NotBlank
	private String featureName;

	/**
	 * Accessibility feature enable/disable.
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
	 * @return the enable
	 */
	public Boolean isEnable() {
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
		return Objects.hash(enable, featureName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessFeatureRequest other = (AccessFeatureRequest) obj;
		return Objects.equals(enable, other.enable) && Objects.equals(featureName, other.featureName);
	}

	@Override
	public String toString() {
		return "AccessFeatureRequest [featureName=" + featureName + ", enable=" + enable + "]";
	}

}
