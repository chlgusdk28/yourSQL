package com.javalec.ResultFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;

public class UpdateNewLevelAndExp {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	// Field
	public static String loginid = null;
	
	// Constructor
	public UpdateNewLevelAndExp() {
		
	}
	
	
	public UpdateNewLevelAndExp(String loginid) {
		super();
		this.loginid = loginid;
	}


	// Method
	
	public void UpdateLeveT() { 
		ShareVar shareVar = new ShareVar();
		ShowlblUSERLEVEL showlblUSERLEVEL = new ShowlblUSERLEVEL();
		CalcExp exp = new CalcExp();
		ArrayList<String>arrayList = new ArrayList<String>();
		BadgeBlob badgeBlob= new BadgeBlob();
		
		
		arrayList = exp.LevelUp(); // String으로 받았음!!!
		LevelBean bean = new LevelBean(Integer.parseInt(arrayList.get(0)), Double.parseDouble(arrayList.get(1)));
		
		if(Integer.parseInt(arrayList.get(0)) == 10){
			arrayList.add(1, Double.toString(0));
		}
	
			if(ShareVar.wrongNoteSwitch == 0) {
			
			}else {
				PreparedStatement ps = null;
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
					@SuppressWarnings("unused")
					Statement stmt_mysql = conn_mysql.createStatement();
					
					String A = "update level set level = ?, exp = ? ";
					String B = "where user_userid = ? ";
					
					ps = conn_mysql.prepareStatement(A+B);
					
					ps.setInt(1, Integer.parseInt(arrayList.get(0)));
					ps.setDouble(2, Double.parseDouble(arrayList.get(1)));
					ps.setString(3, loginid);
					
					ps.executeUpdate();
					
					conn_mysql.close();
					System.out.println("UpdateLeveT from UpdateNewLevelAndExp 업데이트 성공!!!!!");
					
				} catch (Exception e){
					System.out.println("levelup 업데이트 실패...");
				}
				
			}
	
	
	badgeBlob.GetFromBadgeT();
	}
}