/**
 * 
 */
package com.app.features.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.app.features.model.FeatureEntity;
import com.app.features.model.UserEntity;
import com.app.features.model.UserFeatureEntity;
import com.app.features.payload.AccessFeatureRequest;
import com.app.features.payload.AddUserRequest;
import com.app.features.repository.FeatureRepository;
import com.app.features.repository.UserRepository;
import com.app.features.service.exception.FeatureException;

/**
 * A implementation of User Service class.
 * 
 * @author mokht
 *
 */
@Service
public class UserServiceImpl implements UserService {

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
	public UserServiceImpl(UserRepository userRepository, FeatureRepository featureRepository) {
		this.userRepository = userRepository;
		this.featureRepository = featureRepository;
	}

	@Override
	public void addUser(AddUserRequest request) {

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(request.getEmail());
		userEntity.setUserFeatures(new ArrayList<>());

		for (AccessFeatureRequest featureRequest : request.getAccessFeatures()) {

			FeatureEntity featureEntity = featureRepository.findByFeatureName(featureRequest.getFeatureName())
					.orElseThrow(FeatureException::new);

			UserFeatureEntity accessibilityFeatureDetailsEntity = new UserFeatureEntity(featureRequest.isEnable(),
					featureEntity);
			userEntity.getUserFeatures().add(accessibilityFeatureDetailsEntity);

		}

		userRepository.save(userEntity);

	}

}
