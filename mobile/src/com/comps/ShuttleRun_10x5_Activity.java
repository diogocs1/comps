package com.comps;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.comps.banco.DaoDados;
import com.comps.model.ShutleRun_10x5;
import com.comps.util.Chronometer;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class ShuttleRun_10x5_Activity extends AbstractValidacaoActivity implements OnClickListener {

	private Button btIniciar,btParar,btSalvar,btCancelar;
	private Chronometer chronometerSh10;
	private boolean isClickPause = false;
	private boolean isClickResetar = false;
	private long tempoParado = 0;
	private DaoDados dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shuttle_run10x5);

		chronometerSh10 = (Chronometer) findViewById(R.id.chronometerSh10);

		btIniciar = (Button) findViewById(R.id.btIniciarSh);
		btIniciar.setOnClickListener(this);

		btParar = (Button) findViewById(R.id.btPararSh);
		btParar.setOnClickListener(this);

		btSalvar = (Button) findViewById(R.id.btSalvarSh);
		btSalvar.setOnClickListener(this);

		btCancelar = (Button) findViewById(R.id.btResetarSh);
		btCancelar.setOnClickListener(this);

		dao = DaoDados.getInstance(getApplicationContext());
	}
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch(v.getId()){

		case R.id.btIniciarSh:
			if(isClickPause){
				Log.d("click", "Passou3");
				chronometerSh10.setBase(SystemClock.elapsedRealtime()+tempoParado);
				chronometerSh10.start();
				tempoParado = 0;
				isClickPause = false;
			}
			else{
				Log.d("click", "Passou4");

				chronometerSh10.setBase(SystemClock.elapsedRealtime());

				chronometerSh10.start();
				tempoParado = 0;
			}
			isClickResetar = false;
			break;
		case R.id.btPararSh:
			if(isClickPause == false && isClickResetar == false){
				chronometerSh10.stop();
				tempoParado = chronometerSh10.getBase() - SystemClock.elapsedRealtime();
				

				isClickPause = true;	
			}
			break;
		case R.id.btSalvarSh:
			ShutleRun_10x5 sh10x5 = new ShutleRun_10x5();
			if (CompsUtils.idAluno == -1)
				sh10x5.setOutrosDados(CompsUtils.outrosDados);
			else
				sh10x5.setIdAluno(""+CompsUtils.idAluno);
			sh10x5.setIdAvaliador(""+CompsUtils.idAvaliador);
			sh10x5.setData(retornarData());
			sh10x5.setTempo(chronometerSh10.getText().toString()+"");
			String json = GsonManager.getInstance().toJson(sh10x5);
			dao.insert(json);

			ArrayList<String> lista = dao.listAll();
			Log.d("lalala","passou");
			for (String s : lista){
				Log.d("lalala", s);
			}
			mensagemSalvarSucesso();
			i.setClass(getApplicationContext(), ShuttleRun_10x5_Activity.class);

			startActivity(i);
			finish();
			break;
		case R.id.btResetarSh:
			isClickResetar = true; 
			chronometerSh10.setText("00:00:0");
			tempoParado = 0;
			isClickPause = false;
			break;
		}
	}
}
