package com.comps.model;

public class ImpulsaoHorizontal extends AbstractModel {
	

	private int result;
	
	
	
	public ImpulsaoHorizontal(){
		setTipo(AbstractModel.IMPULSAO_HORIZONTAL);
	}
	
	public ImpulsaoHorizontal( int result){
		
		setTipo(AbstractModel.IMPULSAO_HORIZONTAL);
		
		this.setResult(result);
		
		
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
