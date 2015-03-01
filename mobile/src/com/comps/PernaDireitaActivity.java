package com.comps;

import java.util.ArrayList;

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
import android.widget.TextView;

import com.comps.banco.DaoDados;
import com.comps.model.PernaDireita;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;


public class PernaDireitaActivity extends AbstractValidacaoActivity
					implements OnItemSelectedListener {
	
	private EditText et1PD,
					 et2PD,
					 et3PD,
					 et4PD,
					 et5PD,
					 et6PD;
	
	private EditText et7PD,
					 et8PD,
					 et9PD,
					 et10PD,
					 et11PD,
					 et12PD,
					 etR;
	boolean controle = true;
	private Spinner spnPlacas,spnPontos;
	private static final String[] qtPlacas = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private static final String[] pontos = {"0","1","2","3"};
	private ArrayAdapter<String> qtAPlacas;
	private ArrayAdapter<String> aPontos;
	private Button btSalvaPD;
	private int resultado;
	private DaoDados dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perna_direita);
		
		et1PD = (EditText) findViewById(R.id.et1PD);
		et1PD.setText(String.valueOf(0));
		
		et2PD = (EditText) findViewById(R.id.et2PD);
		et2PD.setText(String.valueOf(0));
		
		et3PD = (EditText) findViewById(R.id.et3PD);
		et3PD.setText(String.valueOf(0));
		
		et4PD = (EditText) findViewById(R.id.et4PD);
		et4PD.setText(String.valueOf(0));
		
		et5PD = (EditText) findViewById(R.id.et5PD);
		et5PD.setText(String.valueOf(0));
		
		et6PD = (EditText) findViewById(R.id.et6PD);
		et6PD.setText(String.valueOf(0));
		
		et7PD = (EditText) findViewById(R.id.et7PD);
		et7PD.setText(String.valueOf(0));
		
		et8PD = (EditText) findViewById(R.id.et8PD);
		et8PD.setText(String.valueOf(0));
		
		et9PD = (EditText) findViewById(R.id.et9PD);
		et9PD.setText(String.valueOf(0));
		
		et10PD = (EditText) findViewById(R.id.et10PD);
		et10PD.setText(String.valueOf(0));
		
		et11PD = (EditText) findViewById(R.id.et11PD);
		et11PD.setText(String.valueOf(0));
		
		et12PD = (EditText) findViewById(R.id.et12PD);
		et12PD.setText(String.valueOf(0));
		
		etR  = (EditText) findViewById(R.id.etResult);
		etR.setText(String.valueOf(0));
		
		
		qtAPlacas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,qtPlacas);
		aPontos = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,pontos);
		
		spnPlacas = (Spinner) findViewById(R.id.spinnerQuantidadeDePlacasPD);
		spnPlacas.setAdapter(qtAPlacas);
		spnPlacas.setOnItemSelectedListener(this);
		
		spnPontos = (Spinner) findViewById(R.id.spinnerValoresPD);
		spnPontos.setAdapter(aPontos);
		spnPontos.setOnItemSelectedListener(this);
		
		
		btSalvaPD = (Button) findViewById(R.id.btSalvarPD);
		btSalvaPD.setOnClickListener(this);
		
		dao = DaoDados.getInstance(getApplicationContext());
		
	}
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		
		switch(v.getId()){
			
		case R.id.btSalvarPD:
			
			
			Log.d("PernaDireita","metodoOnclick");
			Log.d("trans", "1");
			resultado = Integer.parseInt(etR.getText().toString());
			
			PernaDireita pd = new PernaDireita(); 
			if (CompsUtils.idAluno == -1)
				pd.setOutrosDados(CompsUtils.outrosDados);
			else
				pd.setIdAluno(""+CompsUtils.idAluno);
			pd.setIdAvaliador(""+CompsUtils.idAvaliador);
			pd.setData(retornarData());
			pd.setResultado(resultado);
			Log.d("trans", "2");
			String json = GsonManager.getInstance().toJson(pd);
			dao.insert(json);
			Log.d("trans", "3");
			ArrayList<String> lista = dao.listAll();
			Log.d("trans","passou");
			for (String s : lista){
				Log.d("trans", s);
			}
			mensagemSalvarSucesso();
			i.setClass(getApplicationContext(), PernaEsquerdaActivity.class);
			startActivity(i);
			finish();			
		}
	}
	public void setPontuacao(Spinner pontuacao,Spinner plac){
		int point = 0,placa = 0;
		try{
			placa = Integer.parseInt((String) plac.getSelectedItem()); 
			point = Integer.parseInt((String) pontuacao.getSelectedItem());
		}catch(Exception e){
			
		}
		
		if(point  > 0){
			preencherEspacosVagos(placa);
		}
		
		if (placa == 1){et1PD.setText(String.valueOf(point));}
		
		else if (placa == 2){et2PD.setText(String.valueOf(point));}
		
		else if (placa == 3){et3PD.setText(String.valueOf(point));}
		
		else if (placa == 4){et4PD.setText(String.valueOf(point));}
		
		else if (placa == 5){et5PD.setText(String.valueOf(point));}
		
		else if (placa == 6){et6PD.setText(String.valueOf(point));}
		
		else if (placa == 7){et7PD.setText(String.valueOf(point));}
		
		else if (placa == 8){et8PD.setText(String.valueOf(point));}
		
		else if (placa == 9){et9PD.setText(String.valueOf(point));}
		
		else if (placa == 10){et10PD.setText(String.valueOf(point));}
		
		else if (placa == 11){et11PD.setText(String.valueOf(point));}
		
		else if (placa == 12){et12PD.setText(String.valueOf(point));}
		
		calculoPotuacaoTotal(et1PD, et2PD, et3PD, et4PD, et5PD, et6PD, et7PD,
				et8PD, et9PD, et10PD, et11PD, et12PD, etR);
	}
	void preencherEspacosVagos(int qtPlaca){
		
		switch(qtPlaca){
		
		case 12: et11PD.setText(String.valueOf(3));
		case 11: et10PD.setText(String.valueOf(3));
		case 10: et9PD.setText(String.valueOf(3));
		case 9: et8PD.setText(String.valueOf(3));
		case 8: et7PD.setText(String.valueOf(3));
		case 7: et6PD.setText(String.valueOf(3));
		case 6: et5PD.setText(String.valueOf(3));
		case 5: et4PD.setText(String.valueOf(3));
		case 4: et3PD.setText(String.valueOf(3));
		case 3: et2PD.setText(String.valueOf(3));
		case 2: et1PD.setText(String.valueOf(3));
		
		}
		
	}
	public void calculoPotuacaoTotal(EditText edt1, EditText edt2,EditText edt3,EditText edt4,EditText edt5,EditText edt6,
			EditText edt7,EditText edt8,EditText edt9,EditText edt10,EditText edt11,EditText edt12,TextView resultado){
		
		int num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,result;
		
		num1 = Integer.parseInt(edt1.getText().toString());
		num2 = Integer.parseInt(edt2.getText().toString());
		num3 = Integer.parseInt(edt3.getText().toString());
		num4 = Integer.parseInt(edt4.getText().toString());
		num5 = Integer.parseInt(edt5.getText().toString());
		num6 = Integer.parseInt(edt6.getText().toString());
		num7 = Integer.parseInt(edt7.getText().toString());
		num8 = Integer.parseInt(edt8.getText().toString());
		num9 = Integer.parseInt(edt9.getText().toString());
		num10 = Integer.parseInt(edt10.getText().toString());
		num11 = Integer.parseInt(edt11.getText().toString());
		num12 = Integer.parseInt(edt12.getText().toString());
		result = num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12;
		
		resultado.setText(String.valueOf(result));
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		switch(arg0.getId()){
			case R.id.spinnerQuantidadeDePlacasPD:
			case R.id.spinnerValoresPD:
				try{
					setPontuacao(spnPontos,spnPlacas);
					
				}catch(Exception e){
					
				}
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
