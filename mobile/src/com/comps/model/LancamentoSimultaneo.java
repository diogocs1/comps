package com.comps.model;

public class LancamentoSimultaneo extends AbstractModel {
	
	
	private int result;
	
	
	public LancamentoSimultaneo(){
		setTipo(AbstractModel.LANCAMENTO_SIMULTANEO);
	}
	
	public LancamentoSimultaneo(int result){
		
		setTipo(AbstractModel.LANCAMENTO_SIMULTANEO);
		
		this.setResult(result);
		
		
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	
}
