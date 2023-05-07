/**
 * 
 */
package com.app.features.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.features.model.UserEntity;

/**
 * @author mokht
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private UserEntity user;

	private static final String REAL_USER_EMAIL = "alex@gmail.com";

	private static final String FAKE_USER_EMAIL = "fake@gmail.com";

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

		user = new UserEntity();
		user.setEmail(REAL_USER_EMAIL);
		user.setUserFeatures(new ArrayList<>());

		userRepository.save(user);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.app.features.repository.UserRepository#findByEmail(java.lang.String)}.
	 */
	@Test
	void testFindByEmail() {

		assertThat(userRepository.findByEmail(REAL_USER_EMAIL)).isPresent();

	}

	/**
	 * Test method for
	 * {@link com.app.features.repository.UserRepository#findByEmail(java.lang.String)}.
	 */
	@Test
	void testFindByEmail_null() {

		assertThat(userRepository.findByEmail(FAKE_USER_EMAIL)).isNotPresent();

	}

}
