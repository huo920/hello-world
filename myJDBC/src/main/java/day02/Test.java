package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * ת�˹��� ����ת���˺ŵ��û�����������ת���˺ŵ��û��� �������Ҫת�˵Ľ�����ת�˲���
 * 
 * @author tedu
 *
 */
public class Test {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("�����ת���˺ŵ��û�����");
		String usernameOut = scan.nextLine();
		System.out.println("������ת���˺ŵ��û�����");
		String usernameIn = scan.nextLine();
		System.out.println("������ת����");
		double money = Double.parseDouble(scan.nextLine());
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			/*
			 * JDBCĬ�����Զ��ύ����ģ�����ÿ��ִ��һ��DML�����󶼻��ύ����
			 * 
			 * ��ϣ�����п���������Ҫ���Զ��ύ����ر�
			 */
			conn.setAutoCommit(false);

			/*
			 * UPDATE userinfo_huo SET account=account-? WHERE username=?
			 */
			//ת������
			String sqlOut = "UPDATE userinfo_huo " + "SET account=account-? " + "WHERE username=? ";
			PreparedStatement psOut = conn.prepareStatement(sqlOut);
			psOut.setDouble(1, money);
			psOut.setString(2, usernameOut);
			int d = psOut.executeUpdate();

			if (d > 0) {
				//ת�����
				String sqlIn = "UPDATE userinfo_huo " + "SET account=account+? " + "WHERE username=? ";
				PreparedStatement psIn = conn.prepareStatement(sqlIn);
				psIn.setDouble(1, money);
				psIn.setString(2, usernameIn);
				d = psIn.executeUpdate();

				if (d > 0) {
					System.out.println("ת�˳ɹ�");
					//�ύ����
					conn.commit();
				} else {
					System.out.println("ת���û�������");
					//�ع�����
					conn.rollback();
				}
			} else{
				System.out.println("ת���û�������");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
}
