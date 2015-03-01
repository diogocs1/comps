package com.comps;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.comps.util.AndroidUtils;
import com.comps.util.CompsUtils;
import com.comps.web.TaskEnviar;

public class SelecaoActivity extends AbstractValidacaoActivity {
	
	Intent intent;
	Spinner spinnerEscola;
	Spinner spinnerAluno;
	Button btAvancar;
	Button btNaoListado;
	Button btSincronizar;
	TextView tvOutrasAcoes;
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_selecao_aluno);
		intent = new Intent();
		
		spinnerEscola = (Spinner) findViewById(R.id.spinnerEscola);
		spinnerAluno = (Spinner) findViewById(R.id.spinnerAluno);
		
		btAvancar = (Button) findViewById(R.id.btAvancar);
		btAvancar.setOnClickListener(this);
		
		btNaoListado = (Button) findViewById(R.id.btNaoListado);
		btNaoListado.setOnClickListener(this);
		
		tvOutrasAcoes = (TextView) findViewById(R.id.tvOutrasAcoes);
		
		btSincronizar = (Button) findViewById(R.id.btSincronizar1);
		btSincronizar.setOnClickListener(this);
		if (!CompsUtils.isOnline){
			tvOutrasAcoes.setVisibility(View.INVISIBLE);
			btSincronizar.setVisibility(View.INVISIBLE);
		}else{
			tvOutrasAcoes.setVisibility(View.VISIBLE);
			btSincronizar.setVisibility(View.VISIBLE);
		}
		
		spinnerEscola.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				try {
					ArrayList<String> alunos = CompsUtils.alunosPorTurma(spinnerEscola.getSelectedItem().toString());
					String[] itens = new String[alunos.size()];
					itens = alunos.toArray(itens);
					Log.d("Itens", itens.toString());
					ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
																						getApplicationContext(), 
																						android.R.layout.simple_list_item_1, 
																						itens);
					spinnerAluno.setAdapter(adapter);
				} catch (JSONException e) {
					Log.d("Erro Alunos", "Erro ao recuperar alunos");
					Log.d("Erro JSON", e.getMessage());
					mensagemErrorLonga("Não foi possível listar os alunos");
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		// Recupera todas as turmas referentes ao usuário
		try {
			ArrayList<String> turmas = CompsUtils.turmaPorNome(getApplicationContext());
			String[] itens = new String[turmas.size()];
			itens = turmas.toArray(itens);
			Log.d("Itens escola", itens.toString());
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
																				getApplicationContext(), 
																				android.R.layout.simple_list_item_1, 
																				itens);
			spinnerEscola.setAdapter(adapter);
		} catch (JSONException e) {
			Log.d("Erro JSON", e.getMessage());
			mensagemErrorLonga("Não foi possível listar as turmas");
		}
	};

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case (R.id.btAvancar):
				try {
					String nome = spinnerAluno.getSelectedItem().toString();
					int idAluno = CompsUtils.retiraId(nome);
					CompsUtils.idAluno = idAluno;
					intent.setClass(getApplicationContext(), MainActivity.class);
					startActivity(intent);
				} catch (Exception e){
					mensagemErrorLonga("Dados inválidos");
				}
				break;
			case R.id.btSincronizar1:
				if(AndroidUtils.isNetworkAvailable(getApplicationContext())){
					AsyncTask<Void,Void,Boolean> result = new TaskEnviar(getApplicationContext()).execute();
					try {
						if (result.get()){
							mensagemSincronizar();
						}else{
							mensagemSincronizarErro();
						}
					} catch (InterruptedException e) {e.printStackTrace();
					} catch (ExecutionException e) {e.printStackTrace();}
				}else{
					msgNoInternet();
				}
				break;
			case R.id.btNaoListado:
				intent.setClass(getApplicationContext(), NaoListadoActivity.class);
				startActivity(intent);
				break;
		}
	}

}
