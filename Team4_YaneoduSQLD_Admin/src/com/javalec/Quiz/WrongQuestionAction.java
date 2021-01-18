package com.javalec.Quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;

public class WrongQuestionAction {
	private final static String url_mysql = ShareVar.url_mysql;
	private final static String id_mysql = ShareVar.id_mysql;
	private final static String pw_mysql = ShareVar.pw_mysql;
	
	public WrongQuestionAction() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<BeanQuiz> CallWrong(String chapter){
		ArrayList<BeanQuiz> beanList = new ArrayList<BeanQuiz>();
		
		String WhereDefault = "select q.quiznum, q.quizcontents, q.question1, q.question2, q.question3, q.question4, q.answer, q.explanation "; 
		String WhereDefault2 = "from sqldquiz as q, solve as s, yaneodusqld.user as u where q.quiznum = s.sqldquiz_quiznum and s.user_userid = '"+chapter +"' and s.Dright= 'X' group by quiznum";		// 과목 받아오기
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
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
	
}
