package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ����һ���û�����Ȼ�󽫸��û�ɾ��
 * @author tedu
 *
 */
public class JDBCDemo4 {
	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.113:1521:xe",
					"system",
					"123456");
			
			
			System.out.println("�Ѿ���������");
			Statement state = conn.createStatement();
			System.out.println("������Ҫɾ�����û�����");
			String username = scan.nextLine();
			/*
			 * DELETE userinfo_huo
			 * WHERE name = ''
			 * 
			 */
			String sql = "DELETE FROM userinfo_huo "+
						"WHERE username = '"+username+"'";
			int d = state.executeUpdate(sql);
			if(d>0){
				
				System.out.println("ɾ���ɹ�");
			}
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
