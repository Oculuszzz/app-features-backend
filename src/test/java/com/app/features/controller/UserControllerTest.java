/**
 * 
 */
package com.app.features.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.app.features.payload.AccessFeatureRequest;
import com.app.features.payload.AddUserRequest;
import com.app.features.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mokht
 *
 */
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userService;

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
	 * {@link com.app.features.controller.UserController#addNewUser(com.app.features.payload.AddUserRequest)}.
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 */
	@Test
	void testAddNewUser() throws JsonProcessingException, Exception {

		// given
		List<AccessFeatureRequest> lAccessFeatureRequest = new ArrayList<>();

		AccessFeatureRequest accessFeatureRequest1 = new AccessFeatureRequest();
		accessFeatureRequest1.setFeatureName(FEATURE_1);
		accessFeatureRequest1.setEnable(Boolean.TRUE);

		AccessFeatureRequest accessFeatureRequest2 = new AccessFeatureRequest();
		accessFeatureRequest2.setFeatureName(FEATURE_2);
		accessFeatureRequest2.setEnable(Boolean.TRUE);

		lAccessFeatureRequest.add(accessFeatureRequest1);
		lAccessFeatureRequest.add(accessFeatureRequest2);

		AddUserRequest request = new AddUserRequest();
		request.setEmail(USER_EMAIL);
		request.setAccessFeatures(lAccessFeatureRequest);

		String path = "/api/feature/user/add-new-user";

		// when
		// then
		mockMvc.perform(
				MockMvcRequestBuilders
				.post(path)
				.content(new ObjectMapper().writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

}
