package com.comps.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.comps.banco.DaoLogin;
import com.comps.exception.SenhaIncorretaException;

public class CompsUtils {
	public static int idAvaliador;
	public static int idAluno;
	public static String outrosDados;
	public static boolean isOnline = false;
	
	public static boolean loginOffline (Context context, String usuario, String senha) throws SenhaIncorretaException{
		DaoLogin db = DaoLogin.getInstance(context);
		ArrayList<String> dados = db.listAll();
		JSONObject json;
		try {
			// Verifica os valores um por um
			for (int i = 0; i < dados.size(); i++){
				json = new JSONObject( dados.get(i) );
				if ( json.get("usuario").equals(usuario) ){
					if ( json.get("senha").equals(senha) ){
						idAvaliador = Integer.parseInt(json.getString("uid"));
						return true;
					}
				}
			}
			throw new SenhaIncorretaException();
			
		} catch (JSONException e) {
			throw new SenhaIncorretaException();
		}
	}
	
	public static ArrayList<String> turmaPorNome (Context context) throws JSONException{
		DaoLogin db = DaoLogin.getInstance(context);
		
		ArrayList<String> result = db.listAll();
		ArrayList<String> turmas = new ArrayList<String>();
		for (String string : result){
			JSONObject obj = new JSONObject( string );
			if (Integer.parseInt(obj.getString("uid")) == CompsUtils.idAvaliador){
				JSONArray turmas_json = obj.getJSONArray("turmas");
				for (int i = 0; i < turmas_json.length(); i++){
					turmas.add(
							turmas_json.getJSONObject(i).getString("name")
							);
				}
			}
		}
		return turmas;
	}
	public static ArrayList<String> alunosPorTurma (String nomeTurma) throws JSONException{
		DaoLogin db = DaoLogin.getInstance(null);
		
		ArrayList<String> result = db.listAll();
		ArrayList<String> alunos = new ArrayList<String>();
		for (String string : result){
			JSONObject obj = new JSONObject( string );
			if (Integer.parseInt(obj.getString("uid")) == CompsUtils.idAvaliador){
				JSONArray turmas_json = obj.getJSONArray("turmas");
				for (int i = 0; i < turmas_json.length(); i++){
					if ( turmas_json.getJSONObject(i).getString("name").equals(nomeTurma) ){
						for ( int j = 0; j < turmas_json.getJSONObject(i).getJSONArray("alunos").length(); j++) {
							alunos.add(
									turmas_json.getJSONObject(i).getJSONArray("alunos").getJSONObject(j).getString("id") + "-> " +
									turmas_json.getJSONObject(i).getJSONArray("alunos").getJSONObject(j).getString("name")
									);
						}
						break;
					}
				}
			}
		}
		return alunos;
	}
	
	public static int retiraId (String nome){
		int id = -1;
		String idTemp = "";
		for (int i = 0; i < nome.length(); i++){
			if (nome.charAt(i) == '-'){
				break;
			}
			idTemp += nome.charAt(i);
		}
		id = Integer.parseInt(idTemp);
		return id;
	}
}
