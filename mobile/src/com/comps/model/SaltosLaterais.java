package com.comps.model;

public class SaltosLaterais extends AbstractModel {

	private int result;
	
	SaltosLaterais(int resultado){
		setTipo(AbstractModel.SALTOS_LATERAIS);
		this.setResultado(resultado);
	}

	public SaltosLaterais() {
		setTipo(AbstractModel.SALTOS_LATERAIS);
	}

	public int getResultado() {
		return result;
	}

	public void setResultado(int resultado) {
		this.result = resultado;
	}
}
