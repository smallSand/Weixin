package com.wechat.util;

import com.wechat.entity.ReceiveXmlEntity;
import com.wechat.tuling.TulingController;
import com.wechat.youdao.YouDaoController;

/**
 * 微信流程控制类
 * @author pamchen-1
 *
 */
public class WechatController {
	/**
	 * 微信处理流程控制
	 * @param content	客户端发送过来的xml数据
	 * @return	处理完成，封装好的xml结果数据
	 */
	public String wechatProcess(String content){
		//解析接收到的xml数据，转为对象
		ReceiveXmlEntity xml = ParseReceiveXml.getMsgEntity(content);
		String tlResult=null;
		
		//如果为英文
		if(xml.getContent().matches("[a-zA-Z]+")){
			//调用有道翻译API处理模块，获取翻译的结果
			tlResult=new YouDaoController().getYouDaoRE(xml.getContent().trim());
		}
		
		else{
		//调用图灵机器人接口处理模块，获取图灵机器人的结果
		tlResult = new TulingController().getTulingRe(xml.getContent());
		}
		
		//封装xml接口的返回数据
		String xmlResult = FormatXmlResult.getXmlResult(xml, tlResult);
		
		return xmlResult;
	}
}
