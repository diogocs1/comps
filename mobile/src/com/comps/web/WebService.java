package com.comps.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class WebService {

	private static final int BAD_REQUEST = 400;
	
//	private String login;// = "root";
//	private String senha;// = "root";
	private String httpCookie = null;
	private HttpPost httpPost = null;
	private HttpGet httpGet = null;
//	private String TAG = "WebService";
	private static WebService web = null;
	
	private DefaultHttpClient httpClient;
	private HttpResponse response = null;

	public WebService(){
		httpClient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
	}

	public WebService(String login, String senha){
		httpClient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
	}

	public WebService(String url, String login, String senha){
		httpClient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
	}


	
	public String post(String url, String dados) throws HttpException, IOException {
		httpPost = new HttpPost(url);
		response = null;
		StringEntity tmp = null; 
		
		try {
			tmp = new StringEntity(dados,"UTF-8");
			tmp.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(tmp);			
			response = httpClient.execute(httpPost);
//			Log.d("WebService", url + "?" + dados);
			return getText(response);
		} catch (UnsupportedEncodingException e) {
			throw new HttpException();
		} catch (ClientProtocolException e) {
			throw new HttpException();
		}catch (NullPointerException e) {
			throw new HttpException();
		}
	}

	

	public String get (String url) throws HttpException {
		httpGet = new HttpGet(url);
//		httpGet.setHeader("Authorization", "Basic "+(login+":"+senha).getBytes());//+Base64.encodeBytes((username+":"+password).getBytes()));
		response = null;
		
		try {
			response = httpClient.execute(httpGet);
			httpCookie = response.getFirstHeader("Set-Cookie").getValue();
				Log.d("Cookie", httpCookie);
				return getText(response);
		} catch (ClientProtocolException e) {
			throw new HttpException();
		} catch (IOException e) {
			throw new HttpException();
		} catch (NullPointerException e) {
			throw new HttpException();
		}		
	}

	
	private String getText(HttpResponse response) throws HttpException {
		String texto = "";
		
		if (response.getStatusLine().getStatusCode() < BAD_REQUEST){
			
			try {
				texto = EntityUtils.toString(response.getEntity());
				Log.d("WebService", getClass()+": HttpResponse: "+texto);
			} catch (ParseException e) {
				Log.e("WebService", "webService: ParseException: " + e);
			} catch (IOException e) {
				Log.e("WebService", "webService: IOException: " + e);
			}
			
		} else {
			Log.e("WebService", "webService: HttpException: " + response.getStatusLine());
			throw new HttpException(response.getStatusLine().toString());
		}
		return texto;
	}
	
	public static WebService getInstance (){
		if (web == null){
			web = new WebService();
		}
		return web;
	}

}
