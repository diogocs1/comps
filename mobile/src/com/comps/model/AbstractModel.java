package com.comps.model;

public class AbstractModel {
	private String avaliador_id;
	private String aluno_id;
	private String idAvaliacao;
	private String create_date;
	private String tipo;
	private String outrosDados;
	
	public static final String MEDIDAS_CORPORAIS = "MEDIDAS_CORPORAIS";
	public static final String PERIMETROS = "PERIMETROS";
	public static final String DOBRAS_CUTANEAS = "DOBRAS_CUTANEAS";
	public static final String IMPULSAO_HORIZONTAL = "IMPULSAO_HORIZONTAL";
	public static final String SENTAR_ALCANCAR = "SENTAR_ALCANCAR";
	public static final String PREENSAO_MANUAL = "PREENSAO_MANUAL";
	public static final String ABDOMINAIS = "ABDOMINAIS";
	public static final String LANCAMENTO_UNILATERAL = "LANCAMENTO_UNILATERAL";
	public static final String LANCAMENTO_SIMULTANEO = "LANCAMENTO_SIMULTANEO";
	public static final String CORRIDA_25M = "CORRIDA_25M";
	public static final String SHUTLLERUN_10x5M = "SHUTLLERUN_10x5M";
	public static final String SHUTLLERUN_20M = "SHUTLLERUN_20M";
	public static final String EQUILIBRIO_RETAGUARDA = "EQUILIBRIO_RETAGUARDA";
	public static final String SALTOS_LATERAIS = "SALTOS_LATERAIS";
	public static final String TRANSPOSICAO_LATERAL = "TRANSPOSICAO_LATERAL";
	public static final String SALTOS_MONOPEDAIS_PERNA_DIREITA = "SALTOS_MONOPEDAIS_PERNA_DIREITA";
	public static final String SALTOS_MONOPEDAIS_PERNA_ESQUERDA = "SALTOS_MONOPEDAIS_PERNA_ESQUERDA";
	
	
	public String getId() {return aluno_id;}
	
	public void setIdAluno(String id) {this.aluno_id = id;}

	public String getIdAvaliacao() {return idAvaliacao;}

	public void setIdAvaliacao(String idAvaliacao) {this.idAvaliacao = idAvaliacao;}

	public String getData() {return create_date;}

	public void setData(String data) {this.create_date = data;}

	public void setTipo(String tipo) {this.tipo = tipo;}
	public String getTipo() {return this.tipo;}

	public String getIdAvaliador() {
		return avaliador_id;
	}

	public void setIdAvaliador(String idAvaliador) {
		this.avaliador_id = idAvaliador;
	}

	public String getOutrosDados() {
		return outrosDados;
	}

	public void setOutrosDados(String outrosDados) {
		this.outrosDados = outrosDados;
	}



}
