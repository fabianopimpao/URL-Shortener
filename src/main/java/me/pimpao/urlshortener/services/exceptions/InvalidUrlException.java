package me.pimpao.urlshortener.services.exceptions;

public class InvalidUrlException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidUrlException(String msg) {
		super(msg);
	}
	
	public InvalidUrlException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
