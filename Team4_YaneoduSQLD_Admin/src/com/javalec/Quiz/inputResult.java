package com.javalec.Quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.javalec.share.ShareVar;

public class inputResult {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	ShareVar shareVar = new ShareVar();
	public inputResult() {
		// TODO Auto-generated constructor stub
	}
	
	public void inputResultAction(int[] quizNumber, String[] correct) {
		PreparedStatement ps = null;
	      try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();
	          for(int i = 0; i < quizNumber.length ; i++) {
	        	  
	        	  String A = "insert into solve (user_userid, sqldquiz_quiznum, Dright) values (";
	        	  String B = "?,?,?)";
	        	  
	        	  ps = conn_mysql.prepareStatement(A+B);
	        	  ps.setString(1, shareVar.userId);
	        	  ps.setInt(2, quizNumber[i]);
	        	  ps.setString(3, correct[i]);
	        	  ps.executeUpdate();
	          }
	
	          conn_mysql.close();
	      } catch (Exception e){
	          e.printStackTrace();
	      }
	}
	public void wrongResultAction(Integer integer) {
		PreparedStatement ps = null;
	      try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();
	        	  
	        	  String A = "delete from solve where sqldquiz_quiznum = ? and user_userid = ?";
	        	  
	        	  ps = conn_mysql.prepareStatement(A);
	        	  ps.setInt(1, integer);
	        	  ps.setString(2, shareVar.userId);
	        	  ps.executeUpdate();
	
	          conn_mysql.close();
	      } catch (Exception e){
	          e.printStackTrace();
	      }
	}

}
