/**
 * 
 */
package com.app.features.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.app.features.payload.AccessFeatureResponse;
import com.app.features.payload.UpdateUserFeatureRequest;
import com.app.features.repository.FeatureRepository;
import com.app.features.repository.UserRepository;
import com.app.features.service.exception.FeatureException;
import com.app.features.service.exception.UpdateUserException;
import com.app.features.service.exception.UserException;

/**
 * @author mokht
 *
 */
@ExtendWith(MockitoExtension.class) // for JUnit 5
class FeaturesServiceImplTest {

	private FeaturesServiceImpl underTest;

	@Mock
	private UserRepository userRepository;

	@Mock
	private FeatureRepository featureRepository;

	private UserEntity currentUserEntityValue1;

	private List<UserFeatureEntity> currentListUserFeatureEntityValue1;

	private static final String USER_EMAIL = "alex@gmail.com";

	private static final String FEATURE_1 = "Feature 1";

	private static final String FEATURE_2 = "Feature 2";

	private static final String NEW_FEATURE_1 = "New Feature 1";

	private static final String FAKE_FEATURE_1 = "Fake Feature 1";

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

		underTest = new FeaturesServiceImpl(userRepository, featureRepository);

		currentListUserFeatureEntityValue1 = new ArrayList<>();
		currentListUserFeatureEntityValue1.add(new UserFeatureEntity(Boolean.TRUE, new FeatureEntity(FEATURE_1)));
		currentListUserFeatureEntityValue1.add(new UserFeatureEntity(Boolean.FALSE, new FeatureEntity(FEATURE_2)));

		currentUserEntityValue1 = new UserEntity();
		currentUserEntityValue1.setEmail(USER_EMAIL);
		currentUserEntityValue1.setUserFeatures(currentListUserFeatureEntityValue1);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.app.features.service.FeaturesServiceImpl#isUserCanAccessByFeatureName(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testIsUserCanAccessByFeatureName() {

		// given
		given(userRepository.findByEmail(USER_EMAIL)).willReturn(Optional.of(currentUserEntityValue1));
		given(featureRepository.existsByFeatureName(FEATURE_1)).willReturn(Boolean.TRUE);
		given(featureRepository.existsByFeatureName(FEATURE_2)).willReturn(Boolean.TRUE);

		// when
		// then
		assertThat(underTest.isUserCanAccessByFeatureName(USER_EMAIL, FEATURE_1))
				.isInstanceOf(AccessFeatureResponse.class);
		assertThat(underTest.isUserCanAccessByFeatureName(USER_EMAIL, FEATURE_2))
				.isInstanceOf(AccessFeatureResponse.class);

		AccessFeatureResponse responseFeature1 = underTest.isUserCanAccessByFeatureName(USER_EMAIL, FEATURE_1);
		assertTrue(responseFeature1.getCanAccess());

		AccessFeatureResponse responseFeature2 = underTest.isUserCanAccessByFeatureName(USER_EMAIL, FEATURE_2);
		assertFalse(responseFeature2.getCanAccess());

	}

	/**
	 * Test method for
	 * {@link com.app.features.service.FeaturesServiceImpl#isUserCanAccessByFeatureName(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testIsUserCanAccessByFeatureName_UserException() {

		// given

		// when
		// then
		assertThatThrownBy(() -> underTest.isUserCanAccessByFeatureName(USER_EMAIL, FEATURE_1))
				.isInstanceOf(UserException.class).hasMessage("ERROR_USER_NOT_FOUND");

	}

	/**
	 * Test method for
	 * {@link com.app.features.service.FeaturesServiceImpl#isUserCanAccessByFeatureName(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testIsUserCanAccessByFeatureName_FeatureException() {

		// given
		given(userRepository.findByEmail(USER_EMAIL)).willReturn(Optional.of(currentUserEntityValue1));

		// when
		// then
		assertThatThrownBy(() -> underTest.isUserCanAccessByFeatureName(USER_EMAIL, FAKE_FEATURE_1))
				.isInstanceOf(FeatureException.class).hasMessage("ERROR_FEATURE_NOT_FOUND");

	}

	/**
	 * Test method for
	 * {@link com.app.features.service.FeaturesServiceImpl#updateUserFeature(com.app.features.payload.UpdateUserFeatureRequest)}.
	 */
	@Test
	void testUpdateUserFeature() {

		// given
		UserEntity currentUserValue1 = currentUserEntityValue1; // Feature 1 = True, Feature 2 = False.
		UserEntity expectedUpdatedUserValue1 = currentUserEntityValue1; // Expected value, Feature 1 = True, Feature 2 =
																		// True.

		for (UserFeatureEntity entity : expectedUpdatedUserValue1.getUserFeatures()) {

			if (entity.getFeature().getFeatureName().contentEquals(FEATURE_2)) {

				entity.setEnable(Boolean.TRUE);
				break;

			}

		}

		UpdateUserFeatureRequest request = new UpdateUserFeatureRequest(); // Request modified Feature 2 = True.
		request.setFeatureName(FEATURE_2);
		request.setEmail(USER_EMAIL);
		request.setEnable(Boolean.TRUE);

		given(userRepository.findByEmail(USER_EMAIL)).willReturn(Optional.of(currentUserValue1));
		given(featureRepository.existsByFeatureName(FEATURE_2)).willReturn(Boolean.TRUE);

		// when
		underTest.updateUserFeature(request);

		// then
		ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
		verify(userRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository
		UserEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedUpdatedUserValue1); // Compare result between ArgumentCaptor(after call
																	// updateUserFeature()) and expected result.

	}

	/**
	 * Test method for
	 * {@link com.app.features.service.FeaturesServiceImpl#updateUserFeature(com.app.features.payload.UpdateUserFeatureRequest)}.
	 */
	@Test
	void testUpdateUserFeature_userNotExist_UpdateUserException() {

		// given
		UpdateUserFeatureRequest request = new UpdateUserFeatureRequest(); // Request modified Feature 2 = True.
		request.setFeatureName(FEATURE_2);
		request.setEmail(USER_EMAIL);
		request.setEnable(Boolean.TRUE);

		// when
		// then
		assertThatThrownBy(() -> underTest.updateUserFeature(request)).isInstanceOf(UpdateUserException.class);

	}

	/**
	 * Test method for
	 * {@link com.app.features.service.FeaturesServiceImpl#updateUserFeature(com.app.features.payload.UpdateUserFeatureRequest)}.
	 */
	@Test
	void testUpdateUserFeature_featureNotExist_UpdateUserException() {

		// given
		UpdateUserFeatureRequest request = new UpdateUserFeatureRequest(); // Request modified Feature 2 = True.
		request.setFeatureName(FEATURE_2);
		request.setEmail(USER_EMAIL);
		request.setEnable(Boolean.TRUE);

		given(userRepository.findByEmail(USER_EMAIL)).willReturn(Optional.of(currentUserEntityValue1));

		// when
		// then
		assertThatThrownBy(() -> underTest.updateUserFeature(request)).isInstanceOf(UpdateUserException.class);

	}

	/**
	 * Test method for
	 * {@link com.app.features.service.FeaturesServiceImpl#addFeature(java.lang.String)}.
	 */
	@Test
	void testAddFeature() {

		// given
		FeatureEntity expectedResponse = new FeatureEntity(NEW_FEATURE_1);

		// when
		underTest.addFeature(NEW_FEATURE_1);

		// then
		ArgumentCaptor<FeatureEntity> userArgumentCaptor = ArgumentCaptor.forClass(FeatureEntity.class);
		verify(featureRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																		// value in repository
		FeatureEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedResponse);
	}

}
