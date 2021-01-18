package com.javalec.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;


public class SearchListAction {

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	int seqno;
	
	public SearchListAction(int seqno) {
		super();
		this.seqno = seqno;
	}

	public SearchListAction() {
		
	}
	
	public ArrayList<BeanAdmin> ConditionQueryNum(String search) {
				
		ArrayList<BeanAdmin> beanList = new ArrayList<BeanAdmin>();
		String WhereDefault = "select quiznum, quizcontents from sqldquiz where quiznum = '";
        String WhereDefault2 = search + "'";
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
          Statement stmt_mysql = conn_mysql.createStatement();

          ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);


          while(rs.next()){
             int wkSeq = rs.getInt(1);
             String wkName = rs.getString(2);
             
             BeanAdmin bean = new BeanAdmin(wkSeq, wkName);
             beanList.add(bean);
             
          }
          conn_mysql.close();
      }
      catch (Exception e){
          e.printStackTrace();
      }
      
      return beanList;
	}
	public ArrayList<BeanAdmin> ConditionQueryQuiz(String search) {
		
		ArrayList<BeanAdmin> beanList = new ArrayList<BeanAdmin>();
		String WhereDefault = "select quiznum, quizcontents from sqldquiz where quizcontents";
        String WhereDefault2 = " like '%" + search + "%'";
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
          Statement stmt_mysql = conn_mysql.createStatement();

          ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);


          while(rs.next()){
             int wkSeq = rs.getInt(1);
             String wkName = rs.getString(2);
             
             BeanAdmin bean = new BeanAdmin(wkSeq, wkName);
             beanList.add(bean);
             
          }
          conn_mysql.close();
      }
      catch (Exception e){
          e.printStackTrace();
      }
      
      return beanList;
	}
	public BeanAdmin tableClick() {
		BeanAdmin bean=null;
		String WhereDefault = "select quizcontents, question1, question2, question3, question4, answer, explanation, chapter from sqldquiz"; 
		String WhereDefault2 = " where quiznum = '" + seqno +"'";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
			
			if(rs.next()) {
				String wkQuiz = rs.getString(1);
				String wkQuestion1 = rs.getString(2);
				String wkQuestion2 = rs.getString(3);
				String wkQuestion3 = rs.getString(4);
				String wkQuestion4 = rs.getString(5);
				int wkAnswer = rs.getInt(6);
				String wkExplanation = rs.getString(7);
				String wkChpater = rs.getString(8);
				bean = new BeanAdmin(wkQuiz, wkQuestion1, wkQuestion2, wkQuestion3, wkQuestion4, wkAnswer, wkExplanation, wkChpater);
			}
			conn_mysql.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return bean;
	}
}
