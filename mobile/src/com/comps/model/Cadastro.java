package com.comps.model;

public class Cadastro {

	private int id;
	private String nome;
	private int idade;
	private String cpf;
	private String instituicao;
	
	public Cadastro () {
		
	}
	
	public Cadastro (int id, String nome, int idade, String cpf, String instituicao) {
		this.id          = id;
		this.nome        = nome;
		this.idade       = idade;
		this.cpf         = cpf;
		this.instituicao = instituicao;
				
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	
}
