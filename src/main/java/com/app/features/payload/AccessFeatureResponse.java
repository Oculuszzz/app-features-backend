/**
 * 
 */
package com.app.features.payload;

import java.util.Objects;

/**
 * A Access Feature response data class.
 * 
 * @author mokht
 *
 */
public class AccessFeatureResponse {

	/**
	 * The accessibility feature enable/disable.
	 */
	private Boolean canAccess = Boolean.FALSE;

	/**
	 * Class constructor.
	 */
	public AccessFeatureResponse() {
		super();
	}

	/**
	 * @param canAccess
	 */
	public AccessFeatureResponse(Boolean canAccess) {
		this.canAccess = canAccess;
	}

	/**
	 * @return the canAccess
	 */
	public Boolean getCanAccess() {
		return canAccess;
	}

	/**
	 * @param canAccess the canAccess to set
	 */
	public void setCanAccess(Boolean canAccess) {
		this.canAccess = canAccess;
	}

	@Override
	public int hashCode() {
		return Objects.hash(canAccess);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessFeatureResponse other = (AccessFeatureResponse) obj;
		return Objects.equals(canAccess, other.canAccess);
	}

	@Override
	public String toString() {
		return "FeaturesResponse [canAccess=" + canAccess + "]";
	}

}
