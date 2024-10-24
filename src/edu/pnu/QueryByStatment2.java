package edu.pnu;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatment2 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/world";
			con = DriverManager.getConnection(url, "root", "1234");
			st = con.createStatement();
			rs = st.executeQuery("select id, name, countrycode,"
								+"district, population from city");
			
			while (rs.next()) {
				System.out.println(rs.getInt("id")+",");
				System.out.println(rs.getString("name")+",");
				System.out.println(rs.getString("countrycode")+",");
				System.out.println(rs.getString("district")+",");
				System.out.println(rs.getInt("population")+"\n");
			}
			
		} catch (Exception e) {
			System.out.println("연결 실패: "+e.getMessage());
		}finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		
	}
}
