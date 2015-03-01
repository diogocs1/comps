package com.comps;

import java.util.ArrayList;

import com.comps.R;
import com.comps.banco.DaoDados;
import com.comps.model.ImpulsaoHorizontal;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ImpulsaoHorizontalActivity extends AbstractValidacaoActivity {

	private EditText etTentativa1,
					 etTentativa2;
	private Button btSalvar;
	
	private int tentativa1,
				tentativa2;
	
	private int result;

	private DaoDados dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.impulsao_horizontal);
		
		etTentativa1 = (EditText) findViewById(R.id.etT1);
		etTentativa2 = (EditText) findViewById(R.id.etT2);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);
		dao = DaoDados.getInstance(getApplicationContext());
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		
		Log.d("Abstract", "1");
		Log.d("Abstract", "2");
		tentativa1 = Integer.parseInt(etTentativa1.getText().toString());
		tentativa2 = Integer.parseInt(etTentativa2.getText().toString());
		if (tentativa1 > tentativa2){
			result = tentativa1;
		}
		else{
			result = tentativa2;
		}
		Log.d("Abstract", "3");
		ImpulsaoHorizontal im = new ImpulsaoHorizontal();
		if (CompsUtils.idAluno == -1)
			im.setOutrosDados(CompsUtils.outrosDados);
		else
			im.setIdAluno(""+CompsUtils.idAluno);
		im.setIdAvaliador(""+CompsUtils.idAvaliador);
		
		Log.d("Abstract", "4");
		im.setResult(result);

		
		im.setData(retornarData());
		Log.d("Abstract", "5");
		
		String json = GsonManager.getInstance().toJson(im);
		Log.d("Abstract", "6");
		
		Log.d("","" + dao.insert(json));
		
		
		
		ArrayList<String> lista = dao.listAll();
		
		Log.d("entrou","passou3");
		
		for (String s : lista){
			Log.d("string", s);
		}
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), ImpulsaoHorizontalActivity.class);
		startActivity(i);
		finish();
	}
}
