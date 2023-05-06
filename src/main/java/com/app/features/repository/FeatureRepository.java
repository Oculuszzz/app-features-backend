/**
 * 
 */
package com.app.features.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.features.model.FeatureEntity;

/**
 * A Feature Repository class.
 * 
 * @author mokht
 *
 */
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

	/**
	 * Check the existing entity filter by feature name.
	 * 
	 * @param featureName
	 * @return
	 */
	public Boolean existsByFeatureName(String featureName);

	/**
	 * Return entity filter by feature name.
	 * 
	 * @param featureName
	 * @return
	 */
	public Optional<FeatureEntity> findByFeatureName(String featureName);

}
