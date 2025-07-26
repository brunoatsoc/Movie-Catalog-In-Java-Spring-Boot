package com.bruno.moviecatalog.exceptions;

public class DuplicateEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateEntityException() {
		super();
	}

	public DuplicateEntityException(String message) {
		super(message);
	}

}
