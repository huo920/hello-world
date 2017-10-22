package com.tedu.core;

import java.io.File;

import com.tedu.common.HttpContext;
import com.tedu.http.HttpResponse;

/**
 * ��������Http�Ĺ�������
 * @author adminitartor
 *
 */
public class HttpServlet {
	public void forward(String uri,HttpResponse response) throws Exception{
		File file = new File(
			"webapp"+uri);
		//����״̬��
		response.setStatus(HttpContext.STATUS_CODE_OK);
		//������Ӧͷ
		response.setContentType("text/html");
		response.setContentLength((int)file.length());
		//������Ӧ����
		response.setEntity(file);
		response.flush();
	}
}
