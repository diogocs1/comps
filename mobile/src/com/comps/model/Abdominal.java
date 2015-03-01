package com.comps.model;

public class Abdominal extends AbstractModel {
	
	
	private String tempo;
	private int qtdAbdominais;
	
	public Abdominal() {
		setTipo(AbstractModel.ABDOMINAIS);
	}
	
	public Abdominal(String tempo){
		setTipo(AbstractModel.ABDOMINAIS);
		this.tempo = tempo;
	}
	
	
	
	public String getTempo() {return tempo;}
	
	public void setTempo(String tempo) {this.tempo = tempo;}

	public int getQtdAbdominais() {
		return qtdAbdominais;
	}

	public void setQtdAbdominais(int qtdAbdominais) {
		this.qtdAbdominais = qtdAbdominais;
	}
}
