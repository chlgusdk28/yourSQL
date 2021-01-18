package com.javalec.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.share.ShareVar;

public class UpdateQuizAction {

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	public UpdateQuizAction() {
		
	}
	
	public boolean UpdateAction(String a, String b, String c ,String d, String e, int f, String g, String h) {
		PreparedStatement ps = null;
		 try{
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			 @SuppressWarnings("unused")
			 Statement stmt_mysql = conn_mysql.createStatement();
			 
			 String A = "update sqldquiz set quizcontents = ?, question1 = ?, question2 = ?, question3 = ?, question4 = ?, answer =?, explanation = ?, chapter = ?";
			 String B = " where quiznum = ? ";
			 
			 ps = conn_mysql.prepareStatement(A+B);
			 
			 ps.setString(1, a);
			 ps.setString(2, b);
			 ps.setString(3, c);
			 ps.setString(4, d);
			 ps.setString(5, e);
			 ps.setInt(6, f);
			 ps.setString(7, g);
			 ps.setString(8, h);
			 ps.setInt(9, ShareVar.quizNumAdm);
			 ps.executeUpdate();
			 System.out.println(g);
			 conn_mysql.close();
		 } catch (Exception e1){
			 return false;
		 }
		 return true; 
	}
	
}
