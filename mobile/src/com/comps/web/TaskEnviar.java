package com.comps.web;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.comps.banco.DaoDados;


/**
 * 
 * @author Marcos José (marcosjfneto@gmail.com)
 *
 *
 */
public class TaskEnviar extends AsyncTask<Void, Void, Boolean> {
	private Context context;

	public TaskEnviar(Context context){
		this.context = context;
		
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		
		WebService ws = WebService.getInstance();
		
		try {
				DaoDados dao = DaoDados.getInstance(context);
				ArrayList<String> lista = dao.listAll();
				JSONObject json = new JSONObject();
				
				for (int i = 0; i < lista.size(); i++){
					json.put("av_"+i, new JSONObject( lista.get(i) ));
				}
				
				Log.d("Task", json.toString());
				ws.post(UrlUtil.sync, json.toString());
				return true;
			}catch (Exception e) {
				return false;
			}
	}

	@Override
	protected void onPostExecute(Boolean ok) {
		
		DaoDados dao = DaoDados.getInstance(context);
		dao.onDelete(); 
		dao.onCreate();
	
	}


}
