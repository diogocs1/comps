package com.comps.exception;

public class HttpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8590769656807004029L;
	public HttpException(String message){
		super(message);
	}
	public HttpException(){
		super("Status code >= 400.");
	}
}
