package com.vsm.devcase.service.exceptions;

public class HttpMessageNotReadableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public HttpMessageNotReadableException(String msg) {
		super(msg);
	}
	
	public HttpMessageNotReadableException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
