package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC java���ݿ�����
 * JDBC��SUN�ṩ��һ�����ڲ������ݿ�ı�׼�ӿ�
 * ��ͬ�����ݿ⳧�̶��ṩ��һ��JDBC�ӿڵ�ʵ�������ڲ������ṩ�����ݿ��Ʒ,��һ��ʵ����ͨ������һ��jar������,�������������
 * 
 * JDBC�ӿ����ṩ��:
 * DriverManager:�������������������ݿ����� 
 * Connection:��ʾ�����ݿ������,�����������,����ִ��SQL���Ķ���Statement
 * Statement:����ִ��SQL���,��ִ�е��ǲ�ѯ���,��õ���ѯ�Ľ����
 * ResultSet:��ʾ��ѯ�Ľ����,����������ɻ�ȡ��ѯ�ľ�������
 * 
 * 
 * @author tedu
 *
 */
public class JDBCDemo01 {
	public static void main(String[] args) {
		try {
			/*
			 * 	1.����������
			 * ��ͬ�����ݿ�,����ֵ��һ��
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/*
			 * 2.��������
			 * ���ݿ��ַ��ͬ�����ݿ��ʽ��һ��
			 */
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.113:1521:xe",          //���ݿ�ĵ�ַ
					"system",          //���ݿ���û���
					"123456"           //���ݿ������
					);
			
			System.out.println("�ѽ�������");
			
			/*
			 * ͨ��Connection��������ִ��SQL����Satementʵ��
			 */
			Statement state = conn.createStatement();
		
			
			/*
			 * ����UserInfo��
			 * �ֶ�:
			 * id NUMBER(6)
			 * username VARCHAR2(32)
			 * password VARCHAR2(32)
			 * nickname VARCHAR2(32)
			 * account NUMBER(10)
			 * 
			 * CREATE TABLE userinfo_huo(
			 * 		id NUMBER(6),
			 * 		username VARCHAR2(32),
			 *		password VARCHAR2(32),
			 * 		nickname VARCHAR2(32),
			 *		account NUMBER(10)
			 * 
			 * )
			 */
			
			String sql = "CREATE TABLE userinfo_huo( "+
							" id NUMBER(6), "+
							" username VARCHAR2(32), "+
							" password VARCHAR2(32), "+
							" nickname VARCHAR2(32), "+
							" account NUMBER(10) "+
							")";
			
//			System.out.println(sql);
			state.execute(sql);
			System.out.println("�������!");
			
			
			conn.close();
			
			
			/*
			 * Statementִ��ҪSQL������ط���:
			 * int executeUpdate(String sql)
			 * ר������ִ��DML����,����ֵΪִ�и�����Ӱ���˱��ж�������¼
			 * 
			 * ResultSet executeQuery(String sql)
			 * ר������ִ��DQL����,����ֵΪ��ѯ�Ľ����,��ResultSetʵ������
			 * 
			 * boolean excute(String sql)
			 * ����ִ���κ����͵�SQL���,������DML,DQL����ר�ŵķ���ִ��,
			 * ���Ը÷���һ������ִ��DDL���,����ֵΪִ�к��Ƿ��н����
			 * 
			 */
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
