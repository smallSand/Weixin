package com.wechat.util;

import java.util.Date;

import com.wechat.entity.ReceiveXmlEntity;

/**
 * 封装返回给微信端的xml数据
 * @author pamchen-1
 *
 */
public class FormatXmlResult {
	/**
	 * 封装xml结果数据
	 * @param xml	接收到的xml对象
	 * @param tlResult	图灵机器人处理结果
	 * @return
	 */
	public static String getXmlResult(ReceiveXmlEntity xml, String tlResult){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(xml.getFromUserName());
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(xml.getToUserName());
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(new Date().getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(tlResult);
		sb.append("]]></Content></xml>");
		return sb.toString();
	}
}
