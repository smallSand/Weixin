package com.wechat.youdao;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.wechat.tuling.HttpGetRequest;

public class YouDaoController {
	
	public String getYouDaoRE(String info){
		//调用有道翻译API
		String url ="http://fanyi.youdao.com/openapi.do?keyfrom=MyHomePage123&key=2094472696&type=data&doctype=json&version=1.1&q="+info;
		String tlResult = HttpGetRequest.get(url);
		//解析有单的json数据
		JSONObject json = JSONObject.fromObject(tlResult);
		StringBuilder sb=new StringBuilder();
		if(json.getInt("errorCode")==0){
			try {
				JSONObject basic=json.getJSONObject("basic");
				JSONArray web= json.getJSONArray("web");
				JSONArray explains	=basic.getJSONArray("explains");
				sb.append("基本释义:"+explains.getString(0)+"\n");
				sb.append("网络释义:"+web.getJSONObject(1).getString("value"));
			} catch (JSONException e) {
				tlResult="我找不到这个词哦";
				return tlResult;
			}
		}
		if(json.getInt("errorCode")==20){
			tlResult="要翻译的文本过长";
		}
		if(json.getInt("errorCode")==30){
			tlResult="无法进行有效的翻译";
		}
		if(json.getInt("errorCode")==60 ){
			tlResult="无词典结果，仅在获取词典结果生效";
		}
		if(tlResult!=null){
			tlResult=sb.toString();
		}
		return tlResult;
	}
}
