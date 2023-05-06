/**
 * 
 */
package com.app.features.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * A User entity/model class.
 * 
 * @author mokht
 *
 */
@Entity
@Table(name = "users")
public class UserEntity {

	/**
	 * User Id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * User email.
	 */
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	/**
	 * List of user features.
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "user_accessibility_features", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_feature_id", nullable = false) })
	private List<UserFeatureEntity> userFeatures;

	/**
	 * Class constructor.
	 */
	public UserEntity() {
		super();
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userFeatures
	 */
	public List<UserFeatureEntity> getUserFeatures() {
		return userFeatures;
	}

	/**
	 * @param userFeatures the userFeatures to set
	 */
	public void setUserFeatures(List<UserFeatureEntity> userFeatures) {
		this.userFeatures = userFeatures;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, userFeatures);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(userFeatures, other.userFeatures);
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", userFeatures=" + userFeatures + "]";
	}

}
