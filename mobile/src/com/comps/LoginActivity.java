package com.comps;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.comps.banco.DaoLogin;
import com.comps.exception.SenhaIncorretaException;
import com.comps.util.CompsUtils;
import com.comps.web.TaskLogin;
import com.comps.web.WebService;

public class LoginActivity extends AbstractValidacaoActivity{

	Button btEntrarOnline;
	Button btEntrarOffline;
	EditText etNomeUsuario;
	EditText etSenha;
	TextView tvConfigConexao;
	
	Intent intent;
	WebService web;
	DaoLogin db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_inicial);
		intent = new Intent();
		
		etNomeUsuario = (EditText) findViewById(R.id.etNomeUsuario);
		etSenha = (EditText) findViewById(R.id.etSenha);
		
		btEntrarOnline = (Button) findViewById(R.id.btEntrarOnline);
		btEntrarOnline.setOnClickListener(this);
		
		btEntrarOffline = (Button) findViewById(R.id.btEntrarOffline);
		btEntrarOffline.setOnClickListener(this);
		
		tvConfigConexao = (TextView) findViewById(R.id.tvConfigConexao);
		tvConfigConexao.setOnClickListener(this);
		
		db = DaoLogin.getInstance(getApplicationContext());
		db.onCreate();
	}	

	@Override
	public void onClick(View v) {
		String nomeUsuario = etNomeUsuario.getText().toString();
		String senha = etSenha.getText().toString();
		switch(v.getId()){
			case(R.id.btEntrarOnline):
				try{
					AsyncTask<Void,Void,Boolean> loginOn = new TaskLogin(getApplicationContext(), nomeUsuario, senha).execute();
					if (!loginOn.get()){
						mensagemErrorLonga("Não foi possível entrar, verifique nome de usuário e senha ou tente mais tarde");
						break;
					}
					CompsUtils.isOnline = true;
					intent.setClass(getApplicationContext(), SelecaoActivity.class);
					startActivity(intent);
					break;
				} catch (InterruptedException e) {
					mensagemError("Ocorreu um erro ao entrar, tente novamente mais tarde");
					break;
				} catch (ExecutionException e) {
					mensagemError("Ocorreu um erro ao entrar, tente novamente mais tarde");
					break;
				} catch (JSONException e) {
					mensagemError("Ocorreu um erro ao entrar, dados inválidos");
					break;
				}
			case (R.id.btEntrarOffline):
				// Tenta o login offline
				try {
					CompsUtils.loginOffline(getApplicationContext(), nomeUsuario, senha);
					CompsUtils.isOnline = false;
					intent.setClass(getApplicationContext(), SelecaoActivity.class);
					startActivity(intent);
				} catch (SenhaIncorretaException e) {
					mensagemErrorLonga("Não foi possível entrar, verifique nome de usuário e senha");
				}
				break;
			case R.id.tvConfigConexao:
				intent.setClass(getApplicationContext(), ConfigConexaoActivity.class);
				startActivity(intent);
				break;
		}
	}
}
