/**
 * 
 */
package com.app.features.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.features.model.FeatureEntity;

/**
 * @author mokht
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FeatureRepositoryTest {

	@Autowired
	private FeatureRepository featureRepository;

	private FeatureEntity feature;

	private static final String REAL_FEATURE_NAME = "Add to Cart";

	private static final String FAKE_FEATURE_NAME = "Fake Feature";

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

		feature = new FeatureEntity(REAL_FEATURE_NAME);

		featureRepository.save(feature);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.app.features.repository.FeatureRepository#existsByFeatureName(java.lang.String)}.
	 */
	@Test
	void testExistsByFeatureName() {

		assertTrue(featureRepository.existsByFeatureName(REAL_FEATURE_NAME));

	}

	/**
	 * Test method for
	 * {@link com.app.features.repository.FeatureRepository#existsByFeatureName(java.lang.String)}.
	 */
	@Test
	void testExistsByFeatureName_notFound() {

		assertFalse(featureRepository.existsByFeatureName(FAKE_FEATURE_NAME));

	}

	/**
	 * Test method for
	 * {@link com.app.features.repository.FeatureRepository#findByFeatureName(java.lang.String)}.
	 */
	@Test
	void testFindByFeatureName() {

		assertThat(featureRepository.findByFeatureName(REAL_FEATURE_NAME)).isPresent();

	}

	/**
	 * Test method for
	 * {@link com.app.features.repository.FeatureRepository#findByFeatureName(java.lang.String)}.
	 */
	@Test
	void testFindByFeatureName_null() {

		assertThat(featureRepository.findByFeatureName(FAKE_FEATURE_NAME)).isNotPresent();

	}

}
