package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import day01.DBUtil;

/**
 * ��ָ�����û��޸�����
 * �����Ӧ���û�����Ȼ���Լ�Ҫ�޸ĵ����룬
 * �����޸�
 * @author tedu
 *
 */

public class PreparedStatementDemo3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("�������û�����");
		String username = scan.nextLine();
		System.out.println("���޸����룺");
		String password = scan.nextLine();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			/*
			 * UPDATE userinfo_huo
			 * SET password=?
			 * WHERE username='"+username+"'
			 */
			String sql  = "UPDATE userinfo_huo "
					+"SET password=? "
					+"WHERE username=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			int d = ps.executeUpdate();
			if(d>0){
				System.out.println("�޸ĳɹ�");
			}else{
				System.out.println("�޸�ʧ��");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

}
