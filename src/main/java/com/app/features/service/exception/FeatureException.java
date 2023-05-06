/**
 * 
 */
package com.app.features.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mokht
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FeatureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FeatureException() {
		super();
	}

	public FeatureException(String errorMessage) {
		super(errorMessage);
	}

}
