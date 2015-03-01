package com.comps;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.comps.banco.DaoDados;
import com.comps.model.ShutleRun_20m;
import com.comps.util.Chronometer;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;


public class ShuttleRun_20m_Activity extends AbstractValidacaoActivity implements OnClickListener{
	
	private Button btIniciar,btParar,btIncremento,btDecremento,btSalvar,btCancelar;
	private Chronometer chronometerSh20;
	private TextView tvQtdVoltas;
	private boolean isClickPause = false;
	private boolean isClickResetar = false;
	private int qtdVoltas = 0;
	private long tempoParado = 0;
	private DaoDados dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shuttle_run_20m);

		chronometerSh20 = (Chronometer) findViewById(R.id.chronometerSh20);
		
		tvQtdVoltas = (TextView) findViewById(R.id.tvVoltas);	
		
		btIniciar = (Button) findViewById(R.id.btIniciarSh20);
		btIniciar.setOnClickListener(this);
		
		btParar = (Button) findViewById(R.id.btParar);
		btParar.setOnClickListener(this);
		
		btIncremento = (Button) findViewById(R.id.btIncremento);
		btIncremento.setOnClickListener(this);
		
		btDecremento = (Button) findViewById(R.id.btDecremento);
		btDecremento.setOnClickListener(this);
		
		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);
		
		btCancelar = (Button) findViewById(R.id.btResetar);
		btCancelar.setOnClickListener(this);
		
		dao = DaoDados.getInstance(getApplicationContext());
	}
	

	
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch(v.getId()){
		
		case R.id.btIniciarSh20:
			if(isClickPause){
				Log.d("click", "Passou3");
				
				chronometerSh20.setBase((SystemClock.elapsedRealtime()+tempoParado));
				chronometerSh20.start();
				tempoParado = 0;
				isClickPause = false;
			}
			else{
				Log.d("click", "Passou4");
				
				chronometerSh20.setBase(SystemClock.elapsedRealtime());

				chronometerSh20.start();
				tempoParado = 0;
			}
			isClickResetar = false;
			break;
		case R.id.btParar:
			
			if(isClickPause == false && isClickResetar == false){
				chronometerSh20.stop();
				tempoParado = chronometerSh20.getBase() - SystemClock.elapsedRealtime();
				//Log.d("lalala",Float.parseFloat(chronometerCo.getText().toString())+"");

				isClickPause = true;
				break;
			}
			
		case R.id.btSalvar:
			ShutleRun_20m sr = new ShutleRun_20m();
			if (CompsUtils.idAluno == -1)
				sr.setOutrosDados(CompsUtils.outrosDados);
			else
				sr.setIdAluno(""+CompsUtils.idAluno);
			sr.setIdAvaliador(""+CompsUtils.idAvaliador);
			sr.setData(retornarData());
			sr.setTempo(chronometerSh20.getText().toString()+"");
			sr.setVoltas(qtdVoltas);
			String json = GsonManager.getInstance().toJson(sr);
			dao.insert(json);
			
			ArrayList<String> lista = dao.listAll();
			Log.d("lalala","passou");
			for (String s : lista){
				Log.d("lalala", s);
			}
			mensagemSalvarSucesso();
			i.setClass(getApplicationContext(), ShuttleRun_20m_Activity.class);
			
			startActivity(i);
			finish();
			break;
		case R.id.btResetar:
		
			isClickResetar = true; 
			
			tempoParado = 0;
			isClickPause = false;
			break;
		
		case R.id.btIncremento:
			qtdVoltas += 1;
			tvQtdVoltas.setText(String.valueOf(qtdVoltas));
			break;
			
		case R.id.btDecremento:
			qtdVoltas -= 1;
			tvQtdVoltas.setText(String.valueOf(qtdVoltas));
			break;
		}
	}
}
