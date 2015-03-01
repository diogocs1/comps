package com.comps;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comps.banco.DaoDados;
import com.comps.model.Perimetros;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class PerimetrosActivity extends AbstractValidacaoActivity {

	private EditText etPerimetroC,
					 etPerimetroA,
					 etPerimetroQ;
	private float perimetroC,
				  perimetroA,
				  perimetroQ;
	private Button btSalvar;
	private final String TAG = "PerimetrosActivity";

	private DaoDados dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perimetros);

		etPerimetroC = (EditText) findViewById(R.id.etPerimetroCintura);

		etPerimetroA = (EditText) findViewById(R.id.etPerimetroAbdominal);

		etPerimetroQ = (EditText) findViewById(R.id.etPerimetroQuadril);


		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);
		dao = DaoDados.getInstance(getApplicationContext());
	}

	@Override
	public void onClick(View v) {
		
		Intent i = new Intent();
		String perimetroCS = etPerimetroA.getText().toString();
		String perimetroCA = etPerimetroC.getText().toString();
		String perimetroCQ = etPerimetroQ.getText().toString();
		Log.d("entrou","passou1");
		perimetroC = Float.parseFloat(eliminarError(perimetroCS));
		perimetroA = Float.parseFloat(eliminarError(perimetroCA));
		perimetroQ = Float.parseFloat(eliminarError(perimetroCQ));

		Perimetros pe = new Perimetros();

		if (CompsUtils.idAluno == -1)
			pe.setOutrosDados(CompsUtils.outrosDados);
		else
			pe.setIdAluno(""+CompsUtils.idAluno);
		pe.setIdAvaliador(""+CompsUtils.idAvaliador);
		pe.setPerimetroAbdomen(perimetroA);
		pe.setPerimetroCintura(perimetroC);
		pe.setPerimetroQuadril(perimetroQ);
		pe.setData(retornarData());

		Log.d("entrou","passou2");
		String json = GsonManager.getInstance().toJson(pe);
		Log.d("","" + dao.insert(json));

		Log.d("entrou",json);
		ArrayList<String> lista = dao.listAll();
		Log.d("entrou","passou3");
		for (String s : lista){
			Log.d(TAG, s);
		}
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), PerimetrosActivity.class);
		startActivity(i);
		finish();
	}
}
