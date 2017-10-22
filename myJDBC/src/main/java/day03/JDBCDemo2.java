package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day01.DBUtil;

/**
 * �����Զ�����
 * @author tedu
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "INSERT INTO dept_huo "
					+"(deptno,dname,loc)"
					+"VALUES "
					+"(seq_dept_id_huo.NEXTVAL,?,?)";
			
			/*
			 * ����ps��ͬʱָ��ִ�и�ps��Ӧ��SQL����
			 * Ҫ�õ������¼��ָ���ֶε�ֵ
			 */
			PreparedStatement ps = conn.prepareStatement(sql,new String[]{"deptno"});
			ps.setString(1, "IT");
			ps.setString(2, "BeiJing");
			ps.executeUpdate();
			//��ȡ�����������ָ���ֶε�ֵ
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			System.out.println("���벿�ŵ�ID�ǣ�"+id);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
	}
}
