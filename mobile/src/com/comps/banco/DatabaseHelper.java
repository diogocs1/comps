package com.comps.banco;

/**
 * Classe respons�vel pela execução de insturções SQL: criar, excluir e atualizar o banco.
 * E utilizada unicamente com a classe DbAdapter. 
 */

//OK modificando
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {


	public static final String TAG = "DatabaseHelper";

	private String[] scriptCreate;
	private String[] scriptDelete;


	public DatabaseHelper(Context ctx, String nomeBd, int versaoBanco,
			String[] listCreate, String[] scriptDelete) {

		super(ctx, nomeBd, null, versaoBanco);
		this.scriptCreate = listCreate;
		this.scriptDelete = scriptDelete;
	}

	@Override
	public void onOpen(SQLiteDatabase db){
		super.onOpen(db);
		if (!db.isReadOnly()) {
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}	

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (String script : scriptCreate){
			try{
				db.execSQL(script);
			}catch (Exception e) {}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//TODO OBS
//		onDelete(db);
//		onCreate(db);
	} 

	/**
	 * 
	 * @param mDb 
	 */
	public void onDelete(SQLiteDatabase mDb) {
		for (String script : scriptDelete){
			try{
				mDb.execSQL(script);
			}catch (Exception e) {}
		}
	}
}