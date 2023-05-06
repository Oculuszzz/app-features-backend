/**
 * 
 */
package com.app.features.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.features.payload.AccessFeatureResponse;
import com.app.features.payload.UpdateUserFeatureRequest;
import com.app.features.service.FeaturesServiceImpl;

import jakarta.validation.Valid;

/**
 * A controller class which specific to Feature API.
 * 
 * @author mokht
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/api/feature")
public class FeaturesController {

	/**
	 * Features Service class object.
	 */
	private final FeaturesServiceImpl featuresServiceImpl;

	/**
	 * Class constructor.
	 * 
	 * @param featuresServiceImpl
	 */
	public FeaturesController(FeaturesServiceImpl featuresServiceImpl) {

		this.featuresServiceImpl = featuresServiceImpl;

	}

	/**
	 * Return an ResponseEntity object where consists of UserAccessFeatureResponse
	 * object and HTTP response. The UserAccessFeatureResponse contains value of the
	 * feature is accessible or not to a specific user which requested by client.
	 * 
	 * @param email
	 * @param featureName
	 * @return
	 */
	@GetMapping
	public ResponseEntity<AccessFeatureResponse> userAccessFeature(@RequestParam String email,
			@RequestParam String featureName) {

		return new ResponseEntity<>(featuresServiceImpl.isUserCanAccessByFeatureName(email, featureName),
				HttpStatus.OK);

	}

	/**
	 * Return an ResponseEntity object where it's successfully update the user
	 * feature or not which requested by client.
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping(value = "update-user")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody UpdateUserFeatureRequest request) {

		featuresServiceImpl.updateUserFeature(request);

		return ResponseEntity.ok().build();

	}

	/**
	 * Return an ResponseEntity object where it's successfully created new feature
	 * type or not which requested by client.
	 * 
	 * @param featureName
	 * @return
	 */
	@PostMapping(value = "add-new-feature")
	public ResponseEntity<Object> addNewFeature(String featureName) {

		featuresServiceImpl.addFeature(featureName);

		return ResponseEntity.ok().build();

	}

}
