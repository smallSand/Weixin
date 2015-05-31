package com.wechat.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.util.WechatController;

@SuppressWarnings("serial")
public class WechatIoServlet extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置接口的数据格式和编码方式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//读取微信客户端post发送过来的数据
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		String str = "";
		StringBuffer sb = new StringBuffer();
		
		//按行读取内容
		while(null != (str = br.readLine())){
			sb.append(str);
		}
		
		String content = sb.toString();
		
		//调用微信处理流程，获取处理结果
		String result = new WechatController().wechatProcess(content);
		//返回处理完成的结果
		OutputStream os = response.getOutputStream();
		os.write(result.getBytes("UTF-8"));
		os.flush();
		os.close();
		
	}

}
