package com.comps;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comps.banco.DaoDados;
import com.comps.model.PreensaoManual;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class PrensaoManualActivity extends AbstractValidacaoActivity{

	private EditText etTentativa1,
					 etTentativa2;
	private Button btSalvar;

	private int tentativa1,tentativa2,result;
	private DaoDados dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prensao_manual);

		etTentativa1 = (EditText) findViewById(R.id.etT1);
		etTentativa2 = (EditText) findViewById(R.id.etT2);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		dao = DaoDados.getInstance(getApplicationContext());
		btSalvar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();

		Log.d("Abstract", "1");
				Log.d("Abstract", "2");
				tentativa1 = Integer.parseInt(etTentativa1.getText().toString());
				tentativa2 = Integer.parseInt(etTentativa2.getText().toString());
				Log.d("Abstract", "3");
				if (tentativa1 > tentativa2){
					result = tentativa1;
				}
				else{
					result = tentativa2;
				}
				PreensaoManual pm = new PreensaoManual();

				if (CompsUtils.idAluno == -1)
					pm.setOutrosDados(CompsUtils.outrosDados);
				else
					pm.setIdAluno(""+CompsUtils.idAluno);
				pm.setIdAvaliador(""+CompsUtils.idAvaliador);

				Log.d("Abstract", "4");
				pm.setResult(result);
				pm.setData(retornarData());
				Log.d("Abstract", "5");

				String json = GsonManager.getInstance().toJson(pm);
				Log.d("Abstract", "6");

				Log.d("","" + dao.insert(json));



				ArrayList<String> lista = dao.listAll();

				Log.d("entrou","passou3");

				for (String s : lista){
					Log.d("string", s);
				}
				mensagemSalvarSucesso();
				i.setClass(getApplicationContext(), PrensaoManualActivity.class);
				startActivity(i);
				finish();
	}

}
