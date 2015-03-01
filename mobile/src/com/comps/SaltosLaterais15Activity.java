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
import com.comps.model.SaltosLaterais;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class SaltosLaterais15Activity extends AbstractValidacaoActivity {
	
	private EditText etTentativa1,
					 etTentativa2;
	private Button btSalvar;
	private int tentativa1,
				tentativa2,
				resultado;
	DaoDados dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saltos_laterais15);

		
		etTentativa1 = (EditText) findViewById(R.id.etT1);
		
		etTentativa2 = (EditText) findViewById(R.id.etT2);
		
		btSalvar = (Button) findViewById(R.id.btSalvarSL);
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
		Log.d("DobrasCutanesActivity","metodoOnclick");
		Log.d("saltos", "1");
		tentativa1 = Integer.parseInt(etTentativa1.getText().toString());
		tentativa2 = Integer.parseInt(etTentativa2.getText().toString());
		resultado = tentativa1 + tentativa2;
		SaltosLaterais sl = new SaltosLaterais(); 
		if (CompsUtils.idAluno == -1)
			sl.setOutrosDados(CompsUtils.outrosDados);
		else
			sl.setIdAluno(""+CompsUtils.idAluno);
		sl.setIdAvaliador(""+CompsUtils.idAvaliador);
		sl.setData(retornarData());
		sl.setResultado(resultado);
		Log.d("saltos", "2");
		String json = GsonManager.getInstance().toJson(sl);
		dao.insert(json);
		Log.d("saltos", "3");
		ArrayList<String> lista = dao.listAll();
		Log.d("saltos","passou");
		for (String s : lista){
			Log.d("saltos", s);
		}
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), SaltosLaterais15Activity.class);
		startActivity(i);
		finish();
	}
}
