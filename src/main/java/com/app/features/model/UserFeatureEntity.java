/**
 * 
 */
package com.app.features.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * A User Feature entity/model class.
 * 
 * @author mokht
 *
 */
@Entity
@Table(name = "user_feature")
public class UserFeatureEntity {

	/**
	 * The User Feature Id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * The accessibility feature enable/disable.
	 */
	@Column(name = "enable", nullable = false)
	private Boolean enable;

	/**
	 * The feature object.
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feature_id", referencedColumnName = "id")
	private FeatureEntity feature;

	/**
	 * Class constructor.
	 */
	public UserFeatureEntity() {
		super();
	}

	/**
	 * Class constructor.
	 * 
	 * @param enable
	 * @param feature
	 */
	public UserFeatureEntity(Boolean enable, FeatureEntity feature) {
		this.enable = enable;
		this.feature = feature;
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
	 * @return the enable
	 */
	public Boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the feature
	 */
	public FeatureEntity getFeature() {
		return feature;
	}

	/**
	 * @param feature the feature to set
	 */
	public void setFeature(FeatureEntity feature) {
		this.feature = feature;
	}

	@Override
	public int hashCode() {
		return Objects.hash(enable, feature, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFeatureEntity other = (UserFeatureEntity) obj;
		return Objects.equals(enable, other.enable) && Objects.equals(feature, other.feature)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserAccessFeatureEntity [id=" + id + ", enable=" + enable + ", feature=" + feature + "]";
	}

}
