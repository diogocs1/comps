package com.comps.model;

public class SentarAlcancar extends AbstractModel {
	
	private int result;
	public SentarAlcancar(){
		setTipo(AbstractModel.SENTAR_ALCANCAR);
	}
	
	public SentarAlcancar(int result){
		
		setTipo(AbstractModel.SENTAR_ALCANCAR);
	
		
		this.setResult(result);

	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
