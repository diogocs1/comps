package com.comps.model;

public class DobrasCutaneas extends AbstractModel{

	
	private float biceps;
	private float triceps;
	private float subEscapular;
	private float peitoral;
	private float axilarMedia;
	private float supraIliaca;
	private float supraEspinhal;
	private float supraPatelar;
	private float abdominal;
	private float coxaMedial;
	private float panturrilhaMedial;
	
	
	
	public DobrasCutaneas () {
		setTipo(AbstractModel.DOBRAS_CUTANEAS);
	}
	
	public DobrasCutaneas(float biceps, float triceps,float subEscapular,float peitoral,
			float axilarMedia,float supraIliaca,float supraEspinhal,float supraPatelar,
			float abdominal,float coxaMedial,float panturrilhaMedial) {
		
		
		setTipo(AbstractModel.DOBRAS_CUTANEAS);
		
		
		this.biceps = biceps;
		this.triceps = triceps;
		this.subEscapular = subEscapular;
		this.peitoral = peitoral;
		this.axilarMedia = axilarMedia;
		this.supraIliaca = supraIliaca;
		this.supraEspinhal = supraEspinhal;
		this.supraPatelar = supraPatelar;
		this.abdominal = abdominal;
		this.coxaMedial = coxaMedial;
		this.panturrilhaMedial = panturrilhaMedial;
		
	}
	
	
	
	public float getTricipital() {return triceps;}
	
	public void setTricipital(float triceps) {this.triceps = triceps;}
	
	public float getSubEscapular() {return subEscapular;}
	
	public void setSubEscapular(float subescapular) {this.subEscapular = subescapular;}

	public float getSupraIliaca() { return supraIliaca; }

	public void setSupraIliaca( float supraIliaca ) {this.supraIliaca = supraIliaca;}

	public float getAbdominal() {return abdominal;}

	public void setAbdominal(float abdominal) {this.abdominal = abdominal;}

	public float getBiceps() {return biceps;}

	public void setBiceps(float biceps) {this.biceps = biceps;}

	public float getPeitoral() {return peitoral;}

	public void setPeitoral(float peitoral) {this.peitoral = peitoral;}

	public float getAxilarMedia() {return axilarMedia;}

	public void setAxilarMedia(float axilarMedia) {this.axilarMedia = axilarMedia;}

	public float getSupraEspinhal() {return supraEspinhal;}

	public void setSupraEspinhal(float supraEspinhal) {this.supraEspinhal = supraEspinhal;}

	public float getSupraPatelar() {return supraPatelar;}

	public void setSupraPatelar(float supraPatelar) {this.supraPatelar = supraPatelar;}

	public float getCoxaMedial() {return coxaMedial;}

	public void setCoxaMedial(float coxaMedial) {this.coxaMedial = coxaMedial;}

	public float getPanturrilhaMedial() {return panturrilhaMedial;}

	public void setPanturrilhaMedial(float panturrilhaMedial) {this.panturrilhaMedial = panturrilhaMedial;}
	
}
