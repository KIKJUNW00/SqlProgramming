package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class QueryPreparedStatement2 {
	
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
				case 6:
					queryStatement6(con);
					break;
				case 7:
					queryStatement7(con);
					break;
				case 0:
					System.out.println("프로그램을 종료합니다.");
					return;
				}
				System.out.println();
			}
			
			
			
		} catch (Exception e) {
			System.out.println("연결실패: "+ e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				
			}
		}
	}
	
//	1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요
	private static void queryStatement1(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.println("인구수: ");
			int val = sc.nextInt();

			pstm = con.prepareStatement("select name, population " 
					   + " from city"
			           + " where population > ?");
			pstm.setInt(1, val);
			rs = pstm.executeQuery();
			

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
				if (pstm != null)
					pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	2. 국가 명의 일부 또는 국가 코드를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요
	private static void queryStatement2(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.println("국가 명 or 코드를 입력하세요: ");
			String val = sc.next();

			pstm = con.prepareStatement("select city.Name, city.Population " 
	                   + " from city JOIN country ON city.countrycode = country.code"
			           + " where city.CountryCode like ? or country.name like ?");
			pstm.setString(1, val+"%");
			pstm.setString(2, val+"%");
			rs = pstm.executeQuery();
			

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
				if (pstm != null)
					pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	3. 대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Continent)
	private static void queryStatement3(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.print("대륙: ");
			String val = sc.next();
			
			pstm = con.prepareStatement("select name"
									   +" from country where Continent"
									   +" like ?");
			pstm.setString(1, val);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				String conName = rs.getString("name");
				System.out.println("나라이름: "+conName);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstm != null)
					pstm.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
//	4.넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로
//	검색해서 출력하세요
	
	private static void queryStatment4(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.println("넓이: ");
			int val = sc.nextInt();
			
			pstm = con.prepareStatement("select name, surfaceArea from country where surfacearea > ? order by surfacearea");
			pstm.setInt(1, val);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				String conName = rs.getString("name");
				int area = rs.getInt("surfaceArea");
				System.out.println("나라이름: "+conName+ " | 면적: "+ area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstm != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
//	5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색해서 출력하세요. (예:‘Kyonggi’)
	private static void queryStatement5(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.println("대한민국 지역: ");
			String val = sc.next();
			
			pstm = con.prepareStatement("select name from city"
										+" where district = ? ");
			pstm.setString(1, val);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println("지역: "+name);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstm != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
//	6. 언어를 입력 받아서 해당 언어가 국가 공식 언어인 국가를 검색해서 출력하세요. (예:'Spanish’)
	private static void queryStatement6(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.print("언어를 입력하시오:");
			String val = sc.next();
			
			pstm = con.prepareStatement("SELECT country.name, countrylanguage.language"
					+ " FROM country"
					+ " JOIN countrylanguage ON country.Code = countrylanguage.CountryCode"
					+ " WHERE countrylanguage.language = ?");
			pstm.setString(1, val);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String language = rs.getString("language");
				System.out.println("나라: "+name+"| 언어: "+language);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstm != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
//	7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색해서 출력하세요.
	private static void queryStatement7(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.print("비율을 입력하세요: ");
			double val = sc.nextDouble();
			
			pstm = con.prepareStatement("select countrycode, percentage from countrylanguage where percentage > ?");
			pstm.setDouble(1, val);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				String countrycode = rs.getString("countrycode");
				double percentage = rs.getDouble("percentage");
				System.out.println("나라코드: "+countrycode+"| 비율: "+percentage);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstm != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}	
	
}
