package com.comps.model;

public class Corrida extends AbstractModel{

	
	private String tempo;
	
	public Corrida(){
		setTipo(AbstractModel.CORRIDA_25M);
	}
	
	public Corrida(String tempo){
		setTipo(AbstractModel.CORRIDA_25M);
	
		this.setTempo(tempo);
	}
	
	
	
	public String getTempo() {return tempo;}
	
	public void setTempo(String string) {this.tempo = string;}
}
