package com.comps.model;

public class ShutleRun_10x5 extends AbstractModel {
	
	
	private String tempo;
	
	public ShutleRun_10x5() {
		setTipo(AbstractModel.SHUTLLERUN_10x5M);
	}
	
	public ShutleRun_10x5(String tempo){
		setTipo(AbstractModel.SHUTLLERUN_10x5M);
		this.tempo = tempo;
	}
	
	
	
	public String getTempo() {return tempo;}
	
	public void setTempo(String tempo) {this.tempo = tempo;}
}
