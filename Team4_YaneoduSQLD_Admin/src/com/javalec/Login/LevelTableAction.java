package com.javalec.Login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.ResultFunction.LevelBean;
import com.javalec.share.ShareVar;


public class LevelTableAction {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	// Field
	
	public static String userid = null;

	
	// Constructor
	public LevelTableAction(String userid) {
		super();
		this.userid = userid;
	}
	
	public LevelTableAction() {
		
	}
	
	
	// Method
	
	
	

	public void InsertInfo() { // 회원가입하면 바로 실행 시킨 후 updateblob 실행해서 뱃지 넣어주기!
		LevelBean bean = null;
		PreparedStatement ps = null;
		BadgeBlob badgeBlob = new BadgeBlob();
		ShareVar ShareVar = new ShareVar();
		System.out.println(" 유저아이디 : " + ShareVar.userId);
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

            String A = "insert into level (user_userid, level, exp, leveldate, badgename, badgeimage";
            String B = ") values (?,?,?,now(),?,?)";
            ps = conn_mysql.prepareStatement(A+B);
            ps.setString(1, ShareVar.userId);
            ps.setInt(2, 1);
            ps.setDouble(3, 0);
            ps.setString(4, "level1");
            ps.setString(5, "test");
            ps.executeUpdate();

            conn_mysql.close();
            System.out.println("levelT 데이터 입력 완료!");
        } catch (Exception e){
        	e.printStackTrace();
           
        }
        badgeBlob.GetFromBadgeT(); // 회원가입 후 뱃지 넣기

		
	}

	
	
	
}
