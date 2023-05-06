/**
 * 
 */
package com.app.features.service;

import com.app.features.payload.UpdateUserFeatureRequest;
import com.app.features.payload.AccessFeatureResponse;

/**
 * A class where responsible to handle the business logic and requirements.
 * 
 * @author mokht
 *
 */
public interface FeaturesService {

	/**
	 * Return the UserAccessFeatureResponse object where consist information of the
	 * specific user had accessibility to feature.
	 * 
	 * @param email
	 * @param featureName
	 * @return
	 */
	public AccessFeatureResponse isUserCanAccessByFeatureName(String email, String featureName);

	/**
	 * Update accessibility to a specific feature handler.
	 * 
	 * @param request
	 */
	public void updateUserFeature(UpdateUserFeatureRequest request);

	/**
	 * Add new feature type handler.
	 * 
	 * @param featureName
	 */
	public void addFeature(String featureName);

}
