package edu.pnu2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import edu.pnu.QueryExecuteUpdateInsert;

public class PhoneBookDao {
	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:mysql://localhost:3306/myfirstdb";
	private static String user = "root";
	private static String pass = "1234";

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(url, user, pass);
		boolean flag = true;
		
		edu.pnu2.QueryExecuteUpdateInsert insertPhonbook = new edu.pnu2.QueryExecuteUpdateInsert();
		QueryExecuteUpdateDelete deletePhonebook = new QueryExecuteUpdateDelete();
		QueryExecuteUpdateUpdate updatePhonebook = new QueryExecuteUpdateUpdate();
		QueryExecuteQuery selectAllPhonebook = new QueryExecuteQuery();
		
		while (flag) {
			System.out.print("[I]nsert/[U]pdate/[D]elete/[S]elect/e[X]it:");
			String s = sc.next().toUpperCase();
			
			switch (s) {
			case "I":
				insertPhonbook.insert(con);
				break;
			case "U":
				updatePhonebook.update(con);
				break;
			case "D":
				deletePhonebook.delete(con);
				break;
			case "S":
				selectAllPhonebook.selectAll(con);
				break;
			case "X":
				flag = false;
				break;
			}
		}
		System.out.println("Bye~");
	}

}
