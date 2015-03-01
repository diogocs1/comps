package com.comps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainAntropometriaActivity extends Activity implements OnClickListener{

	
	private Button btMedidasCorporaes;
	private Button btPerimetros;
	private Button btDobrasCutaneas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_antropometria);
		
		btMedidasCorporaes = (Button) findViewById(R.id.btMedidasCorporais);
		btMedidasCorporaes.setOnClickListener(this);

		btPerimetros = (Button) findViewById(R.id.btPerimetros);
		btPerimetros.setOnClickListener(this);

		btDobrasCutaneas = (Button) findViewById(R.id.btDobrasCutaneas);
		btDobrasCutaneas.setOnClickListener(this);

		
	}
	
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch (v.getId()) {
		
		case R.id.btDobrasCutaneas:
			i.setClass(getApplicationContext(), DobrasCutaneasActivity.class);
			startActivity(i);
			break;
			
		case R.id.btMedidasCorporais:
			i.setClass(getApplicationContext(), MedidasCorporaisActivity.class);
			startActivity(i);
			break;
			
		case R.id.btPerimetros:
			i.setClass(getApplicationContext(), PerimetrosActivity.class);
			startActivity(i);
			break;
					
		}
	}
}