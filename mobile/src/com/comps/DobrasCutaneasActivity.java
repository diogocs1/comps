package com.comps;

/** 
 * @author Danylo Souza(danylosouza1993@gmail.com) 
 */

import java.util.ArrayList;

import com.comps.R;
import com.comps.banco.DaoDados;
import com.comps.model.DobrasCutaneas;
import com.comps.util.CompsUtils;
import com.comps.util.GsonManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

public class DobrasCutaneasActivity extends AbstractValidacaoActivity implements OnFocusChangeListener{

	private EditText etBiceps1,etBiceps2,etBiceps3,etBicepsR;
	private EditText etTriceps1,etTriceps2,etTriceps3,etTricepsR;
	private EditText etSubScapular1,etSubScapular2,etSubScapular3,etSubScapularR;
	private EditText etPeitoral1,etPeitoral2,etPeitoral3,etPeitoralR;
	private EditText etAxilarMedia1,etAxilarMedia2,etAxilarMedia3,etAxilarMediaR;
	private EditText etSupraIliaca1,etSupraIliaca2,etSupraIliaca3,etSupraIliacaR;
	private EditText etSupraEspinhal1,etSupraEspinhal2,etSupraEspinhal3,etSupraEspinhalR;
	private EditText etSupraPatelar1,etSupraPatelar2,etSupraPatelar3,etSupraPatelarR;
	private EditText etAbdominal1,etAbdominal2,etAbdominal3,etAbdominalR;
	private EditText etCoxaMedial1,etCoxaMedial2,etCoxaMedial3,etCoxaMedialR;
	private EditText etPanturrilhaMedial1,etPanturrilhaMedial2,etPanturrilhaMedial3,etPanturrilhaMedialR;

	private float bicepsR;
	private float tricepsR;
	private float subEscapularR;
	private float peitoralR;
	private float axilarMediaR;
	private float supraIliacaR;
	private float supraEspinhalR;
	private float supraPatelarR;
	private float abdominalR;
	private float coxaMedialR;
	private float panturrilhaMedialR;

	private final String TAG = "DobrasCutaneasActivity";

	private DaoDados dao;

