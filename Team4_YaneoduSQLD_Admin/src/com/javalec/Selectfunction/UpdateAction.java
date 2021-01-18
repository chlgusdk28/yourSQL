package com.javalec.Selectfunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.javalec.Select.SelectQuiz;
import com.javalec.share.ShareVar;

public class UpdateAction {
	ShareVar shareVar = new ShareVar();

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	 String useremail1;
	 String userid;
	 String username;
	 String userpw = "";
	 String userbirth = "";
	 String useremail = "";
	 String usermonth = "";
	 String userday = "";
	 
	
	public UpdateAction() {
		
	}
	
	



	public UpdateAction(String useremail) {
		super();
		this.useremail = useremail;
	}





	public UpdateAction(String userid, String username, String userpw, String userbirth, String useremail) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpw = userpw;
		this.userbirth = userbirth;
		this.useremail = useremail;
	}










	public UpdateAction(String userid, String username, String userpw, String userbirth, String useremail,
			String usermonth, String userday) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpw = userpw;
		this.userbirth = userbirth;
		this.useremail = useremail;
		this.usermonth = usermonth;
		this.userday = userday;
	}





	public boolean updateAction2() {
	PreparedStatement ps = null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
        @SuppressWarnings("unused")
		Statement stmt_mysql = conn_mysql.createStatement();

        String A = "update user set userid =?, username = ?, userpw = ?, userbirth = ?, useremail = ? ";
        String B = " where userid = ? ";

       

        ps = conn_mysql.prepareStatement(A+B);
        
        
        ps.setString(1, userid);
        ps.setString(2, username);
        ps.setString(3, userpw);
        ps.setString(4, userbirth+usermonth+userday);
        ps.setString(5, useremail);
        ps.setString(6, shareVar.userId);
        
       
        ps.executeUpdate();

        conn_mysql.close();
        
        
        return true;
       	} catch (Exception e){
        e.printStackTrace();
        return false;
    }
	}
	

	public boolean overlapCheckAction() {
		String WhereDefault = "select useremail from yaneodusqld.user";
		String WhereDefault2 = " where useremail = '" + useremail + "'";
		SelectQuiz quiz = new SelectQuiz();
		boolean check = false;
		selectUseremail();
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        Statement stmt_mysql = conn_mysql.createStatement();
	        ResultSet rs = stmt_mysql.executeQuery(WhereDefault+WhereDefault2);
	            while(rs.next()){
	            	useremail1 = rs.getString(1);
	            }
	            if(shareVar.beforeUserEmail.equals(useremail)) {
	            	shareVar.beforeEmailCount = 1;
	            	return true;
	            }
	            else if (useremail.equals(useremail1)){
	        		JOptionPane.showMessageDialog(quiz,"중복된 이메일입니다. 다른이메일을 입력해주세요.");
	        		if(shareVar.beforeEmailCount == 1) {
	        			shareVar.beforeEmailCount = 0;
	        			shareVar.checkEmailCount = 1;
	        			
	        			return true;
	        		}else {
	        			return false;
	        		}
	        	} 
	        	else if (useremail.length() > 20) {
	        		JOptionPane.showMessageDialog(quiz,"이메일은 20자 이하만 가능합니다.");	
	        		return false;
	        	}
	        	else if(useremail.length()==0) {
	        		JOptionPane.showMessageDialog(quiz,"이메일을 입력해주세요.");
	        		return false;
	        	}
	            shareVar.checkEmailCount = 0;
	        	
	        conn_mysql.close();
	    }
	    catch (Exception e){
	        e.printStackTrace();
	    }	
	    if(shareVar.checkEmailCount == 0) {
	    	check = true;
	    }else {
	    	check = false;
	    }

	    return check;
		
		
	}
	public boolean selectUseremail() {
		String WhereDefault = "select useremail from yaneodusqld.user where userid = '" + shareVar.userId + "'";

	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        Statement stmt_mysql = conn_mysql.createStatement();
	        ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
	            while(rs.next()){
	            	shareVar.beforeUserEmail = rs.getString(1);
	            }
	        
	        	
	        	
	        conn_mysql.close();
	    }
	    catch (Exception e){
	        e.printStackTrace();
	    }	
	    return true;
		
		
	}

	}
	
	
	

