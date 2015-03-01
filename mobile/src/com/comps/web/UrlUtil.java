package com.comps.web;

public class UrlUtil {
	
	//TODO SE FOR USAR WEB SERVICE2 COLOCAR O CAMINHO COMPLETO
	//SE FOR O WEB SERVICE3 COLOCAR APENAS O MÉTODO
	//OUTRA OPÇÃO É IGUALAR
	
	// Identificadores do servidor e do banco de dados 
	public static String server = "http://192.168.42.132:8069";
	public static String db = "admin";
	
	// Uri's da aplicação
	public static String selectDb = server + "/web?db=" + db;
	public static String sync = server + "/sync";
	public static String sync_login = server + "/sync/login";
	
	public static void geraUrls(){
		selectDb = server + "/web?db=" + db;
		sync = server + "/sync";
		sync_login = server + "/sync/login";
	}
}
