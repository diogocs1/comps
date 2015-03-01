package com.comps;
import java.util.ArrayList;

import com.comps.R;
import com.comps.banco.DaoDados;
import com.comps.model.EquilibrioRetaguarda;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EquilibrioRetaguardaActivity extends AbstractValidacaoActivity implements OnItemSelectedListener{
	
	private EditText etR1,etR2,etR3,etResult;
	private static final String[] pontuacao = {"0","1","2","3","4","5","6","7","8"};
	private ArrayAdapter<String> aPontuacao;
	private Spinner spnT1B1,spnT1B2,spnT1B3;
	private Spinner spnT2B1,spnT2B2,spnT2B3;
	private Spinner spnT3B1,spnT3B2,spnT3B3;
	private Button btSalvar;
	
	private DaoDados dao;
	
	private float result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.equilibrio_retaguarda);
		
		etR1 = (EditText) findViewById(R.id.etR1);
		etR2 = (EditText) findViewById(R.id.etR2);
		etR3 = (EditText) findViewById(R.id.etR3);
		

		etResult = (EditText) findViewById(R.id.etPontuacaoFinal);
		aPontuacao = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pontuacao);
		btSalvar = (Button) findViewById(R.id.btSalvarER);
		btSalvar.setOnClickListener(this);
		
		spnT1B1 = (Spinner) findViewById(R.id.spnT1B1);
		spnT1B1.setAdapter(aPontuacao);
		spnT1B1.setOnItemSelectedListener(this);
		
		
		spnT1B2 = (Spinner) findViewById(R.id.spnT1B2);
		spnT1B2.setAdapter(aPontuacao);
		spnT1B2.setOnItemSelectedListener(this);

		
		spnT1B3 = (Spinner) findViewById(R.id.spnT1B3);
		spnT1B3.setAdapter(aPontuacao);
		spnT1B3.setOnItemSelectedListener(this);
		
		spnT2B1 = (Spinner) findViewById(R.id.spnT2B1);
		spnT2B1.setAdapter(aPontuacao);
		spnT2B1.setOnItemSelectedListener(this);
		
		spnT2B2 = (Spinner) findViewById(R.id.spnT2B2);
		spnT2B2.setAdapter(aPontuacao);
		spnT2B2.setOnItemSelectedListener(this);
		
		spnT2B3 = (Spinner) findViewById(R.id.spnT2B3);
		spnT2B3.setAdapter(aPontuacao);
		spnT2B3.setOnItemSelectedListener(this);
		
		spnT3B1 = (Spinner) findViewById(R.id.spnT3B1);
		spnT3B1.setAdapter(aPontuacao);
		spnT3B1.setOnItemSelectedListener(this);
		
		spnT3B2 = (Spinner) findViewById(R.id.spnT3B2);
		spnT3B2.setAdapter(aPontuacao);
		spnT3B2.setOnItemSelectedListener(this);
		
		spnT3B3 = (Spinner) findViewById(R.id.spnT3B3);
		spnT3B3.setAdapter(aPontuacao);
		spnT3B3.setOnItemSelectedListener(this);
		
		dao = DaoDados.getInstance(getApplicationContext());
	}
	
	public void somaDePontosParciais(Spinner spn1,Spinner spn2,Spinner spn3, EditText etResult){

		int num1=0,num2=0,num3=0,result=0;
		try{
			num1 = Integer.parseInt((String) spn1.getSelectedItem());	
			num2 = Integer.parseInt((String) spn2.getSelectedItem());
			num3 = Integer.parseInt((String) spn3.getSelectedItem());
			
			Log.d("entrou","" + spn3.getSelectedItemPosition());
		}catch(Exception e){}
		
		result = num1+num2+num3;
		etResult.setText(String.valueOf(result));
	}
	
	public void somaDePontosTotais(EditText etR1,EditText etR2, EditText etR3, EditText etResult){
		int num1=0,num2=0,num3=0,result=0;
		try{
			num1 = Integer.parseInt(etR1.getText().toString());
			num2 = Integer.parseInt(etR2.getText().toString());
			num3 = Integer.parseInt(etR3.getText().toString());
		}catch(Exception e){}
		result = num1+num2+num3;
		etResult.setText(String.valueOf(result));
	}
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		Log.d("entrou", "1");
		result = Float.parseFloat(etResult.getText().toString());
		EquilibrioRetaguarda er = new EquilibrioRetaguarda();
		er.setResult(result);
		er.setIdAvaliador(""+CompsUtils.idAvaliador);
		if (CompsUtils.idAluno == -1)
			er.setOutrosDados(CompsUtils.outrosDados);
		else
			er.setIdAluno(""+CompsUtils.idAluno);
		er.setData(retornarData());
		
		
		String json = GsonManager.getInstance().toJson(er);
		dao.insert(json);
		
		ArrayList<String> lista = dao.listAll();
		Log.d("lalala","passou");
		for (String s : lista){
			Log.d("lalala", s);
		}
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), EquilibrioRetaguardaActivity.class);
		startActivity(i);
		finish();
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
			switch(arg0.getId()){
			
			case R.id.spnT1B1:
			case R.id.spnT1B2:
			case R.id.spnT1B3:
				somaDePontosParciais(spnT1B1, spnT1B2, spnT1B3, etR1);
				somaDePontosTotais(etR1, etR2, etR3, etResult);
				break;
				
			case R.id.spnT2B1:
			case R.id.spnT2B2:
			case R.id.spnT2B3:
				somaDePontosParciais(spnT2B1, spnT2B2, spnT2B3, etR2);
				somaDePontosTotais(etR1, etR2, etR3, etResult);
				break;
				
			case R.id.spnT3B1:
			case R.id.spnT3B2:
			case R.id.spnT3B3:
				somaDePontosParciais(spnT3B1, spnT3B2, spnT3B3, etR3);
				somaDePontosTotais(etR1, etR2, etR3, etResult);
				break;
				
			}
			
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}