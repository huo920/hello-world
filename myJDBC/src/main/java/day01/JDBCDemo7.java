package day01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ��ɵ�¼����
 * Ҫ���û������û���������
 * ��userinfo�����ж�Ӧ���ݣ�����ʾ��½�ɹ�
 * ������ʾ��¼ʧ��
 * @author tedu
 *
 */
public class JDBCDemo7 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("�������ݿ�");
			
			Scanner scan = new Scanner(System.in);
			System.out.println("�������û�����");
			String username = scan.nextLine();
			System.out.println("���������룺");
			String password = scan.nextLine();
			
			
			
			String sql = "SELECT id,username,password,nickname,account "+
							"FROM userinfo_huo "+
							"WHERE username='"+username+"' AND password='"+password+"' ";
			/*
			 * ����Σ�գ�SQLע�빥��
			 * 
			 * �������û�����
			 * sdfgafdg
			 * ���������룺
			 * asdfasf' OR '1'='1
			 * 
			 * 
			 * SELECT id,username,password,nickname,account 
			 * FROM userinfo_huo 
			 * WHERE username='sdfgafdg' 
			 * AND password='asdfasf' 
			 * OR '1'='1' 
             * ��¼�ɹ�
			 */
			
			System.out.println(sql);
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				System.out.println("��¼�ɹ�");
			}else{
				System.out.println("�û������������");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
			
		}
	}

}
