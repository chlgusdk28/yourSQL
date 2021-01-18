package com.javalec.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.javalec.share.ShareVar;

public class inputQuizAction {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	

	public inputQuizAction() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean inputContents(String quiz, String one, String two, String three, String four, String answer, String exp, String chap) {
		PreparedStatement ps = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			@SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String A = "insert into sqldquiz (quizcontents, question1, question2, question3, question4, answer, explanation, chapter";
			String B = ") values (?,?,?,?,?,?,?,?)";
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, quiz);
			ps.setString(2, one);
			ps.setString(3, two);
			ps.setString(4, three);
			ps.setString(5, four);
			ps.setString(6, answer);
			ps.setString(7, exp);
			ps.setString(8, chap);
			ps.executeUpdate();
			
			conn_mysql.close();
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
