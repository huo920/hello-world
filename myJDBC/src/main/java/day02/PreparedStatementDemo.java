package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import day01.DBUtil;

/**
 * Ԥ����SQL���
 * 
 * statement�ʺ�ִ�о�̬SQL��䣬����SQL�����û��ƴ�Ӷ�̬����
 * 
 * PreparedStatement�ʺ�ִ�ж�̬SQL
 * 
 * ���ݿ����յ�SQL���ʱ��������SQL��䲢����һ����Ӧ��ִ�мƻ�������ִ�мƻ�������
 * �������ظ�����ͬ��SQL���ʱ�����ݿ���������ɵ�ִ�мƻ�������ֻҪSQL������ж�̬���ݣ�
 * ��������һ�£����ݲ�ͬ��Ҳ��������ִ�мƻ���ÿ�ζ��������µ�ִ�мƻ���Ϊ�ˣ�
 * �����ж�̬���ݵ���������ͬ��SQLҪ��ִ��ʱӦ��ʹ��Ԥ����SQl
 * @author tedu
 *
 */
public class PreparedStatementDemo {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("�������û�����");
		String username = scan.nextLine();
		System.out.println("���������룺");
		String password = scan.nextLine();
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			/*
			 * Ԥ����SQL����еĶ�̬���ݿ�����"?"����
			 */
			
			String sql = "SELECT id,username,password,nickname,account "
					+ "FROM userinfo_huo "
					+ "WHERE username=? "
					+ "AND password=? ";
			/*
			 * PreparedStatement�ڴ�����ʱ�����Ҫ��Ԥ�����SQL��䴫�룬�����͸����ݿ������ƶ�Ӧ��ִ�мƻ�
			 */
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//���ã���Ӧ��ֵ
			ps.setString(1, username);
			/*
			 * ���������а���SQL�����ַ���SQLע�빥����
			 * Ҳֻ�Ὣ�䵱�����뿴�����������ٸı�SQL����
			 */
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("��¼�ɹ�");
			}else {
				System.out.println("�˺Ż��������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

}
