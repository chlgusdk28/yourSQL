package com.javalec.ResultFunction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;
import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;


public class ShowlblUSERLEVEL {

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	// Field

	public static ArrayList<String>arrayList = new ArrayList<String>();
	public static String Loginid = null;

	// Constructor
	
	public ShowlblUSERLEVEL() {
		// TODO Auto-generated constructor stub
	}
	
	public ShowlblUSERLEVEL(String loginid) {
		super();
		Loginid = loginid;
	}
	
	
	// Metohd
	



	//	 (**************************구현완료***************************) 
	public ArrayList<String> ReturnLevelAndExp(){ // ** db에서 레벨 가져오기 확인
		ShareVar shareVar = new ShareVar();
		
		String WhereDefault = "select level, exp from level "; 
		String WhereDefault2 = "where user_userid = '" + Loginid + "'"; // wootest에는 returnBeanData에 로그인 시 입력받은 id를 넣어서 가져와야한다!
		
		try{
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
			
			while(rs.next()){
				arrayList.add(0, rs.getString(1));
				arrayList.add(1, rs.getString(2));

			}
			conn_mysql.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return arrayList;
		
		
	}
	
	public void ReturnLevelBean(){ // 처음, 마지막에 뿌리기!
		ShareVar shareVar = new ShareVar();
		LevelBean bean = null;
		String WhereDefault = "select user_userid, level, exp, leveldate from level ";  
		String WhereDefault2 = "where user_userid = '" + Loginid + "'"; // wootest에는 로그인 시 입력받은 id를 넣어서 가져와야한다!
		try{
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
			
			while(rs.next()){
				String beanid = rs.getString(1);
				int beanLevel = Integer.parseInt(rs.getString(2));
				Double beanExp = Double.parseDouble(rs.getString(3));
				String beanLevelDate = rs.getString(4);
				
				bean = new LevelBean(beanid, beanLevel, beanExp, beanLevelDate);

			}
			conn_mysql.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	
		
		
	}
		

	
	

}
