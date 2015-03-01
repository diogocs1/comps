package com.comps.model;

public class Usuario {
	private String nomeUsuario;
	private String senha;
	private String escolas;
	private String turmas;
	private String alunos;
	
	public Usuario (String nomeUsuario, String senha, String escolas, String turmas, String alunos){
		setNomeUsuario(nomeUsuario);
		setSenha(senha);
		setEscolas(escolas);
		setTurmas(turmas);
		setAlunos(alunos);
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEscolas() {
		return escolas;
	}
	public void setEscolas(String escolas) {
		this.escolas = escolas;
	}
	public String getTurmas() {
		return turmas;
	}
	public void setTurmas(String turmas) {
		this.turmas = turmas;
	}
	public String getAlunos() {
		return alunos;
	}
	public void setAlunos(String alunos) {
		this.alunos = alunos;
	}
	
}
