package com.comps.model;

public class MedidasCorporais extends AbstractModel {
	private float massa;
	private float estatura;
	private float altura_sentado;
	private float envergadura;
	
	public MedidasCorporais(){
		setTipo(AbstractModel.MEDIDAS_CORPORAIS);
	}
	
	public MedidasCorporais(String id, String idAvaliacao, float massaCorporal, float estatura, float alturaSentado, float envergadura){
		
		setIdAluno(id);
		setTipo(AbstractModel.MEDIDAS_CORPORAIS);
		this.massa = massaCorporal;
		this.estatura = estatura;
		this.altura_sentado = alturaSentado;
		this.envergadura = envergadura;
	}
	

	
	public float getMassaCorporal() {return massa;}
	
	public void setMassaCorporal(float massaCorporal) {this.massa = massaCorporal;}
	
	public float getEstatura() {return estatura;}
	
	public void setEstatura(float estatura) {this.estatura = estatura;}
	
	public float getAlturaSentado() {return altura_sentado;}
	
	public void setAlturaSentado(float alturaSentado) {this.altura_sentado = alturaSentado;}

	public float getEnvergadura() {return envergadura;}

	public void setEnvergadura(float envergadura) {this.envergadura = envergadura;}
	
}
