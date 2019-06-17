package me.pimpao.urlshortener.services.exceptions;

public class ExpiredUrlException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExpiredUrlException(String msg) {
		super(msg);
	}
	
	public ExpiredUrlException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
