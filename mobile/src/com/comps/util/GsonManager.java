package com.comps.util;

import com.google.gson.Gson;

public class GsonManager {

	private static Gson instance;
	
	public static Gson getInstance(){
		if (instance == null){
			instance = new Gson();
		} 
		return instance;
	}
}
