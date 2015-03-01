package com.comps.exception;

public class SenhaIncorretaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8815205735197464930L;
	
	public SenhaIncorretaException(String message){
		super(message);
	}
	public SenhaIncorretaException(){
		super("Verificação de senha retornou false");
	}

}
