package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ����в���һ����¼
 * @author tedu
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		try {
			
			/*
			 * Ҫ���û������û��������룬�ǳ�
			 * �����û�����userinfo��
			 * �˻����Ĭ�϶���5000
			 */
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("�������û�����");
			String username = scanner.nextLine();
			System.out.println("���������룺");
			String password = scanner.nextLine();
			System.out.println("�������ǳƣ�");
			String nickname = scanner.nextLine();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.113:1521:xe",
					"system",
					"123456");
			
			System.out.println("�Ѿ���������");
			
			Statement state = conn.createStatement();
			
			/*
			 * ����в�������
			 * INSERT INTO userinfo_huo
			 * (id,username,paddword,nickname,account)
			 * VALUES
			 * (seq_userinfo_id_huo.NEXTVAL,'','','',5000)
			 */
			
			String sql = "INSERT INTO userinfo_huo "+
					"(id,username,password,nickname,account) "+
					"VALUES "+
					"(userinfo_id.NEXTVAL,'"+username+"','"+password+"','"+nickname+"',5000) ";
			
			
			int d = state.executeUpdate(sql);
			if(d > 0){
				System.out.println("����"+d+"����¼");
			}
		
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
