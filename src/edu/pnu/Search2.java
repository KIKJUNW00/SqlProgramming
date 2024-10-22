package edu.pnu;

import java.util.Scanner;

public class Search2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("실행할 시스템을 고르시오");
		System.out.print("(1)Statement | (2)PreparedStatement: ");
		int num = sc.nextInt();
		switch (num) {
		case 1:
			  QueryStatement2.main(args);
			break;

		case 2:
			 QueryPreparedStatement2.main(args);
             break;
		}
	}
}
