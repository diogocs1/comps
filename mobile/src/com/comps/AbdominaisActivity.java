package com.comps;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.comps.banco.DaoDados;
import com.comps.model.Abdominal;
import com.comps.util.Chronometer;
import com.comps.util.CompsUtils;
import com.comps.util.Chronometer.OnChronometerTickListener;
import com.comps.util.GsonManager;



public class AbdominaisActivity extends AbstractValidacaoActivity {

	private Chronometer chronometer1;
	private  Button btIniciar,
					btIncrementoAb,
					btDecrementoAb,
					btCancelar,
					btSalvar;
	
	private boolean isClickPause = false;
	public long tempoQuandoParado = 0;
	private TextView tvQtdAbdominais;
	private long tempo = 0;
	
	private int qtdAbdominais = 0;

	private DaoDados dao;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abdominais_minuto);	

		chronometer1 = (Chronometer) findViewById(R.id.chronometer1);
		tvQtdAbdominais = (TextView) findViewById(R.id.tvAbdominais);
		
		chronometer1.setOnChronometerTickListener(new OnChronometerTickListener() {


			public void onChronometerTick(Chronometer chronometer) {
				
				//chronometer.setText(DateFormat.format("kk:mm:ss:SSS", inDate));
				if ( SystemClock.elapsedRealtime() >= tempo){
					
					chronometer1.stop();
				}

			}
		});

		btIniciar = (Button) findViewById(R.id.btIniciarAb20);
		btIniciar.setOnClickListener(this);

		btIncrementoAb = (Button) findViewById(R.id.btIncremento);
		btIncrementoAb.setOnClickListener(this);

		btDecrementoAb = (Button) findViewById(R.id.btDecremento);
		btDecrementoAb.setOnClickListener(this);

		btCancelar = (Button) findViewById(R.id.btResetar);
		btCancelar.setOnClickListener(this);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);
		
		dao = DaoDados.getInstance(getApplicationContext());
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch(v.getId()){

		case R.id.btIniciarAb20:
			if(isClickPause){ 
				chronometer1.stop();
				isClickPause = false;
			}
			else{
				tempo = SystemClock.elapsedRealtime()+60000;
				chronometer1.setBase(SystemClock.elapsedRealtime());
				chronometer1.start();
				tempoQuandoParado = 0;
				btIniciar.setText("Parar");
				isClickPause = true;
			}
			break;
		case R.id.btSalvar:
			Abdominal ab = new Abdominal();
			if (CompsUtils.idAluno == -1)
				ab.setOutrosDados(CompsUtils.outrosDados);
			else
				ab.setIdAluno(""+CompsUtils.idAluno);
			ab.setIdAvaliador(""+CompsUtils.idAvaliador);
			ab.setData(retornarData());
			ab.setTempo(chronometer1.getText().toString()+"");
			ab.setQtdAbdominais(qtdAbdominais);
			String json = GsonManager.getInstance().toJson(ab);
			dao.insert(json);

			mensagemSalvarSucesso();
			i.setClass(getApplicationContext(), AbdominaisActivity.class);
			
			startActivity(i);
			finish();
			break;
		case R.id.btIncremento:
			qtdAbdominais += 1;
			tvQtdAbdominais.setText(String.valueOf(qtdAbdominais));
			break;
		case R.id.btDecremento:
			qtdAbdominais -= 1;
			if(qtdAbdominais < 0){
				qtdAbdominais = 0;
				tvQtdAbdominais.setText(String.valueOf(qtdAbdominais));
				
			}else{
				tvQtdAbdominais.setText(String.valueOf(qtdAbdominais));
			}
			break;
		case R.id.btResetar:
			chronometer1.stop();
			chronometer1.setText("00:00:0");

			tempoQuandoParado = 0;
			break;
		}
	}
}