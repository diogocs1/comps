package com.comps.model;

public class TransposicaoLateral extends AbstractModel{
	
	
	
	private int result;
	
	public TransposicaoLateral(){
		setTipo(AbstractModel.TRANSPOSICAO_LATERAL);
	}
	
	public TransposicaoLateral(int resultado){
		
		setTipo(AbstractModel.TRANSPOSICAO_LATERAL);
		
		this.result = resultado;
		
	}
	public int getResultado() {return result;}
	
	public void setResultado(int resultado) {this.result = resultado;}
}
