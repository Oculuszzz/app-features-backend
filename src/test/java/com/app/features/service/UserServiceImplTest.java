/**
 * 
 */
package com.app.features.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.features.model.FeatureEntity;
import com.app.features.model.UserEntity;
import com.app.features.model.UserFeatureEntity;
import com.app.features.payload.AccessFeatureRequest;
import com.app.features.payload.AddUserRequest;
import com.app.features.repository.FeatureRepository;
import com.app.features.repository.UserRepository;
import com.app.features.service.exception.FeatureException;

/**
 * @author mokht
 *
 */
@ExtendWith(MockitoExtension.class) // for JUnit 5
class UserServiceImplTest {

	private UserServiceImpl underTest;

	@Mock
	private UserRepository userRepository;

	@Mock
	private FeatureRepository featureRepository;

	private static final String NEW_USER_EMAIL = "alex@gmail.com";

	private static final String FEATURE_1 = "Feature 1";

	private static final String FEATURE_2 = "Feature 2";

	private List<AccessFeatureRequest> accessFeaturesRequest;

	private FeatureEntity expectedFeatureEntity1;
	private FeatureEntity expectedFeatureEntity2;

	private List<UserFeatureEntity> expectedUserFeatures;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		underTest = new UserServiceImpl(userRepository, featureRepository);

		// Listed available features for new user
		AccessFeatureRequest accessFeatureRequest1 = new AccessFeatureRequest();
		accessFeatureRequest1.setFeatureName(FEATURE_1);
		accessFeatureRequest1.setEnable(Boolean.TRUE);

		AccessFeatureRequest accessFeatureRequest2 = new AccessFeatureRequest();
		accessFeatureRequest2.setFeatureName(FEATURE_2);
		accessFeatureRequest2.setEnable(Boolean.TRUE);

		accessFeaturesRequest = new ArrayList<>();
		accessFeaturesRequest.add(accessFeatureRequest1);
		accessFeaturesRequest.add(accessFeatureRequest2);

		// List expected expectedUserFeatures
		UserFeatureEntity userFeatureEntity1 = new UserFeatureEntity();
		userFeatureEntity1.setEnable(Boolean.TRUE);
		userFeatureEntity1.setFeature(new FeatureEntity(FEATURE_1));

		UserFeatureEntity userFeatureEntity2 = new UserFeatureEntity();
		userFeatureEntity2.setEnable(Boolean.TRUE);
		userFeatureEntity2.setFeature(new FeatureEntity(FEATURE_2));

		expectedUserFeatures = new ArrayList<>();
		expectedUserFeatures.add(userFeatureEntity1);
		expectedUserFeatures.add(userFeatureEntity2);

		// Set expected return feature 1 and 2
		expectedFeatureEntity1 = new FeatureEntity();
		expectedFeatureEntity1.setFeatureName(FEATURE_1);

		expectedFeatureEntity2 = new FeatureEntity();
		expectedFeatureEntity2.setFeatureName(FEATURE_2);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.app.features.service.UserServiceImpl#addUser(com.app.features.payload.AddUserRequest)}.
	 */
	@Test
	void testAddUser() {

		// given
		AddUserRequest addUserRequest = new AddUserRequest();
		addUserRequest.setEmail(NEW_USER_EMAIL);
		addUserRequest.setAccessFeatures(accessFeaturesRequest);

		UserEntity expectedResult = new UserEntity();
		expectedResult.setId(null);
		expectedResult.setEmail(NEW_USER_EMAIL);
		expectedResult.setUserFeatures(new ArrayList<>());
		expectedResult.setUserFeatures(expectedUserFeatures);

		given(featureRepository.findByFeatureName(FEATURE_1)).willReturn(Optional.of(expectedFeatureEntity1));
		given(featureRepository.findByFeatureName(FEATURE_2)).willReturn(Optional.of(expectedFeatureEntity2));

		// when
		underTest.addUser(addUserRequest);

		// then
		ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
		verify(userRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository
		UserEntity result = userArgumentCaptor.getValue();

		assertThat(result).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
															// addUser()) and expected result.

	}

	/**
	 * Test method for
	 * {@link com.app.features.service.UserServiceImpl#addUser(com.app.features.payload.AddUserRequest)}.
	 */
	@Test
	void testAddUser_FeatureException() {

		// given
		AddUserRequest addUserRequest = new AddUserRequest();
		addUserRequest.setEmail(NEW_USER_EMAIL);
		addUserRequest.setAccessFeatures(accessFeaturesRequest);

		// when
		// then
		assertThatThrownBy(() -> underTest.addUser(addUserRequest)).isInstanceOf(FeatureException.class);
	}

}
