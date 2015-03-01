package com.comps;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.comps.banco.DaoDados;
import com.comps.model.Corrida;
import com.comps.util.Chronometer;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class CorridaActivity extends AbstractValidacaoActivity {

	private Button btIniciar,btParar,btSalvar,btResetar;
	private Chronometer chronometerCo; 
	private boolean isClickPause = false;
	private long tempoParado = 0;
	private DaoDados dao;

	private boolean isClickResetar = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.corrida);
		Log.d("corrida", "Passou1");

		chronometerCo = (Chronometer) findViewById(R.id.chronometerCo);

		btIniciar = (Button) findViewById(R.id.btIniciarCo);
		btIniciar.setOnClickListener(this);

		btParar = (Button) findViewById(R.id.btParar);
		btParar.setOnClickListener(this);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);

		btResetar = (Button) findViewById(R.id.btResetar);
		btResetar.setOnClickListener(this);

		dao = DaoDados.getInstance(getApplicationContext());
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch(v.getId()){

		case R.id.btIniciarCo:
			if(isClickPause){
				Log.d("click", "Passou3");
				chronometerCo.setBase(SystemClock.elapsedRealtime()+tempoParado);
				chronometerCo.start();
				tempoParado = 0;
				isClickPause = false;
			}
			else{
				Log.d("click", "Passou4");

				chronometerCo.setBase(SystemClock.elapsedRealtime());

				chronometerCo.start();
				tempoParado = 0;
			}
			isClickResetar = false;
			break;



		case R.id.btParar:
			if(isClickPause == false && isClickResetar == false){
				chronometerCo.stop();
				tempoParado = chronometerCo.getBase() - SystemClock.elapsedRealtime();
				//Log.d("lalala",Float.parseFloat(chronometerCo.getText().toString())+"");

				isClickPause = true;
				break;
			}
			break;
		case R.id.btSalvar:
			Corrida co = new Corrida();
			if (CompsUtils.idAluno == -1)
				co.setOutrosDados(CompsUtils.outrosDados);
			else
				co.setIdAluno(""+CompsUtils.idAluno);
			co.setIdAvaliador(""+CompsUtils.idAvaliador);
			co.setData(retornarData());
			co.setTempo(chronometerCo.getText().toString()+"");
			String json = GsonManager.getInstance().toJson(co);
			dao.insert(json);

			ArrayList<String> lista = dao.listAll();
			Log.d("lalala","passou");
			for (String s : lista){
				Log.d("lalala", s);
			}
			mensagemSalvarSucesso();
			i.setClass(getApplicationContext(), CorridaActivity.class);
			
			startActivity(i);
			finish();
			break;
		case R.id.btResetar:
			isClickResetar = true; 
			chronometerCo.setText("00:00:0");
			tempoParado = 0;
			isClickPause = false;
			break;
		}
	}
}
