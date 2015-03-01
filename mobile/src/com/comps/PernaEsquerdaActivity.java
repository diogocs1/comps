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
import com.comps.model.PernaEsquerda;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

public class PernaEsquerdaActivity extends AbstractValidacaoActivity
					implements OnItemSelectedListener{
		private EditText et1PE,
						 et2PE,
						 et3PE,
						 et4PE,
						 et5PE,
						 et6PE;
		
		private EditText et7PE,
						 et8PE,
						 et9PE,
						 et10PE,
						 et11PE,
						 et12PE,
						 etR;
		
		private Spinner spnPlacas,spnPontos;
		private static final String[] qtPlacas = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		private static final String[] pontos = {"0","1","2","3"};
		private ArrayAdapter<String> qtAPlacas;
		private ArrayAdapter<String> aPontos;
		private Button btSalvaPE;
		private DaoDados dao;
		private int resultado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.perna_esquerda);
		
		et1PE = (EditText) findViewById(R.id.et1PE);
		et1PE.setText(String.valueOf(0));
		
		et2PE = (EditText) findViewById(R.id.et2PE);
		et2PE.setText(String.valueOf(0));
		
		et3PE = (EditText) findViewById(R.id.et3PE);
		et3PE.setText(String.valueOf(0));
		
		et4PE = (EditText) findViewById(R.id.et4PE);
		et4PE.setText(String.valueOf(0));
		
		et5PE = (EditText) findViewById(R.id.et5PE);
		et5PE.setText(String.valueOf(0));
		
		et6PE = (EditText) findViewById(R.id.et6PE);
		et6PE.setText(String.valueOf(0));
		
		et7PE = (EditText) findViewById(R.id.et7PE);
		et7PE.setText(String.valueOf(0));
		
		et8PE = (EditText) findViewById(R.id.et8PE);
		et8PE.setText(String.valueOf(0));
		
		et9PE = (EditText) findViewById(R.id.et9PE);
		et9PE.setText(String.valueOf(0));
		
		et10PE = (EditText) findViewById(R.id.et10PE);
		et10PE.setText(String.valueOf(0));
		
		et11PE = (EditText) findViewById(R.id.et11PE);
		et11PE.setText(String.valueOf(0));
		
		et12PE = (EditText) findViewById(R.id.et12PE);
		et12PE.setText(String.valueOf(0));
		
		etR = (EditText) findViewById(R.id.etRes);
		etR.setText(String.valueOf(0));
		
		qtAPlacas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,qtPlacas);
		aPontos = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,pontos);
		
		spnPlacas = (Spinner) findViewById(R.id.spinnerQuantidadeDePlacasPE);
		spnPlacas.setAdapter(qtAPlacas);
		spnPlacas.setOnItemSelectedListener(this);
		
		spnPontos = (Spinner) findViewById(R.id.spinnerValoresPE);
		spnPontos.setAdapter(aPontos);
		spnPontos.setOnItemSelectedListener(this);
		
		
		btSalvaPE = (Button) findViewById(R.id.btSalvarPE);
		btSalvaPE.setOnClickListener(this);
		
		dao = DaoDados.getInstance(getApplicationContext());
	}
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch(v.getId()){
			
		case R.id.btSalvarPE:
			Log.d("trans", "1");
			resultado = Integer.parseInt(etR.getText().toString());
			
			PernaEsquerda pe = new PernaEsquerda(); 
			if (CompsUtils.idAluno == -1)
				pe.setOutrosDados(CompsUtils.outrosDados);
			else
				pe.setIdAluno(""+CompsUtils.idAluno);
			pe.setIdAvaliador(""+CompsUtils.idAvaliador);
			pe.setData(retornarData());
			pe.setResultado(resultado);
			Log.d("trans", "2");
			String json = GsonManager.getInstance().toJson(pe);
			dao.insert(json);
			Log.d("trans", "3");
			ArrayList<String> lista = dao.listAll();
			Log.d("trans","passou");
			for (String s : lista){
				Log.d("trans", s);
			}
			mensagemSalvarSucesso();
			i.setClass(getApplicationContext(), PernaDireitaActivity.class);
			startActivity(i);
			finish();	
		}
	}
	public void setPontuacao(Spinner pontuacao,Spinner plac){
		int point = 0,placa = 0;
		placa = Integer.parseInt((String) plac.getSelectedItem()); 
		point = Integer.parseInt((String) pontuacao.getSelectedItem());
		
		if (point > 0){preencherEspacosVagos(placa);}
		
		if (placa == 1){et1PE.setText(String.valueOf(point));}
		
		else if (placa == 2){et2PE.setText(String.valueOf(point));}
		
		else if (placa == 3){et3PE.setText(String.valueOf(point));}
		
		else if (placa == 4){et4PE.setText(String.valueOf(point));}
		
		else if (placa == 5){et5PE.setText(String.valueOf(point));}
		
		else if (placa == 6){et6PE.setText(String.valueOf(point));}
		
		else if (placa == 7){et7PE.setText(String.valueOf(point));}
		
		else if (placa == 8){et8PE.setText(String.valueOf(point));}
		
		else if (placa == 9){et9PE.setText(String.valueOf(point));}
		
		else if (placa == 10){et10PE.setText(String.valueOf(point));}
		
		else if (placa == 11){et11PE.setText(String.valueOf(point));}
		
		else if (placa == 12){et12PE.setText(String.valueOf(point));}
		
		
		calculoPotuacaoTotal(et1PE, et2PE, et3PE, et4PE, et5PE, et6PE, et7PE, et8PE, et9PE,
				et10PE, et11PE, et12PE, etR);
		
		
	}
	void preencherEspacosVagos(int qtPlaca){
		
			switch(qtPlaca){
			
			case 12: et11PE.setText(String.valueOf(3));
			case 11: et10PE.setText(String.valueOf(3));
			case 10: et9PE.setText(String.valueOf(3));
			case 9: et8PE.setText(String.valueOf(3));
			case 8: et7PE.setText(String.valueOf(3));
			case 7: et6PE.setText(String.valueOf(3));
			case 6: et5PE.setText(String.valueOf(3));
			case 5: et4PE.setText(String.valueOf(3));
			case 4: et3PE.setText(String.valueOf(3));
			case 3: et2PE.setText(String.valueOf(3));
			case 2: et1PE.setText(String.valueOf(3));
			
			}
			
		}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		switch(arg0.getId()){
			case R.id.spinnerQuantidadeDePlacasPE:
			case R.id.spinnerValoresPE:
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
}

