package com.comps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comps.util.CompsUtils;

public class NaoListadoActivity extends AbstractValidacaoActivity {
	
	Intent intent;
	
	EditText etNomeEscola;
	EditText etNomeSerie;
	EditText etNomeTurma;
	EditText etAno;
	EditText etNomeAluno;
	Button btAvancar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nao_listado);
		intent = new Intent();
		
		etNomeEscola = (EditText) findViewById(R.id.etNomeEscola);
		etNomeSerie = (EditText) findViewById(R.id.etNomeSerie);
		etNomeTurma = (EditText) findViewById(R.id.etNomeTurma);
		etAno = (EditText) findViewById(R.id.etAno);
		etNomeAluno = (EditText) findViewById(R.id.etNomeAluno);
		
		btAvancar = (Button) findViewById(R.id.btAvancarNl);
		btAvancar.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btAvancarNl:
				String escola = etNomeEscola.getText().toString();
				String serie = etNomeSerie.getText().toString();
				String turma = etNomeTurma.getText().toString();
				String ano = etAno.getText().toString();
				String aluno = etNomeAluno.getText().toString();
				
				CompsUtils.outrosDados = "escola:"+ escola +",\n turma:"+ turma + ",\n " + 
										  "serie:"+ serie + ",\n ano:"+ ano + ",\n " + 
										  "aluno:"+ aluno;
				
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
		}
	}

}
