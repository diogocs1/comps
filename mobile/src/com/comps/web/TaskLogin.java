package com.comps.web;

import java.io.IOException;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.comps.banco.DaoLogin;
import com.comps.util.CompsUtils;

public class TaskLogin extends AsyncTask<Void, Void, Boolean>{
	
	private JSONObject dados;
	protected Context context;
	
	public TaskLogin (Context context, String usuario, String senha) throws JSONException{
		this.context = context;
		dados = new JSONObject();
		dados.put("usuario", usuario);
		dados.put("senha", senha);
		dados.put("db", UrlUtil.db);
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			// Realizando o login online
			WebService web = WebService.getInstance();
			web.get(UrlUtil.selectDb);
			String response = web.post(UrlUtil.sync_login, dados.toString());
			
			JSONObject result = new JSONObject(response).getJSONObject("result");
			String uid = result.getString("uid");
			if (uid.contentEquals("false")){
				return false;
			}
			// Preparando os dados para gravar no banco
			DaoLogin db = DaoLogin.getInstance(context);
			result.put("usuario", dados.get("usuario"));
			result.put("senha", dados.get("senha"));
			CompsUtils.idAvaliador = Integer.parseInt(result.getString("uid"));
			Log.d("result", result.toString());
			db.onDelete();
			db.onCreate();
			db.insert(result.toString());
			return true;
		} catch (HttpException e) {
			return false;
		} catch (JSONException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}	
}