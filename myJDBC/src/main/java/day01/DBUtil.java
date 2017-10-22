package day01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



import org.apache.commons.dbcp.BasicDataSource;

/**
 * ���ݿ����ӵĹ�����
 * 
 */
public class DBUtil {
	
	
	private static BasicDataSource ds;
	
	static{
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("config.properties"));
			String driverclass = prop.getProperty("driverclass");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(prop.getProperty("maxactive"));
			int maxWait = Integer.parseInt(prop.getProperty("maxwait"));
					
			
			System.out.println("��ʼ�����ݿ�����...");
			System.out.println("������Ϣ��"+driverclass+","+url+","+username+","+password);
			
			ds = new BasicDataSource();
			
			//Class.forName(...)
			ds.setDriverClassName(driverclass);
			
			//DriverManager.getConnection(...)
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			//�������������
			ds.setMaxActive(maxActive);
			//�������ȴ�ʱ��
			ds.setMaxWait(maxWait);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡһ�����ݿ�����
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
			try {
				/*
				 * �����ӳػ�ȡ����
				 * �����ӳ���û�п��õ�����ʱ���÷�����������ǰ�̣߳��������������ӳ����˿�������ʱ��
				 * ���������ӷ��أ�����ʱ��Ȼû�п�������ʱ���÷������׳��쳣
				 */
				return ds.getConnection();
			}  catch (SQLException e) {
				throw e;
			}
	}
	
	/**
	 * �ر����ݿ�����
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try {
			if(conn != null){
				conn.setAutoCommit(true);
				/*
				 * ���ӳص����Ӷ���close�����Ĵ����ǽ����ӵ�״̬����Ϊ���ж�����Ľ���ر�
				 */
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