	private Button btSalvar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dobras_cutaneas);

		etBiceps1 = (EditText) findViewById(R.id.etBiceps1);
		etBiceps1.setOnFocusChangeListener(this);
		etBiceps2 = (EditText) findViewById(R.id.etBiceps2);
		etBiceps2.setOnFocusChangeListener(this);
		etBiceps3 = (EditText) findViewById(R.id.etBiceps3);
		etBiceps3.setOnFocusChangeListener(this);
		etBicepsR = (EditText) findViewById(R.id.etBicepsRes);
		etBicepsR.setText("0.0");

		etTriceps1 = (EditText) findViewById(R.id.etTriceps1);
		etTriceps1.setOnFocusChangeListener(this);
		etTriceps2 = (EditText) findViewById(R.id.etTriceps2);
		etTriceps2.setOnFocusChangeListener(this);
		etTriceps3 = (EditText) findViewById(R.id.etTriceps3);
		etTriceps3.setOnFocusChangeListener(this);
		etTricepsR = (EditText) findViewById(R.id.etTricepsRes);
		etTricepsR.setText("0.0");

		etSubScapular1 = (EditText) findViewById(R.id.etSubscapular1);
		etSubScapular1.setOnFocusChangeListener(this);
		etSubScapular2 = (EditText) findViewById(R.id.etSubscapular2);
		etSubScapular2.setOnFocusChangeListener(this);
		etSubScapular3 = (EditText) findViewById(R.id.etSubscapular3);
		etSubScapular3.setOnFocusChangeListener(this);
		etSubScapularR = (EditText) findViewById(R.id.etSubscapularRes);
		etSubScapularR.setText("0.0");

		etPeitoral1 = (EditText) findViewById(R.id.etPeitoral1);
		etPeitoral1.setOnFocusChangeListener(this);
		etPeitoral2 = (EditText) findViewById(R.id.etPeitoral2);
		etPeitoral2.setOnFocusChangeListener(this);
		etPeitoral3 = (EditText) findViewById(R.id.etPeitoral3);
		etPeitoral3.setOnFocusChangeListener(this);
		etPeitoralR = (EditText) findViewById(R.id.etPeitoralRes);
		etPeitoralR.setText("0.0");

		etAxilarMedia1 = (EditText) findViewById(R.id.etAxilar1);
		etAxilarMedia1.setOnFocusChangeListener(this);
		etAxilarMedia2 = (EditText) findViewById(R.id.etAxilar2);
		etAxilarMedia2.setOnFocusChangeListener(this);
		etAxilarMedia3 = (EditText) findViewById(R.id.etAxilar3);
		etAxilarMedia3.setOnFocusChangeListener(this);
		etAxilarMediaR = (EditText) findViewById(R.id.etAxilarRes);
		etAxilarMediaR.setText("0.0");

		etSupraIliaca1 = (EditText) findViewById(R.id.etSuprailiaca1);
		etSupraIliaca1.setOnFocusChangeListener(this);
		etSupraIliaca2 = (EditText) findViewById(R.id.etSuprailiaca2);
		etSupraIliaca2.setOnFocusChangeListener(this);
		etSupraIliaca3 = (EditText) findViewById(R.id.etSuprailiaca3);
		etSupraIliaca3.setOnFocusChangeListener(this);
		etSupraIliacaR = (EditText) findViewById(R.id.etSuprailiacaRes);
		etSupraIliacaR.setText("0.0");

		etSupraEspinhal1 = (EditText) findViewById(R.id.etEspinhal1);
		etSupraEspinhal1.setOnFocusChangeListener(this);
		etSupraEspinhal2 = (EditText) findViewById(R.id.etEspinhal2);
		etSupraEspinhal2.setOnFocusChangeListener(this);
		etSupraEspinhal3 = (EditText) findViewById(R.id.etEspinhal3);
		etSupraEspinhal3.setOnFocusChangeListener(this);
		etSupraEspinhalR = (EditText) findViewById(R.id.etEspinhalRes);
		etSupraEspinhalR.setText("0.0");

		etSupraPatelar1 = (EditText) findViewById(R.id.etPatelar1);
		etSupraPatelar1.setOnFocusChangeListener(this);
		etSupraPatelar2 = (EditText) findViewById(R.id.etPatelar2);
		etSupraPatelar2.setOnFocusChangeListener(this);
		etSupraPatelar3 = (EditText) findViewById(R.id.etPatelar3);
		etSupraPatelar3.setOnFocusChangeListener(this);
		etSupraPatelarR = (EditText) findViewById(R.id.etPatelarRes);
		etSupraPatelarR.setText("0.0");

		etAbdominal1 = (EditText) findViewById(R.id.etAbdominal1);
		etAbdominal1.setOnFocusChangeListener(this);
		etAbdominal2 = (EditText) findViewById(R.id.etAbdominal2);
		etAbdominal2.setOnFocusChangeListener(this);
		etAbdominal3 = (EditText) findViewById(R.id.etAbdominal3);
		etAbdominal3.setOnFocusChangeListener(this);
		etAbdominalR = (EditText) findViewById(R.id.etAbdominalRes);
		etAbdominalR.setText("0.0");

		etCoxaMedial1 = (EditText) findViewById(R.id.etCoxaMedial1);
		etCoxaMedial1.setOnFocusChangeListener(this);
		etCoxaMedial2 = (EditText) findViewById(R.id.etCoxaMedial2);
		etCoxaMedial2.setOnFocusChangeListener(this);
		etCoxaMedial3 = (EditText) findViewById(R.id.etCoxaMedial3);
		etCoxaMedial3.setOnFocusChangeListener(this);
		etCoxaMedialR = (EditText) findViewById(R.id.etCoxaMedialRes);
		etCoxaMedialR.setText("0.0");

		etPanturrilhaMedial1 = (EditText) findViewById(R.id.etPanturrilhaMedial1);
		etPanturrilhaMedial1.setOnFocusChangeListener(this);
		etPanturrilhaMedial2 = (EditText) findViewById(R.id.etPanturrilhaMedial2);
		etPanturrilhaMedial2.setOnFocusChangeListener(this);
		etPanturrilhaMedial3 = (EditText) findViewById(R.id.etPanturrilhaMedial3);
		etPanturrilhaMedial3.setOnFocusChangeListener(this);
		etPanturrilhaMedialR = (EditText) findViewById(R.id.etPanturrilhaMedialRes);
		etPanturrilhaMedialR.setText("0.0");

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(this);
		

		dao = DaoDados.getInstance(getApplicationContext());


	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch(v.getId()){

		case R.id.etBiceps1:	
		case R.id.etBiceps2:
		case R.id.etBiceps3:
			mediaGeral(etBiceps1, etBiceps2,etBiceps3, etBicepsR);
			break;

		case R.id.etTriceps1:	
		case R.id.etTriceps2:
		case R.id.etTriceps3:
			mediaGeral(etTriceps1,etTriceps2,etTriceps3, etTricepsR);
			break;

		case R.id.etSubscapular1:
		case R.id.etSubscapular2:
		case R.id.etSubscapular3:
			mediaGeral(etSubScapular1,etSubScapular2,etSubScapular3, etSubScapularR);
			break;

		case R.id.etPeitoral1:	
		case R.id.etPeitoral2:
		case R.id.etPeitoral3:
			mediaGeral(etPeitoral1, etPeitoral2,etPeitoral3, etPeitoralR);
			break;

		case R.id.etAxilar1:	
		case R.id.etAxilar2:
		case R.id.etAxilar3:
			mediaGeral(etAxilarMedia1, etAxilarMedia2,etAxilarMedia3, etAxilarMediaR);
			break;

		case R.id.etSuprailiaca1:
		case R.id.etSuprailiaca2:
		case R.id.etSuprailiaca3:
			mediaGeral(etSupraIliaca1,etSupraIliaca2,etSupraIliaca3, etSupraIliacaR);
			break;

		case R.id.etEspinhal1:
		case R.id.etEspinhal2:
		case R.id.etEspinhal3:
			mediaGeral(etSupraEspinhal1,etSupraEspinhal2,etSupraEspinhal3, etSupraEspinhalR);
			break;

		case R.id.etPatelar1:
		case R.id.etPatelar2:
		case R.id.etPatelar3:
			mediaGeral(etSupraPatelar1,etSupraPatelar2,etSupraPatelar3, etSupraPatelarR);
			break;

		case R.id.etAbdominal1:
		case R.id.etAbdominal2:
		case R.id.etAbdominal3:
			mediaGeral(etAbdominal1,etAbdominal2,etAbdominal3, etAbdominalR);
			break;

		case R.id.etCoxaMedial1:
		case R.id.etCoxaMedial2:
		case R.id.etCoxaMedial3:
			mediaGeral(etCoxaMedial1,etCoxaMedial2,etCoxaMedial3, etCoxaMedialR);
			break;

		case R.id.etPanturrilhaMedial1:
		case R.id.etPanturrilhaMedial2:
		case R.id.etPanturrilhaMedial3:
			mediaGeral(etPanturrilhaMedial1,etPanturrilhaMedial2,etPanturrilhaMedial3, etPanturrilhaMedialR);
			break;

		}

	}
	public void mediaGeral(EditText editText1, EditText editText2,EditText editText3, EditText editTextR){
		float media = 0.00f;
		int controle = 0;
		float valor1 = 0.0f,valor2 = 0.0f,valor3 = 0.0f,valorR=0.0f;

		try{
			valor1 = Float.parseFloat(editText1.getText().toString());	
			media =+ valor1;
			controle +=1;
		}
		catch(Exception e){}

		try{
			valor2 = Float.parseFloat(editText2.getText().toString());
			media += valor2;
			controle +=1;
		}catch(Exception e){}

		if (valor1 == valor2){
			editTextR.setText(String.valueOf(valor1));
		}else{

			try{
				valor3 = Float.parseFloat(editText3.getText().toString());
				media += valor3;
				controle +=1;
			}catch(Exception e){}

			if (controle == 0){
				editTextR.setText(String.valueOf(media));
			}else{

				valorR = media/controle;
				editTextR.setText(String.valueOf(arredondamento(valorR)));
				
			}
		}
	}

	public float arredondamento(float valorR){

		float valor = (float) (Math.floor(valorR*100)/100.0f);
		float conversao = (float) ((float) valor - Math.floor(valor));
		if (conversao <= 0.25f){
			return (float) Math.floor(valor);
		}else if (conversao > 0.25f && conversao <0.75f){
			return (float) (Math.floor(valor)+0.50f);
		}else{
			return (float) (Math.floor(valor)+1.00f);
		}

	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		Log.d("DobrasCutanesActivity","metodoOnclick");
		//modificarFocusCampos(false);
		DobrasCutaneas dc = new DobrasCutaneas();
		etPanturrilhaMedialR.requestFocus();
		bicepsR = Float.parseFloat(etBicepsR.getText().toString());
		Log.d("DobrasCutanesActivity","metodoOnclick");
		tricepsR = Float.parseFloat(etTricepsR.getText().toString());
		subEscapularR = Float.parseFloat(etSubScapularR.getText().toString());
		peitoralR = Float.parseFloat(etPeitoralR.getText().toString());
		axilarMediaR = Float.parseFloat(etAxilarMediaR.getText().toString());
		supraIliacaR = Float.parseFloat(etSupraIliacaR.getText().toString());
		supraEspinhalR = Float.parseFloat(etSupraEspinhalR.getText().toString());
		supraPatelarR = Float.parseFloat(etSupraPatelarR.getText().toString());
		abdominalR = Float.parseFloat(etAbdominalR.getText().toString());
		coxaMedialR = Float.parseFloat(etCoxaMedialR.getText().toString());
		panturrilhaMedialR = Float.parseFloat(etPanturrilhaMedialR.getText().toString());

		if (CompsUtils.idAluno == -1)
			dc.setOutrosDados(CompsUtils.outrosDados);
		else
			dc.setIdAluno(""+CompsUtils.idAluno);

		dc.setBiceps(bicepsR);

		dc.setTricipital(tricepsR);

		dc.setSubEscapular(subEscapularR);

		dc.setPeitoral(peitoralR);

		dc.setAxilarMedia(axilarMediaR);

		dc.setSupraIliaca(supraIliacaR);

		dc.setSupraEspinhal(supraEspinhalR);

		dc.setSupraPatelar(supraPatelarR);

		dc.setAbdominal(abdominalR);

		dc.setCoxaMedial(coxaMedialR);

		dc.setPanturrilhaMedial(panturrilhaMedialR);
		
		dc.setData(retornarData());
		Log.d(TAG,"SetouOsObjetos");

		String json = GsonManager.getInstance().toJson(dc);
		dao.insert(json);

		ArrayList<String> lista = dao.listAll();
		Log.d(TAG,"passou");
		for (String s : lista){
			Log.d(TAG, s);
		}
		//modificarFocusCampos(true);
		
		//limparTodosCampos();
		
		mensagemSalvarSucesso();
		i.setClass(getApplicationContext(), DobrasCutaneasActivity.class);
		startActivity(i);
		finish();
	}
}
