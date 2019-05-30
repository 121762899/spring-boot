package com.spring.boot.exceptions;

public class ResourceExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ResourceExistException(String msg) {
		super(msg);
	}
	public ResourceExistException(String msg, Throwable t) {
		super(msg ,t);
	}
}
