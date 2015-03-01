package com.comps;

import java.util.concurrent.ExecutionException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.comps.util.AndroidUtils;
import com.comps.util.CompsUtils;
import com.comps.web.TaskEnviar;

public class MainActivity extends AbstractValidacaoActivity implements OnClickListener{

	private Button btAntropometria;
	private Button btKTK;
	private Button btAptidaoFisica;
	private Button btSincronizar;
//	private final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btKTK = (Button) findViewById(R.id.btKTK);
		btKTK.setOnClickListener(this);

		btAntropometria = (Button) findViewById(R.id.btAntropometria);
		btAntropometria.setOnClickListener(this);

		btAptidaoFisica = (Button) findViewById(R.id.btAptidaoFisica);
		btAptidaoFisica.setOnClickListener(this);

		btSincronizar  = (Button) findViewById(R.id.btSincronizar);
		btSincronizar.setOnClickListener(this);
		
		if (!CompsUtils.isOnline){
			btSincronizar.setVisibility(View.INVISIBLE);
		}else{
			btSincronizar.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		Log.d("MainAc", "1");
		switch (v.getId()) {
		
		case R.id.btSincronizar:
			//			i.setClass(getApplicationContext(), DobrasCutaneasActivity.class);
			//			startActivity(i);
			if(AndroidUtils.isNetworkAvailable(getApplicationContext())){
				AsyncTask<Void,Void,Boolean> result = new TaskEnviar(getApplicationContext()).execute();
				try {
					if (result.get()){
						mensagemSincronizar();
					}else{
						mensagemSincronizarErro();
					}
				} catch (InterruptedException e) {e.printStackTrace();
				} catch (ExecutionException e) {e.printStackTrace();}
			}else{
				msgNoInternet();
			}
			break;
			
		case R.id.btAptidaoFisica:
			i.setClass(getApplicationContext(), MainAptidaoFisicaActivity.class);
			startActivity(i);
			break;
			
		case R.id.btAntropometria:
			Log.d("MainAc", "3");
			i.setClass(getApplicationContext(), MainAntropometriaActivity.class);
			startActivity(i);
			break;
		case R.id.btKTK:
			Log.d("MainAc", "4");
			i.setClass(getApplicationContext(), MainKTKActivity.class);
			startActivity(i);
			break;

		}
	}
}