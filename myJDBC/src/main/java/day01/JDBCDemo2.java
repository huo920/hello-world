package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * �������� seq_userinfo_id_huo ��1��ʼ,����Ϊ1
 * 
 * @author tedu
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.113:1521:xe", 
					"system",
					"123456");

			System.out.println("�Ѿ���������");

			Statement state = conn.createStatement();

			/*
			 * CREATE SEQUENCE userinfo_id_huo START WITH 1 INCREMENT BY 1
			 */
			String sql = "CREATE SEQUENCE userinfo_id " + 
					" START WITH 1 " + 
					" INCREMENT BY 1 ";

			state.execute(sql);
			System.out.println("�������");
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
