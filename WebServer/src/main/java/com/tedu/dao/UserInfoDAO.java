package com.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tedu.db.DBUtil;
import com.tedu.entities.UserInfo;

/**
 * DAO �������Ӷ���
 * DAO��һ����Σ��ò���������඼�Ǻ����ݿ��
 * �����ģ������ǽ����ݲ����Ĺ��ܴ�ҵ���߼���
 * �з��������ʹ��ҵ���߼����רע�Ĵ���ҵ��
 * ���������������ݵ�ά���������뵽DAO�С�
 * ����DAO��ҵ���߼���֮������JAVA�еĶ�����
 * �������ݣ���Ҳʹ������DAO������ҵ���߼���
 * �����ݵĲ�����ȫ������󻯡�
 * @author adminitartor
 *
 */
public class UserInfoDAO {
	/**
	 * 
	 * �޸ĸ������û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public boolean update(UserInfo userInfo){
		/*
		 * ���ֺ�ID�����޸ģ����Ը����û���
		 * �޸�userInfo�и��û��������룬�ǳ�
		 * �Լ����
		 * UPDATE userinfo 
		 * SET password=?,nickname=?,account=?
		 * WHERE username=?
		 */
		return false;
	}
	
	/**
	 * ���ݸ������û����������ѯ���û�
	 * @param username
	 * @param password
	 * @return
	 */
	public UserInfo findByUsernameAndPassword(String username,String password){
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT id,username,password,nickname,account "
					   + "FROM userinfo "
					   + "WHERE username=? AND password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				username = rs.getString("username");
				password = rs.getString("password");
				String nickname = rs.getString("nickname");
				int account = rs.getInt("account");
				UserInfo userInfo = new UserInfo(id, username, password, nickname, account);
				return userInfo;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return null;
		
	}
	
	/**
	 * ���ݸ������û������Ҹ��û�
	 * @param username
	 * @return ��û�д��û��򷵻�ֵΪNULL
	 */
	public UserInfo findByUsername(String username){
		/*
		 * ���ݸ������û�����ѯ���û���Ϣ����û��
		 * ��¼��ֱ�ӷ���NULL������ѯ����������¼
		 * �����ֶε�ֵȡ�������뵽һ��UserInfoʵ��
		 * �в����ء�
		 */
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT id,username,password,nickname,account "
					   + "FROM userinfo "
					   + "WHERE username=?";
			PreparedStatement ps
				=	conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				username = rs.getString("username");
				String password = rs.getString("password");
				String nickname = rs.getString("nickname");
				int account = rs.getInt("account");
				UserInfo userInfo = new UserInfo(id, username, password, nickname, account);
				return userInfo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return null;
	}
	
	/**
	 * ���������UserInfo��������ʾ���û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public boolean save(UserInfo userInfo){
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO userinfo "
					   + "(id,username,password,nickname,account) "
					   + "VALUES "
					   + "(seq_userinfo_id.NEXTVAL,?,?,?,?)";
			PreparedStatement ps 
				= conn.prepareStatement(sql,new String[]{"id"});
			ps.setString(1, userInfo.getUsername());
			ps.setString(2, userInfo.getPassword());
			ps.setString(3, userInfo.getNickname());
			ps.setInt(4, userInfo.getAccount());
			int d = ps.executeUpdate();
			if(d>0){
				//�������ݳɹ�
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				int id = rs.getInt(1);
				userInfo.setId(id);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserInfoDAO dao = new UserInfoDAO();
		UserInfo userinfo = dao.findByUsername("jack");
		System.out.println(userinfo);
	}
}









