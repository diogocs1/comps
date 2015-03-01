package com.comps.banco;

/**
 * Classe responsavel pela cria��o das tabelas do banco.
 * Ela possibilita o gerenciamento do banco. 
 * @author Marcos Jos�
 * 
 */


//import com.projeto1.database.BloodPressureMeasureDAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapterLogin extends DbAdapter {
	
	private static final String DATABASE_NAME = "login.db";
	private static final int DATABASE_VERSION = 1;
	
//	private static final String TAG = "DbAdapter";
	
	private SQLiteDatabase mDb;
	private final Context context;
	private DatabaseHelper mDbHelper;
	
	
	private final String[] SCRIPT_DB_DELETE = new String[] { 
			//inserir os scripts delete
			DaoDados.SCRIPT_DELETE,
			DaoLogin.SCRIPT_DELETE			
			};
	
	private String[] SCRIPT_DB_CREATE = new String[] { 
			//inserir os scripts create
			DaoDados.SCRIPT_CREATE,
			DaoLogin.SCRIPT_CREATE
			};
	
	public DbAdapterLogin(Context ctx) {
		super(ctx);
		context = ctx;
	}
	
	public SQLiteDatabase open(){
		mDbHelper = new DatabaseHelper(context,  
			       DATABASE_NAME, DATABASE_VERSION,   
			       SCRIPT_DB_CREATE, SCRIPT_DB_DELETE);  
	
		mDb = mDbHelper.getWritableDatabase();
		return mDb;
	}
	
	public void close(){
		mDbHelper.close();
		mDb.close();
	}
	
	public void onDelete(){
		mDbHelper.onDelete(mDb);
	}
	
	public void onCreate(){
		mDbHelper.onCreate(mDb);
	}
}


