package com.comps;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comps.banco.DaoDados;
import com.comps.model.TransposicaoLateral;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class TransposicoesLaterais20Activity extends AbstractValidacaoActivity {
	
	private EditText etTentativa1,etTentativa2;
	private Button btSalvar;
	
	private int tentativa1,tentativa2,resultado;
	private DaoDados dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transposicao_laterais20);
		
		etTentativa1 = (EditText) findViewById(R.id.etT1);
		etTentativa1.setOnClickListener(this);
		
		etTentativa2 = (EditText) findViewById(R.id.etT2);
		etTentativa2.setOnClickListener(this);
		
		btSalvar = (Button) findViewById(R.id.btSalvarTL);
		btSalvar.setOnClickListener(this);
		
		dao = DaoDados.getInstance(getApplicationContext());
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		
		Log.d("Transposição lateral","metodoOnclick");
		Log.d("trans", "1");
		tentativa1 = Integer.parseInt(etTentativa1.getText().toString());
		tentativa2 = Integer.parseInt(etTentativa2.getText().toString());
		resultado = tentativa1 + tentativa2;
		TransposicaoLateral tp = new TransposicaoLateral(); 
		if (CompsUtils.idAluno == -1)
			tp.setOutrosDados(CompsUtils.outrosDados);
		else
			tp.setIdAluno(""+CompsUtils.idAluno);
		tp.setIdAvaliador(""+CompsUtils.idAvaliador);
		tp.setData(retornarData());
		tp.setResultado(resultado);
		Log.d("trans", "2");
		String json = GsonManager.getInstance().toJson(tp);
		dao.insert(json);
		Log.d("trans", "3");
		ArrayList<String> lista = dao.listAll();
		Log.d("trans","passou");
		for (String s : lista){
			Log.d("trans", s);
		}
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), TransposicoesLaterais20Activity.class);
		startActivity(i);
		finish();
	}
}
