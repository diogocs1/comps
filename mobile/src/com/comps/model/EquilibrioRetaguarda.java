package com.comps.model;

public class EquilibrioRetaguarda extends AbstractModel{
	
	private float result;
	
	
	public EquilibrioRetaguarda() {
		setTipo(AbstractModel.EQUILIBRIO_RETAGUARDA);
	}
	
	public EquilibrioRetaguarda(int result){
		setTipo(AbstractModel.EQUILIBRIO_RETAGUARDA);
		this.result = result;

	}

	public float getResult() {return result;}
	
	public void setResult(float result) {this.result = result;}

	
		
}
