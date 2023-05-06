/**
 * 
 */
package com.app.features.service;

import org.springframework.stereotype.Service;

import com.app.features.model.FeatureEntity;
import com.app.features.model.UserEntity;
import com.app.features.model.UserFeatureEntity;
import com.app.features.payload.UpdateUserFeatureRequest;
import com.app.features.payload.AccessFeatureResponse;
import com.app.features.repository.FeatureRepository;
import com.app.features.repository.UserRepository;
import com.app.features.service.exception.FeatureException;
import com.app.features.service.exception.UpdateUserException;
import com.app.features.service.exception.UserException;

/**
 * A implementation class of FeaturesService.
 * 
 * @author mokht
 *
 */
@Service
public class FeaturesServiceImpl implements FeaturesService {

	/**
	 * The User Repository object.
	 */
	private final UserRepository userRepository;

	/**
	 * The Feature Repository object.
	 */
	private final FeatureRepository featureRepository;

	/**
	 * Class constructor.
	 * 
	 * @param userRepository
	 * @param featureRepository
	 */
	public FeaturesServiceImpl(UserRepository userRepository, FeatureRepository featureRepository) {
		this.userRepository = userRepository;
		this.featureRepository = featureRepository;
	}

	@Override
	public AccessFeatureResponse isUserCanAccessByFeatureName(String email, String featureName) {

		// Check user existing
		UserEntity userEntity = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserException("ERROR_USER_NOT_FOUND"));

		// Check feature existing
		if (Boolean.FALSE.equals(featureRepository.existsByFeatureName(featureName))) {

			// throw exception
			throw new FeatureException("ERROR_FEATURE_NOT_FOUND");

		}

		// Get requested accessible feature and return the response
		AccessFeatureResponse response = new AccessFeatureResponse();

		for (UserFeatureEntity accessibilityFeatureDetailsEntity : userEntity.getUserFeatures()) {

			if (accessibilityFeatureDetailsEntity.getFeature().getFeatureName().contentEquals(featureName)) {

				response.setCanAccess(accessibilityFeatureDetailsEntity.isEnable());
				break;

			}

		}

		return response;

	}

	@Override
	public void updateUserFeature(UpdateUserFeatureRequest request) {

		// Check user existing
		UserEntity userEntity = userRepository.findByEmail(request.getEmail()).orElseThrow(UpdateUserException::new);

		// Check feature existing
		if (Boolean.FALSE.equals(featureRepository.existsByFeatureName(request.getFeatureName()))) {

			// throw exception
			throw new UpdateUserException();

		}

		// Update user accessibility feature
		for (UserFeatureEntity accessibilityFeatureDetailsEntity : userEntity.getUserFeatures()) {

			if (accessibilityFeatureDetailsEntity.getFeature().getFeatureName().contentEquals(request.getFeatureName())) {

				accessibilityFeatureDetailsEntity.setEnable(request.getEnable());
				break;

			}

		}

		userRepository.save(userEntity);

	}

	@Override
	public void addFeature(String featureName) {

		FeatureEntity entity = new FeatureEntity(featureName);

		featureRepository.save(entity);

	}

}
