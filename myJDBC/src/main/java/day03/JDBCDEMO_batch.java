package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import day01.DBUtil;

/**
 * ������
 * �������ǿ��Խ�Ҫִ�еĴ���SQL��仺���ڱ���Ȼ��һ���Է��͸����ݿ⣬
 * ����������ã�����ִ��Ч��
 * @author tedu
 *
 */
public class JDBCDEMO_batch {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
//			Statement state = conn.createStatement();
//			for(int i =0 ;i<100;i++){
//				
//				String sql = "INSERT INTO userinfo_huo "
//						+"(id,username,password,nickname,account)"
//						+"VALUES "
//						+"(userinfo_id.NEXTVAL,'test+"+i+"','123123','test',5000)";
//				//ÿ�ε���excuteUpdate()���ᷢ�͸����ݿ�
////				state.executeUpdate(sql);
//				state.addBatch(sql);  //�Ȼ��浽����
//			}
//			
//			int[] data = state.executeBatch();
			
			
			/*
			 * ���ַ�ʽЧ����ߣ�ʹ�ø��ӷ���
			 */
			conn.setAutoCommit(false);			
			String sql = "INSERT INTO userinfo_huo "
					+"(id,username,password,nickname,account) "
					+"VALUES "
					+"(userinfo_id.NEXTVAL,?,'123123','test',5000)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			for (int i = 0; i < 200; i++) {
				ps.setString(1, "test" + i);
				// ps.executeUpdate();
				ps.addBatch();
				count++;
				if (count % 50 == 0) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			
			int[] date = ps.executeBatch();
			conn.commit();
			
			System.out.println("ִ����ϣ�");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
	}
}


