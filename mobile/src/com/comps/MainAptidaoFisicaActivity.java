package com.comps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainAptidaoFisicaActivity extends Activity implements OnClickListener{

	
	private Button btImpulsaoHorizontal;
	private Button btPressaoManual;
	private Button btAbdominais;
	private Button btLacamentoSimultaneo;
	private Button btLancamentoUnilateral;
	private Button btSentarAlcancar;
	private Button btCorrida;
	private Button bt10_5_Shut;
	private Button bt20m_Shut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_aptidao_fisica);
		
			
		btImpulsaoHorizontal  = (Button) findViewById(R.id.btImpulsaoHorizontal);
		btImpulsaoHorizontal.setOnClickListener(this);
		
		btPressaoManual  = (Button) findViewById(R.id.btPreensaoManual);
		btPressaoManual.setOnClickListener(this);
		
		btSentarAlcancar = (Button) findViewById(R.id.btSentarAlcancar);
		btSentarAlcancar.setOnClickListener(this);
		
		btLacamentoSimultaneo = (Button) findViewById(R.id.btLancamentoSimultaneo);
		btLacamentoSimultaneo.setOnClickListener(this);
		
		btAbdominais  = (Button) findViewById(R.id.btAbdominais);
		btAbdominais.setOnClickListener(this);
		
		btCorrida  = (Button) findViewById(R.id.btCorrida);
		btCorrida.setOnClickListener(this);
		
		btLancamentoUnilateral  = (Button) findViewById(R.id.btLancamento);
		btLancamentoUnilateral.setOnClickListener(this);
		
		bt10_5_Shut  = (Button) findViewById(R.id.btShuttle_10_5);
		bt10_5_Shut.setOnClickListener(this);
		
		bt20m_Shut  = (Button) findViewById(R.id.btShuttle_20m);
		bt20m_Shut.setOnClickListener(this);
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch (v.getId()) {
		
		case R.id.btImpulsaoHorizontal:
			i.setClass(getApplicationContext(), ImpulsaoHorizontalActivity.class);
			startActivity(i);
			break;
			
		case R.id.btPreensaoManual:
			i.setClass(getApplicationContext(), PrensaoManualActivity.class);
			startActivity(i);
			break;
					
		case R.id.btLancamentoSimultaneo:
			i.setClass(getApplicationContext(), LancamentoSimultaneoActivity.class);
			startActivity(i);
			break;
			
		case R.id.btSentarAlcancar:
			i.setClass(getApplicationContext(), SentarAlcancarActivity.class);
			startActivity(i);
			break;
			
					
		case R.id.btAbdominais:
			i.setClass(getApplicationContext(), AbdominaisActivity.class);
			startActivity(i);
			break;
			
		case R.id.btLancamento:
			i.setClass(getApplicationContext(), LancamentoUnilateralActivity.class);
			startActivity(i);
			break;
			
		case R.id.btCorrida:
			i.setClass(getApplicationContext(), CorridaActivity.class);
			startActivity(i);
			break;
			
		case R.id.btShuttle_10_5:
			i.setClass(getApplicationContext(), ShuttleRun_10x5_Activity.class);
     		startActivity(i);
			break;
			
		case R.id.btShuttle_20m:
			i.setClass(getApplicationContext(), ShuttleRun_20m_Activity.class);
			startActivity(i);
					
		}
	}
}