package com.comps.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DaoLogin extends DAO<String> {
	private static DaoLogin instance = null;
	// Simulando um banco de dados Chave-valor
	public static final String TAG 		= "DAOLOGIN";
	public static final String NAME_TABLE 		= "tbl_usuario";
	//Colunas
	public static final String CLN_ID 	= "_id";
	public static final String CLN_INFORMACAO 	= "informacao";


	public static final String SCRIPT_CREATE = "CREATE TABLE " + NAME_TABLE + " (" 
			+ CLN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	
			+ CLN_INFORMACAO + " STRING NOT NULL)";

	public static final String SCRIPT_DELETE = "DROP TABLE IF EXISTS " + NAME_TABLE;
	
	

	public DaoLogin(Context ctx) {
		super(ctx, NAME_TABLE);
		dbAdapter = new DbAdapterLogin(ctx);
	}

	@Override
	public long insert(String dados){
		long id = super.insert(dados);
		return id;
	}
	
	@Override
	protected ContentValues ofObjectToValue(String object) {
		ContentValues cv = new ContentValues();
		cv.put(CLN_INFORMACAO, object);
		return cv;
	}

	@Override
	protected String ofCursorToObject(Cursor mCursor) {
		return mCursor.getString((mCursor.getColumnIndex(CLN_INFORMACAO)));
	}
	
	public static DaoLogin getInstance (Context context){
		if (instance == null) {
			instance = new DaoLogin(context);
		}
		return instance;
	}

}
