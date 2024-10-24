package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class QueryExecuteQuery {
	public void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfirstdb", "root", "1234");

			System.out.print("검색할 인덱스 번호를 입력하세요: ");
			int id = sc.nextInt();

			String sql = "select * from phonebook where id > ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getInt("id") + ",");
				System.out.print(rs.getString("name")+",");
				System.out.print(rs.getString("mobile")+",");
				System.out.print(rs.getString("home")+",");
				System.out.println();

			}
			
			con.close();
			psmt.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
