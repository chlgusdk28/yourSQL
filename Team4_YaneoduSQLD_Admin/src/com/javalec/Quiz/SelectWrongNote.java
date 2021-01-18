package com.javalec.Quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;

public class SelectWrongNote {
	private final static String url_mysql = ShareVar.url_mysql;
	private final static String id_mysql = ShareVar.id_mysql;
	private final static String pw_mysql = ShareVar.pw_mysql;
	
	
	public static int CallWrong(){
		int countWrongNote = 0;
		String WhereDefault = "select count(*) from solve where Dright = 'X' and user_userid = '" + ShareVar.userId + "'" ; 
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
			
			while(rs.next()){
				countWrongNote = rs.getInt(1);
				
			}
			conn_mysql.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return countWrongNote;
	}

}
