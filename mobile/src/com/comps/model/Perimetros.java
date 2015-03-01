package com.comps.model;

public class Perimetros extends AbstractModel {
	

	private float perimetroCintura;
	private float perimetroAbdomen;
	private float perimetroQuadril;
	
	public Perimetros(){
		setTipo(AbstractModel.PERIMETROS);
	}
	
	public Perimetros(String id,String idAvaliacao, float perimetroCintura, float perimetroAbdomen, float perimetroQuadril){
		this.perimetroAbdomen = perimetroAbdomen;
		this.perimetroCintura = perimetroCintura;
		this.perimetroQuadril = perimetroQuadril;
	}
	

	
	public float getPerimetroCintura() {return perimetroCintura;}
	
	public void setPerimetroCintura(float perimetroCintura) {this.perimetroCintura = perimetroCintura;}
	
	public float getPerimetroAbdomen() {return perimetroAbdomen;}
	
	public void setPerimetroAbdomen(float perimetroAbdomen) {this.perimetroAbdomen = perimetroAbdomen;}
	
	public float getPerimetroQuadril() {return perimetroQuadril;}
	
	public void setPerimetroQuadril(float perimetroQuadril) {this.perimetroQuadril = perimetroQuadril;}
	
	
}
