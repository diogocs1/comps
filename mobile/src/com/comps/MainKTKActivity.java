package com.comps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainKTKActivity extends Activity implements OnClickListener{

	
	private Button btEquilibrioRetaguarda;
	private Button btSaltosLaterias15s;
	private Button btSaltosLaterias20s;
	private Button btSaltosMonopedais;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_ktk);
		
		btEquilibrioRetaguarda = (Button) findViewById(R.id.btEquilibrioRetaguarda);
		btEquilibrioRetaguarda.setOnClickListener(this);
		
		btSaltosLaterias15s = (Button) findViewById(R.id.btSaltosLaterais15);
		btSaltosLaterias15s.setOnClickListener(this);
		
		btSaltosLaterias20s = (Button) findViewById(R.id.btTransposicaoLaterais20);
		btSaltosLaterias20s.setOnClickListener(this);
		
		btSaltosMonopedais  = (Button) findViewById(R.id.btSaltosMonopedais);
		btSaltosMonopedais.setOnClickListener(this);
		
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch (v.getId()) {
		
		case R.id.btSaltosLaterais15:
			i.setClass(getApplicationContext(), SaltosLaterais15Activity.class);
			startActivity(i);
			break;
			
		case R.id.btTransposicaoLaterais20: 
					i.setClass(getApplicationContext(), TransposicoesLaterais20Activity.class);
					startActivity(i);
					break;
		case R.id.btSaltosMonopedais:
			i.setClass(getApplicationContext(), PernaDireitaActivity.class);
			startActivity(i);
			break;
		case R.id.btEquilibrioRetaguarda: 
			i.setClass(getApplicationContext(), EquilibrioRetaguardaActivity.class);
			startActivity(i);
			break;
					
		}
	}
}