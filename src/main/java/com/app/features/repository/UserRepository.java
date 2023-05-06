/**
 * 
 */
package com.app.features.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.features.model.UserEntity;

/**
 * A User Repository class.
 * 
 * @author mokht
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	/**
	 * Return entity filter by email.
	 * 
	 * @param email
	 * @return
	 */
	public Optional<UserEntity> findByEmail(String email);

}
