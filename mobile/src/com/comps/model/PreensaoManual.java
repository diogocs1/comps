package com.comps.model;

public class PreensaoManual extends AbstractModel {
	
	private int result;
	
	
	public PreensaoManual(){
		setTipo(AbstractModel.PREENSAO_MANUAL);
	}
	
	public PreensaoManual( int result){
		
		setTipo(AbstractModel.PREENSAO_MANUAL);
		
		this.setResult(result);
		
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	
	
}


