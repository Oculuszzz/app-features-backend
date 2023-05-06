/**
 * 
 */
package com.app.features.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A Features entity/model class.
 * 
 * @author mokht
 *
 */
@Entity
@Table(name = "features")
public class FeatureEntity {

	/**
	 * The Id feature.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * The feature name.
	 */
	@Column(name = "featureName", nullable = false, unique = true)
	private String featureName;

	/**
	 * Class constructor.
	 */
	public FeatureEntity() {
		super();
	}

	/**
	 * Class constructor.
	 * 
	 * @param featureName
	 */
	public FeatureEntity(String featureName) {
		this.featureName = featureName;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the featureName
	 */
	public String getFeatureName() {
		return featureName;
	}

	/**
	 * @param featureName the featureName to set
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(featureName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeatureEntity other = (FeatureEntity) obj;
		return Objects.equals(featureName, other.featureName) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "FeatureEntity [id=" + id + ", featureName=" + featureName + "]";
	}

}
