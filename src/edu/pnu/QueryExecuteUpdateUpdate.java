package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class QueryExecuteUpdateUpdate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/myfirstdb";
		String user = "root";
		String password = "1234";
		
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			System.out.print("전화 번호를 입력하세요: ");
			String home = sc.next();
			System.out.print("변경할 인덱스를 고르세요 (1~200): ");
			int id = sc.nextInt();
			
			// PreparedStatement 사용
			String sql = "update phonebook set home=? where id=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setString(1, home);
			psmt.setInt(2, id);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
