package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

import day01.DBUtil;

/**
 * ����ִ��������ͬ��SQLʱ��ps��������ִ�мƻ� ��С���ݿ⿪��
 * 
 * @author tedu
 *
 */
public class PreparedStatementDemo2 {
	/*
	 * ������usernfo���в���1000������ ��������Ӱ�����ݿ�ִ��Ч����Ҫ�������棺 1�����紫�� 2��ִ�мƻ������� 3���������
	 */
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO userinfo_huo "
					+"(id,username,password,nickname,account) "
					+"VALUES "
					+"(userinfo_id.NEXTVAL,?,'123456',?,?)";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			for(int i =0;i<1000;i++){
				
				ps.setString(1, "test"+i);
				ps.setString(2, "test"+i);
				ps.setInt(3, 5000);
				ps.executeUpdate();  //����ͬһ��ִ�мƻ�1000��
			
			}
			conn.commit();
			System.out.println("������ϣ�");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

}
