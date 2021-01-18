package com.javalec.Quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;



public class popUpAction {
	
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;

	public popUpAction() {
		
	}
		
	public ArrayList<BeanQuiz> CallAll(String chapter) {
		ArrayList<BeanQuiz> beanList = new ArrayList<BeanQuiz>();
		
		String WhereDefault = "select quiznum, quizcontents, question1, question2, question3, question4, answer, explanation"; 
		String WhereDefault2 = " from sqldquiz where chapter = '" + chapter+"'";		// 과목 받아오기
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
			
			while(rs.next()){
				int wkQuiznum = rs.getInt(1);
				String wkQuizcontents = rs.getString(2);
				String wkQuestion1 = rs.getString(3);
				String wkQuestion2 = rs.getString(4);
				String wkQuestion3 = rs.getString(5);
				String wkQuestion4 = rs.getString(6);
				int wkAnswer = rs.getInt(7);
				String wkExplanation = rs.getString(8);
				BeanQuiz beanQuiz = new BeanQuiz(wkQuiznum, wkQuizcontents, wkQuestion1, wkQuestion2, wkQuestion3, wkQuestion4, wkAnswer, wkExplanation);
				beanList.add(beanQuiz);
			}
			conn_mysql.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return beanList;
	}
	
	public int maxNumber(String chapter) {
		int Max = 0;
		String WhereDefault = "select count(quiznum) from sqldquiz where chapter = '";
		String WhereDefault2 = chapter+"'";
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault+WhereDefault2);
			
			while(rs.next()){
				Max = rs.getInt(1);
			}
			conn_mysql.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return Max;
	}
}
