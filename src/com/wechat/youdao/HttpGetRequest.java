package com.wechat.youdao;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpGetRequest {
	public static String get(String url){
	try{
		HttpGet request = new HttpGet(url);
		//执行http get请求
		HttpResponse response = HttpClients.createDefault().execute(request);
		
		//根据返回码判断返回是否成功
		String result = "";
		if(response.getStatusLine().getStatusCode() == 200){
			result = EntityUtils.toString(response.getEntity());
		}
		return result;
	}catch(Exception e){
		e.printStackTrace();
		return "";
	}
	}
}
