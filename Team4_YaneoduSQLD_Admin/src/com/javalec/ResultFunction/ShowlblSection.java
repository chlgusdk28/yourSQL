package com.javalec.ResultFunction;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.util.ArrayList;

import com.javalec.share.ShareVar;

public class ShowlblSection {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	
	
	
	// Field
	public static String LoginId = null;


	
	// Constructor
	
	
	public ShowlblSection() {
		
	}
	
	
	
	public ShowlblSection(String loginId) { // 로그인 성공했을 때의 아이디를 받기
		super();
		LoginId = loginId;
	}



	// Method
	
//	public String GetRowNum() { // 사용자의 rownum을 받는 메소드
//		setRownum();
//		String rowNum = null;
//		String WhereDefault = "select rownum ";
//		String WhereDefault1 = "from(select @ROWNUM:=@ROWNUM+1 rownum, level.* from level order by level desc, exp desc) A ";
//		String WhereDefault2 = "where user_userid = 'wootest'";
//	
//		
//				// 1. level 내림차순 후 exp 내림차순을 통해 높은 레벨부터 정리하고 동레벨일 경우 높은 exp부터 정리한다.
//				// 2. 거기에 rownum을 추가하여 모든 행의 rownum이 높은 순부터 1씩 매겨진다.
//				// 3. 위의 쿼리를 서브쿼리로 from절에 대입하여 A라는 테이블명을 정해준다. (안정해주면 오류 발생!)
//				// 4. A테이블에서 user_userid가 login시 받은 유저의 id와 같으면 rownum을 뽑아낸다.
//
//		try{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
//			Statement stmt_mysql = conn_mysql.createStatement();
//			
//			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault1 + WhereDefault2);
//			
//			while(rs.next()){
//		
//				rowNum = rs.getString(1); // ROWNUM
//
//			
//				
//			
//			}
//			conn_mysql.close();
//			return rowNum; // 저장된 값들을 내보내서 필요한 곳에서 나누어 사용함
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			
//			return "리턴실패";
//		}
//	}
	
	 // (**************************구현완료***************************)
//	public String GetName() { // 사용자 이름을 받는 메소드
//		String getName = null;
//		
//		String WhereDefault = "select username from user "; 
//		String WhereDefault2 = "where userid = '" + LoginId + "'"; // wootest에는 생성자를 통해 받은 loginId를 넣어야한다.
//
//		try{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
//			Statement stmt_mysql = conn_mysql.createStatement();
//			
//			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
//			
//			while(rs.next()){
//				
//				getName = rs.getString(1); // user_userid에 맞는 사용자 이름을 가져온다
//			
//				
//			
//			}
//			conn_mysql.close();
//			return getName;
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
	
//	public void setRownum() { // rownum을 뽑을 때마다 초기화해줘야 제대로 된 값을 뽑을 수 있다!!
//		String WhereDefault = "set @ROWNUM:=0 "; 
//
//		try{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
//			Statement stmt_mysql = conn_mysql.createStatement();
//			
//			ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
//			
//		
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			
//		}
		
		
//	}
	
	// (**************************구현완료***************************)
	public int rowCount() { // 총 유저수를 출력하는 메소드
		String WhereDefault = "select count(*) from level "; 
		int rowCount = 0;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
			
			while(rs.next()){
				
				rowCount = rs.getInt(1); // 카운트한 값을 변수에 넣어서 리턴시켜준다.
			}
			
			
		return rowCount;
		}
		catch (Exception e){
			e.printStackTrace();
		
		return 0;
		}
		
		
	}
	
	// (**************************구현완료***************************)
	public double CounthighLevel() { // 자신보다 높은 레벨 유저를 카운트!
		ShareVar shareVar = new ShareVar();
		double countHighLevel = 0;
		String WhereDefault = "select count(*) "; 
		String WhereDefault1 = "from level "; 
		String WhereDefault2 = "where (select level from level where user_userid = '" + LoginId + "') < level";
	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault1 + WhereDefault2);
			
			while(rs.next()){
				
				countHighLevel = rs.getDouble(1); // 카운트한 값을 변수에 넣어서 리턴시켜준다.
			}
			
			
		return countHighLevel;
		}
		catch (Exception e){
			e.printStackTrace();
		
		return 0;
		}
		
		
	}
	
	// (**************************구현완료***************************)
	public double CountHighExp() { // 레벨이 같을 때 유저보다 높은 exp를 뽑아내기
		ShareVar shareVar = new ShareVar();
		double countHighExp = 0;
		String WhereDefault = "select count(*) "; 
		String WhereDefault1 = "from level "; 
		String WhereDefault2 = "where (select level from level where user_userid = '" + LoginId + "') = level "; 
		String WhereDefault3 = "and (select exp from level where user_userid = '" + LoginId + "') <= exp";
	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault1 + WhereDefault2 + WhereDefault3);
			
			while(rs.next()){
				
				countHighExp = rs.getDouble(1); // 카운트한 값을 변수에 넣어서 리턴시켜준다.
			}
			
			
		return countHighExp;
		}
		catch (Exception e){
			e.printStackTrace();
		
		return 0;
		}
	}
	
	
	// (**************************구현완료***************************)
	public String returnPercent() { // 최종 상위 몇 %인지를 리턴시켜주는 메소드
		ShareVar shareVar = new ShareVar();
		double calcPercent = ((CountHighExp() + CounthighLevel()) / rowCount()) * 100; // 몇 %인지 구하는 공식
		String txtPercent = null;
		
		if(calcPercent == 0) {
			txtPercent = (LoginId + "님은 전체 유저 중에서 상위 " + "<" + 100 + "%>" + "에 속합니다!");
		}else {
			txtPercent = (LoginId + "님은 전체 유저 중에서 상위 " + "<" + String.format("%.1f", calcPercent) + "%>" + "에 속합니다!"); // 최종 리턴값
			
		}
		
		return txtPercent;
	}
	
	
}
