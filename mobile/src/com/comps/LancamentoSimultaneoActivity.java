package com.comps;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comps.banco.DaoDados;
import com.comps.model.LancamentoSimultaneo;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class LancamentoSimultaneoActivity extends AbstractValidacaoActivity {


	private EditText etTentativa1,
					 etTentativa2;
	private int tentativa1,
				tentativa2,
				result;
	private Button btSalvar;
	private DaoDados dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lancamento_simultaneo);

		etTentativa1 = (EditText) findViewById(R.id.etLancamentoST1);
		etTentativa2 = (EditText) findViewById(R.id.etLancamentoST2);
		dao = DaoDados.getInstance(getApplicationContext());
		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);
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
		LancamentoSimultaneo ls = new LancamentoSimultaneo();
		if (CompsUtils.idAluno == -1)
			ls.setOutrosDados(CompsUtils.outrosDados);
		else
			ls.setIdAluno(""+CompsUtils.idAluno);
		ls.setIdAvaliador(""+CompsUtils.idAvaliador);

		Log.d("Abstract", "4");
		ls.setResult(result);
		ls.setData(retornarData());
		Log.d("Abstract", "5");

		String json = GsonManager.getInstance().toJson(ls);
		Log.d("Abstract", "6");

		Log.d("","" + dao.insert(json));



		ArrayList<String> lista = dao.listAll();

		Log.d("entrou","passou3");

		for (String s : lista){
			Log.d("string", s);
		}
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), LancamentoSimultaneoActivity.class);
		startActivity(i);
		finish();
	}

}

