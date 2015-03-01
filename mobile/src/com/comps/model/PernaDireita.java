package com.comps.model;

public class PernaDireita extends AbstractModel {
	
	
	private int result;
	
	public PernaDireita() {
		setTipo(AbstractModel.SALTOS_MONOPEDAIS_PERNA_DIREITA);
		
	}
	
	public PernaDireita(int resultado){
		
		setTipo(AbstractModel.SALTOS_MONOPEDAIS_PERNA_DIREITA);
		
		this.setResultado(resultado);
	}

	public int getResultado() {
		return result;
	}

	public void setResultado(int resultado) {
		this.result = resultado;
	}
}