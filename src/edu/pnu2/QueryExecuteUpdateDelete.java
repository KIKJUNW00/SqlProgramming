package edu.pnu2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class QueryExecuteUpdateDelete {
	public void delete(Connection con) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("삭제할 인덱스를 입력하시오 (1~200): ");
			int id = sc.nextInt();
			sc.close();
			
			
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
