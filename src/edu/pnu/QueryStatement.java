package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class QueryStatement {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "1234");

			while (true) {
				System.out.print("질의번호를 입력하세요: ");
				int num = sc.nextInt();
				
				switch (num) {
				case 1:
					queryStatement1(con);		
					break;
				case 2:
					queryStatement2(con);
					break;
				case 3:
					queryStatement3(con);
					break;
				case 4:
					queryStatment4(con);
					break;
				case 5:
					queryStatement5(con);
					break;
//				case 6:
//					queryStatement6(con);
//					break;
//				case 7:
//					queryStatement7(con);
//					break;
				case 0:
					System.out.println("프로그램을 종료합니다.");
					return;
				}
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("연결 실패: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}

			} catch (Exception e2) {
			}

		}
	}

//1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요.
	private static void queryStatement1(Connection con) {
		Statement st = null;
		ResultSet rs = null;

		try {
			System.out.println("인구수: ");
			int val = sc.nextInt();

			st = con.createStatement();
			rs = st.executeQuery("select name, population " 
							   + " from city"
					           + " where population > " + val);

			while (rs.next()) {
				String cityName = rs.getString("name");
				int population = rs.getInt("population");
				System.out.println("도시 이름: " + cityName + ", 인구: " + population);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

//2. 국가 명의 일부 또는 국가 코드를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요.
	private static void queryStatement2(Connection con) {
		Statement st = null;
		ResultSet rs = null;

		try {
			System.out.println("국가 명 or 코드를 입력하세요: ");
			String val = sc.next();

			st = con.createStatement();
			rs = st.executeQuery("select city.Name, city.Population " 
			                   + " from city JOIN country ON city.countrycode = country.code"
					           + " where city.CountryCode like '" + val + "%' or country.name like '" + val + "%'");

			while (rs.next()) {
				String cityName = rs.getString("name");
				int population = rs.getInt("population");
				System.out.println("도시 이름: " + cityName + ", 인구: " + population);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
//3. 대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Continent)
	private static void queryStatement3(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("대륙: ");
			String val = sc.next();

			
			st = con.createStatement();
			rs = st.executeQuery("select name"
					   +" from country where Continent"
					   +" like '"+ val+"'");	
			
			while (rs.next()) {
				String country = rs.getString("name");
				System.out.println("나라이름: " + country);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	4.넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로
//	검색해서 출력하세요
	
	private static void queryStatment4(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("넓이: ");
			int val = sc.nextInt();

			
			st = con.createStatement();
			rs = st.executeQuery("select name, surfaceArea"
					   +" from country where surfacearea > '"+val+"'"
					   +"order by surfacearea");	
			
			while (rs.next()) {
				String country = rs.getString("name");
				int area = rs.getInt("surfaceArea");
				System.out.println("나라이름: "+country+ " | 면적: "+ area);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색해서 출력하세요. (예:‘Kyonggi’)
	
	private static void queryStatement5(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("언어를 입력하시오: ");
			String val = sc.next();

			
			st = con.createStatement();
			rs = st.executeQuery("SELECT country.name, countrylanguage.language"
					+ " FROM country"
					+ " JOIN countrylanguage ON country.Code = countrylanguage.CountryCode"
					+ " WHERE countrylanguage.language = '"+val+"'");
			
			while (rs.next()) {
				String name = rs.getString("name");
				String language = rs.getString("language");
				System.out.println("나라: "+name+"| 언어: "+language);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
