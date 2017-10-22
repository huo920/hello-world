package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ÿҳ��ʾ5������ʾemp���еڶ�ҳ��Ա����Ϣ
 * Ҫ��Ա����Ϣ���չ��ʵĽ������к�鿴�ڶ�ҳ��Ϣ
 * @author tedu
 *
 */
public class JDBCDemo6 {
	public static void main(String[] args) {
		try {
			
			Connection conn = DBUtil.getConnection();
			System.out.println("�������ӳɹ�");
			
		   /*
			* SELECT *
			* FROM(SELECT ROWNUM rn, t.*
			*    FROM(SELECT ename,sal,JOB,deptno
			*      FROM emp_huo
			*      ORDER BY sal DESC)t
			* )
			* WHERE rn BETWEEN 6 AND 10
			*/

			Scanner scan = new Scanner(System.in);
			System.out.println("������ÿҳ��ʾ����Ŀ����");
			int pageSize = Integer.parseInt(scan.nextLine());
			System.out.println("������Ҫ��ʾ��ҳ����");
			int page = Integer.parseInt(scan.nextLine());
			int start = (page -1) * pageSize + 1;
			int end = page * pageSize;
			
			String sql = "SELECT * "+
						"FROM(SELECT ROWNUM rn, t.* "+
						"	FROM(SELECT ename,sal,job,deptno "+
						"		FROM emp_huo "+
						"		ORDER BY sal DESC)t "+
						"	WHERE ROWNUM<="+end+
						"	) "+
						"WHERE rn >= "+start;
			
			System.out.println(sql);
			
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int sal = rs.getInt("sal");
				int deptno = rs.getInt("deptno");
				
				System.out.println(ename+","+sal+","+job+","+deptno);
			}
			
			System.out.println("��ѯ���");
			
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
