package com.tedu.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tedu.common.HttpContext;
import com.tedu.http.HttpRequest;
import com.tedu.http.HttpResponse;
import com.tedu.service.LoginServlet;
import com.tedu.service.RegServlet;
import com.tedu.service.UpdateServlet;

/**
 * ���߳��������ڴ���ÿ���ͻ��˵�����
 * @author adminitartor
 *
 */
public class ClientHandler implements Runnable{
	
	//���� uri �� Servlet ��Ķ�Ӧ��ϵ
		private static Map<String, String> 
			uriMapping = new HashMap<String, String>();
		//��ʼ�� uriMapping, ��ȡweb.xml ��䵽uriMapping
		static{
			try{
				File file= new File("webapp/web.xml");
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				//��ȡ webapp ��Ԫ��
				Element root = doc.getRootElement();
				//��ȡȫ���� url-mapping Ԫ��
				List<Element> list=
						root.elements("url-mapping");
				for (Element e : list) {
					//e ����ÿ�� url-mapping
					//e.elementText("url")��ȡeԪ����
					//url��Ԫ���е��ı�ֵ
					String uri = e.elementText("url");
					String className=e.elementText("class");
					uriMapping.put(uri, className);
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	
	private Socket socket;
	public ClientHandler(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try {
			InputStream in = socket.getInputStream();
			//������Ӧ���������
			HttpRequest request = new HttpRequest(in);
			OutputStream out = socket.getOutputStream();
			//������Ӧ����Ӧ����
			HttpResponse response = new HttpResponse(out);
			
			/*
			 * �����û�����
			 */
			//��ȡ�û�������Դ·��
			/*
			 *  /index.html
			 *  /reg
			 */
			String uri = request.getUri();
			System.out.println("uri:"+uri);
			if("/".equals(uri)){
				//ȥ��ҳ
				File file = new File("webapp/index.html");
				responseFile(HttpContext.STATUS_CODE_OK, file, response);
			}else{
				File file = new File("webapp"+uri);
				if(file.exists()){
					System.out.println("�ҵ�����Ӧ��Դ:"+file.length());
					responseFile(HttpContext.STATUS_CODE_OK,file,response);	
				}else{
					
					invoke(uri, request, response);
					
				}
				
				
				//�鿴�Ƿ�����һ������
//				}else if("/reg".equals(uri)){
//					RegServlet servlet = new RegServlet();
//					servlet.service(request,response);
//					
//				}else if("/login".equals(uri)){
//					LoginServlet servlet = new LoginServlet();
//					servlet.service(request,response);
//					
//				}else if("/update".equals(uri)){
//					UpdateServlet servlet = new UpdateServlet();
//					servlet.service(request,response);
//					
//				}else{
//					System.out.println("û����Դ:404");
//					file = new File("webapp/404.html");
//					responseFile(HttpContext.STATUS_CODE_NOTFOUND,file,response);	
//				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ���ݸ������ļ����������ֺ�׺�Ի�ȡ
	 * ��Ӧ��ContenType
	 * @param file
	 * @return
	 */
	private String getContentTypeByFile(File file){
		//��ȡ�ļ���
		String name = file.getName();
		
		//��ȡ��׺
		String ext = name.substring(
				name.lastIndexOf(".")+1);
		
		//��ȡ��Ӧ��ContentType
		String contentType 
			= HttpContext.contentTypeMapping.get(ext);
		return contentType;
				
	}
	/**
	 * ��Ӧ�ͻ���ָ����Դ
	 * @param status ��Ӧ״̬��
	 * @param file Ҫ��Ӧ����Դ
	 * @throws Exception 
	 */
	private void responseFile(int status,File file,HttpResponse response) throws Exception{
		try {
			//1 ����״̬����Ϣ
			response.setStatus(status);
			//2 ������Ӧͷ��Ϣ
			//�������ļ���׺�����ݺ�׺��ȡ��Ӧ��ContentType
			response.setContentType(getContentTypeByFile(file));
			response.setContentLength((int)file.length());
			//3 ������Ӧ����
			response.setEntity(file);
			//4 ��Ӧ�ͻ���
			response.flush();	
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void invoke(String uri, 
			HttpRequest request, HttpResponse response) 
		throws Exception{
		//����uri �ҵ����� 
		//��̬�����ൽ�ڴ�
		//��̬��������
		//��̬���ҷ��� service
		//��̬���÷���
		String className = uriMapping.get(uri);
		Class cls=Class.forName(className);
		Object obj = cls.newInstance();
		Method method = cls.getDeclaredMethod(
				"service", request.getClass(),
				response.getClass());
		method.setAccessible(true); 
		method.invoke(obj, request,response); 
	}
	
	
}


