package com.comps;

import java.sql.Date;
import java.text.SimpleDateFormat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

abstract public class AbstractValidacaoActivity extends Activity
implements OnClickListener {

	public void mensagemError(String msg){

		String text = msg;
		int tempoDuracao = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(getApplicationContext(), text, tempoDuracao);
		toast.show();

	}
	public void mensagemErrorLonga(String msg){

		String text = msg;
		int tempoDuracao = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(getApplicationContext(), text, tempoDuracao);
		toast.show();

	}

	public void modificarFocus(EditText et,boolean valor){
		et.setFocusable(valor);
	}

	public void mensagemSincronizar(){
		String text = "Dados sincronizados";
		int tempoDuracao = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(getApplicationContext(), text, tempoDuracao);
		toast.show();
	}
	public void mensagemSincronizarErro(){
		String text = "Ocorreu um erro ao sincronizar";
		int tempoDuracao = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(getApplicationContext(), text, tempoDuracao);
		toast.show();
	}
	
	public void msgNoInternet(){
		String text = "Sem conecção com internet";
		int tempoDuracao = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(getApplicationContext(), text, tempoDuracao);
		toast.show();
		
	}


	public  String eliminarError(String et1){
		if(et1.equals("")){
			return "0.0";
		}
		else{
			return et1;
		}
	}

	public void mensagemSalvarSucesso(){
		String text = "Dados salvos com sucesso ";
		int tempoDuracao = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(getApplicationContext(), text, tempoDuracao);
		toast.show();
	}
	@SuppressLint("SimpleDateFormat") public String retornarData(){
		//Calendar c = Calendar.getInstance();
		/*int dia= c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH)+1;
		int ano = c.get(Calendar.YEAR);
		int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);
        int segundos = c.get(Calendar.SECOND);*/
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		return simpleFormat.format( new Date( System.currentTimeMillis()));

	}

}
