package com.javalec.Section;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;

public class SectionAction {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	public static ArrayList<String>arrayList = new ArrayList<String>();
	
	
	
	
	
	
	
	public ArrayList<String> rowCount() { 
		for(int i = 1; i <= 10; i++) {
			
			String WhereDefault = "select count(*) from level where level = " + i; 
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
				
				while(rs.next()){
					
					arrayList.add(rs.getString(1)); // 카운트한 값을 변수에 넣어서 리턴시켜준다.
				}
				
				
			}
			catch (Exception e){
				e.printStackTrace();
				
			}

		}
		return arrayList;
		
	}

}
