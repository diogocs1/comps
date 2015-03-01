package com.comps.model;

public class LancamentoUnilateral extends AbstractModel {
	
	
	private int result;
	
	
	public LancamentoUnilateral(){
		setTipo(AbstractModel.LANCAMENTO_UNILATERAL);
	}
	
	public LancamentoUnilateral(int result){
		
		setTipo(AbstractModel.LANCAMENTO_UNILATERAL);
		this.setResult(result);

	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	
}
