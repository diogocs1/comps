package com.comps.model;

public class ShutleRun_20m extends AbstractModel{

	
	private String tempo;
	private int voltas;
	
	public ShutleRun_20m() {
		setTipo(AbstractModel.SHUTLLERUN_20M);
	}
	
	public ShutleRun_20m(String tempo){
		
		setTipo(AbstractModel.SHUTLLERUN_20M);
		this.tempo = tempo;
	}
	
	
	
	public String getTempo() {return tempo;}
	
	public void setTempo(String tempo) {this.tempo = tempo;}

	public int getVoltas() {
		return voltas;
	}

	public void setVoltas(int voltas) {
		this.voltas = voltas;
	}
}
