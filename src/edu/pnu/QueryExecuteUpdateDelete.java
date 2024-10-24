package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class QueryExecuteUpdateDelete {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/myfirstdb";
		String user = "root";
		String password = "1234";
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			System.out.print("삭제할 인덱스를 입력하시오 (1~200): ");
			int id = sc.nextInt();
			
			
			// PreparedStatement 사용
			String sql = "delete from phonebook where id=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
