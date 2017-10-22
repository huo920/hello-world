package com.tedu.service;

import com.tedu.core.HttpServlet;
import com.tedu.dao.UserInfoDAO;
import com.tedu.entities.UserInfo;
import com.tedu.http.HttpRequest;
import com.tedu.http.HttpResponse;

/**
 * ��������û�ע�Ṧ��
 * @author adminitartor
 *
 */
public class RegServlet extends HttpServlet{
	
	
	public void service(HttpRequest request,HttpResponse response){
		System.out.println("��ʼ����ע��!");
		//��ȡ�û���
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		
		UserInfoDAO dao = new UserInfoDAO();
		//����֤���û��Ƿ��Ѿ�����?
		if(dao.findByUsername(username)!=null){
			//���û��Ѵ���!
			try {
				forward("/reg_info.html", response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{		
			//����һ��UserInfoʵ�����ڱ�ʾ��ע����Ϣ
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(username);
			userInfo.setPassword(password);
			userInfo.setNickname(nickname);
			userInfo.setAccount(5000);	
			//�����û���Ϣ
			
			boolean success = dao.save(userInfo);
			if(success){
				try {
					/*
					 * ��Ӧע��ɹ���ҳ����ͻ���
					 */	
					forward("/reg_ok.html", response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				//��תע��ʧ��ҳ��
				System.out.println("ע��ʧ��!");
			}	
		}
	}
	
	
}




