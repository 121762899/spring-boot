package com.spring.boot.exceptions;

public class ResourceNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ResourceNotExistException(String msg) {
		super(msg);
	}
	public ResourceNotExistException(String msg, Throwable t) {
		super(msg, t);
	}
}
