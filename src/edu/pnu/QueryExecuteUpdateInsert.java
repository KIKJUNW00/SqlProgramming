package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class QueryExecuteUpdateInsert {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/myfirstdb";
		String user = "root";
		String password = "1234";
		
		String name = "홍길동";
		String mobile = "010-1234-5678";

		try {
			// DB 연결
			Connection con = DriverManager.getConnection(url, user, password);

			for (int i = 1; i <= 100; i++) {

				// PreparedStatement 사용하여 INSERT 실행
				String sql = "INSERT INTO phonebook(name, mobile) VALUES(?, ?)";
				PreparedStatement psmt = con.prepareStatement(sql);
				
				psmt.setString(1, name + i);
				psmt.setString(2, mobile);

				// 쿼리 실행
				int rowsInserted = psmt.executeUpdate();

				// 결과 확인
				if (rowsInserted > 0) {
					System.out.println("성공적으로 데이터가 삽입되었습니다!");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
