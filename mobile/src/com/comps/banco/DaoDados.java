package com.comps.banco;



/**
 * Classe responsável pela definição da tabela device 
 * e pelo gerenciamento dos dados dessa tabela.
 * @author Marcos Jos�
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class DaoDados extends DAO<String> {




	public static final String TAG 		= "IMCDAO";
	public static final String NAME_TABLE 		= "tbl_avaliacao";

	//Colunas device
	public static final String CLN_ID 	= "_id";
	public static final String CLN_INFORMACAO 	= "informacao";


	public static final String SCRIPT_CREATE = "CREATE TABLE " + NAME_TABLE + " (" 
			+ CLN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	
			+ CLN_INFORMACAO + " STRING NOT NULL)"; //

	public static final String SCRIPT_DELETE = "DROP TABLE IF EXISTS " + NAME_TABLE; 


	private static DaoDados instance = null;
	
	
	private DaoDados(Context ctx) {
		super(ctx, NAME_TABLE);
		// TODO Auto-generated constructor stub
	}
	
	
	public static DaoDados getInstance(Context ctx){
		if (instance == null){
			instance = new DaoDados(ctx); 
		}
		return instance;
	}

	
	
	
	/**
	 * <b>public long insert(Device device)</b>
	 * Insert the device in the database.
	 * @param systemId systemId of device.
	 * @return Return the id of device.
	 * @throws SQLException.
	 */
	@Override
	public long insert(String dados){
			long id = super.insert(dados);
			return id;
	}

	
	@Override
	protected String ofCursorToObject(Cursor mCursor) {
		//TODO ADICIONAR ID
		return mCursor.getString((mCursor.getColumnIndex(CLN_INFORMACAO)));
		  
	}


	@Override
	protected ContentValues ofObjectToValue(String object) {
		ContentValues cv = new ContentValues(); 
		
		cv.put(CLN_INFORMACAO, object);
		

		return cv;
	}		
}




