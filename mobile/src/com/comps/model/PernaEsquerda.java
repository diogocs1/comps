package com.comps.model;

public class PernaEsquerda extends AbstractModel{

	private int result;
	
	public PernaEsquerda(){
		setTipo(AbstractModel.SALTOS_MONOPEDAIS_PERNA_ESQUERDA);
		
	}
	
	public PernaEsquerda(int resultado){
		
		setTipo(AbstractModel.SALTOS_MONOPEDAIS_PERNA_ESQUERDA);
		this.result = resultado;
		
	}

	public int getResultado() {
		return result;
	}

	public void setResultado(int resultado) {
		this.result = resultado;
	}
	
	
}
