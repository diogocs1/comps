package com.comps.banco;

/**
 * Classe base que possui os metodos comuns a todas as classes DAO.
 */

//import br.com.asssp.database.DbAdapter;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public abstract class DAO<T> {//OK

//	private static final String TAG 	= "DAO";

	protected String table;
	public static final String CLN_ID = "_id";

	protected SQLiteDatabase mDb;
	protected static Context context;
	protected DbAdapter dbAdapter;

	public DAO(Context ctx, String table){
		this.table = table;
		context = ctx;
		dbAdapter = new DbAdapter(context);
	}

	/**
	 * Open or create the database if not exist.
	 */
	public synchronized void open(){//synchronized
		if (mDb == null || (mDb != null && !mDb.isOpen())) {
			mDb = dbAdapter.open();	
		}
	}

	/**
	 * Close the database.
	 */
	public synchronized void close(){//synchronized
		if ((mDb != null) && mDb.isOpen()){
			dbAdapter.close();
			mDb.close();
		}
	}

	/**
	 * Cria o banco.
	 */
	//para dar certo, descomentar em DbAdapter
	public void onCreate(){
		open();
		dbAdapter.onCreate();
		close();
	}

	/**
	 * Clean the database.
	 */
	public void onDelete(){
		open();
		dbAdapter.onDelete();
		close();
	}	


	/**
	 * Insert a raw into the database.
	 * @param object Object that well insert.
	 * @return long Id of object select.
	 */
	public long insert(T object){
		ContentValues cv = ofObjectToValue(object); 
		open();
		long id = mDb.insert(table, null, cv);
		close();
		return id;//TODO OBS MODIFICADO PARA TESTE
	}

	/**
	 * <b>public boolean delete(int id)</b>
	 * Delete a raw in the database.
	 * @param id Id do objeto que serÃ¡ removido.
	 * @return true Se o objeto foi removido com sucesso.
	 */
	public boolean delete(int id){  
		open();
		long rows = mDb.delete(table, CLN_ID + " = ?",   
				new String[]{ String.valueOf(id) });  
		close();
		return (rows > 0 ? true: false); // qtde. de linhas afetadas  
	}  

	/**
	 * <b>public T search(int id) throws SQLException</b>
	 * Search the object through the id.
	 * @param id long - Id of the object that will search.//TODO
	 * @return objeto T (Tipo_Solicitado) - Retorna o objeto do tipo manipulado na classe (Person, Device, BloodPressureMeasurement, etc.).
	 * @exception SQLException.
	 */
	public T search(long id, boolean closeAfterAction) throws SQLException {///OBS COLOCAR MAIS RETORNO PARA EVITAR PARTE DESNECESSÃ�RIA
		open();
		Cursor mCursor =
				mDb.query(table, null, CLN_ID + "=?", 
						new String[] { String.valueOf(id) },
						null, null,
						null, null);
		T object = null;
		if (mCursor != null) {
			mCursor.moveToFirst();
			object = ofCursorToObject(mCursor);
		}
		if (closeAfterAction) close();
		return object;
	}

	/**
	 * Lista todos os objetos armazenados.
	 * @return Retorna um ArrayList<tipo_objeto_solicitado>
	 */
	public ArrayList<T> listAll(){
		ArrayList<T> list = new ArrayList<T>();

		open();
		Cursor mCursor = mDb.query(table, null,   
				null, null, null, null, null);  

		if (mCursor != null) {
			mCursor.moveToFirst();
		} 

		while(!mCursor.isAfterLast()){  
			T object = ofCursorToObject(mCursor);   
			
			list.add(object);  
			mCursor.moveToNext();  
		}  
		close();

		return list;
	}

	protected ArrayList<T> ofCursorToArrayList(Cursor mCursor){
		open();
		if (mCursor == null) {
			close();
			return null;	
		}

		mCursor.moveToFirst();

		ArrayList<T> list = new ArrayList<T>();

		while(!mCursor.isAfterLast()){  
			T object = ofCursorToObject(mCursor);   
			list.add(object);  
			mCursor.moveToNext();  
		}  

		mCursor.close();
		close();

		return list;
	}

	/**
	 * Converte cursor em objeto.
	 * @param cursor Cursor que serÃ¡ convertido.
	 * @return Rotorna um objeto do tipo solicitado T.
	 */
	protected abstract T ofCursorToObject(Cursor mCursor); 

	/**
	 * Converte objeto em cursor.
	 * @param objeto Objeto que serÃ¡ convertido.
	 * @return Retorna um ContentValues;
	 */
	protected abstract ContentValues ofObjectToValue(T object);
}

