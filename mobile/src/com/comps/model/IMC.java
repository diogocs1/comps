package com.comps.model;

public class IMC {

	private int id;
	private float peso;
	private float altura;
	
	public IMC () {
		
	}
	
	public IMC(int id, float peso, float altura) {
		this.id     = id;
		this.peso   = peso;
		this.altura = altura;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public float imc() {
		return ((peso)/(altura*altura));
	}
	
	
}
