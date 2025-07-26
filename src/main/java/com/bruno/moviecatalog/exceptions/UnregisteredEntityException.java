package com.bruno.moviecatalog.exceptions;

public class UnregisteredEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnregisteredEntityException() {
		super();
	}

	public UnregisteredEntityException(String message) {
		super(message);
	}

}
