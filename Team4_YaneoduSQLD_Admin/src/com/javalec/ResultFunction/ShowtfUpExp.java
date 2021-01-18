package com.javalec.ResultFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.share.ShareVar;

public class ShowtfUpExp {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
		// ***************먼저 레벨시스템을 완성해야한다!****************
	
	
	// ex) 정답이 더 많을 경우 => ▲exp(?%) => 현재 경험치는 exp(?%)입니다!
	// ex) 오답이 더 많을 경우 => ▼exp(?%) => 현재 경험치는 exp(?%)입니다!
	// ex) 오답 == 정답  => -exp(0%) => 현재 경험치는 exp(?%)입니다!
	
	
	// Field
	public static String loginid = null;
	// Constructor
	public ShowtfUpExp() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	// Method
	
	public ShowtfUpExp(String loginid) {
		super();
		this.loginid = loginid;
	}



	public double NowExp() { // 문제를 푼 후 오른 경험치를 계산해서 바로 내보내자! 
		ShareVar shareVar = new ShareVar();
		String WhereDefault = "select exp from level "; 
		String WhereDefault2 = "where user_userid = '" + loginid + "'"; // wootest에는 returnBeanData에 로그인 시 입력받은 id를 넣어서 가져와야한다!
		
		double returnExp = 0;
		try{
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
			
			while(rs.next()){
				returnExp = Double.parseDouble(rs.getString(1)); // CalcExp에서 계산한 값을 추가하기! 

			}
			conn_mysql.close();
			
			
			return returnExp;
			
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	

}
