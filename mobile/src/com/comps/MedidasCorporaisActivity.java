package com.comps;

import java.util.ArrayList;

import com.comps.R;
import com.comps.banco.DaoDados;
import com.comps.model.MedidasCorporais;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MedidasCorporaisActivity extends AbstractValidacaoActivity {

	private EditText etMassaCorporal,etEstatura,etAlturaSentado,etEnvergadura;
	private Button btSalvar;

	private float massaCorporal,estatura,alturaSentado,envergadura;
	private DaoDados dao;
	private final String TAG = "MedidasCorporaisActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.massa_estatura);


		etMassaCorporal = (EditText) findViewById(R.id.etMassaCorporal);
		etEstatura = (EditText) findViewById(R.id.etEstatura);
		etAlturaSentado = (EditText) findViewById(R.id.etAlturaSentado);
		etEnvergadura = (EditText) findViewById(R.id.etEnvergadura);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);

		dao = DaoDados.getInstance(getApplicationContext());

	}
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		String massa = etMassaCorporal.getText().toString();
		String est = etEstatura.getText().toString();
		String at =  etAlturaSentado.getText().toString();
		String enver = etEnvergadura.getText().toString();
		
		massaCorporal = Float.parseFloat(eliminarError(massa));
		estatura = Float.parseFloat(eliminarError(est));
		alturaSentado = Float.parseFloat(eliminarError(at));
		envergadura = Float.parseFloat(eliminarError(enver));

		MedidasCorporais mc = new MedidasCorporais();

		if (CompsUtils.idAluno == -1)
			mc.setOutrosDados(CompsUtils.outrosDados);
		else
			mc.setIdAluno(""+CompsUtils.idAluno);
		mc.setIdAvaliador(""+CompsUtils.idAvaliador);
		mc.setAlturaSentado(alturaSentado);
		mc.setEnvergadura(envergadura);
		mc.setEstatura(estatura);
		mc.setMassaCorporal(massaCorporal);
		mc.setData(retornarData());
		
		String json = GsonManager.getInstance().toJson(mc);
		dao.insert(json);

		ArrayList<String> lista = dao.listAll();
		
		for (String s : lista){
			Log.d(TAG, s);
		}
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), MedidasCorporaisActivity.class);
		startActivity(i);
		finish();
	}
}
