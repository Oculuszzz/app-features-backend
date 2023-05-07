/**
 * 
 */
package com.app.features.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.features.payload.AccessFeatureResponse;
import com.app.features.payload.UpdateUserFeatureRequest;
import com.app.features.service.FeaturesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mokht
 *
 */
@WebMvcTest(controllers = FeaturesController.class)
class FeaturesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FeaturesServiceImpl featuresService;

	private static final String USER_EMAIL = "alex@gmail.com";

	private static final String FEATURE_1 = "Feature 1";

	private static final String FEATURE_2 = "Feature 2";

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

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.app.features.controller.FeaturesController#userAccessFeature(java.lang.String, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testUserAccessFeature() throws Exception {

		// given
		AccessFeatureResponse responseBody = new AccessFeatureResponse();
		responseBody.setCanAccess(Boolean.TRUE);

		given(featuresService.isUserCanAccessByFeatureName(USER_EMAIL, FEATURE_1)).willReturn(responseBody);

		String path = String.format("/api/feature?email=%s&featureName=%s", USER_EMAIL, FEATURE_1);

		// when
		// then
		mockMvc.perform(get(path)).andExpect(status().isOk()).andExpect(jsonPath("$.canAccess", is(true)));

	}

	/**
	 * Test method for
	 * {@link com.app.features.controller.FeaturesController#updateUser(com.app.features.payload.UpdateUserFeatureRequest)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testUpdateUser() throws Exception {

		// given
		UpdateUserFeatureRequest request = new UpdateUserFeatureRequest();
		request.setEmail(USER_EMAIL);
		request.setEnable(Boolean.TRUE);
		request.setFeatureName(FEATURE_1);

		String path = "/api/feature/update-user";

		// when
		// then
		mockMvc.perform(MockMvcRequestBuilders
				.put(path)
				.content(new ObjectMapper().writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	/**
	 * Test method for
	 * {@link com.app.features.controller.FeaturesController#addNewFeature(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testAddNewFeature() throws Exception {

		// given
		String path = String.format("/api/feature/add-new-feature?%s", FEATURE_2);

		// when
		// then
		mockMvc.perform(post(path)).andExpect(status().isOk());

	}

}
