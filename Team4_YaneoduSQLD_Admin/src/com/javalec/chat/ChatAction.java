package com.javalec.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.ResultFunction.CalcExp;
import com.javalec.ResultFunction.LevelBean;
import com.javalec.ResultFunction.ShowlblUSERLEVEL;
import com.javalec.share.ShareVar;



public class ChatAction {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	
	// Field
	public int count = 0;
	
	// Constructor
	public ChatAction() {

	}
	
	
	
	// Method
	
	
	
	
	
	
	
	public void InsertInfo() { 
		
		
		ShareVar share = new ShareVar();
		
		PreparedStatement ps = null;
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

            String A = "insert into chat (chatseqno, chattext, chatdatetime";
            String B = ") values (?,?,now())";
            ps = conn_mysql.prepareStatement(A+B);
            ps.setInt(1, share.chatseqno);
            ps.setString(2, ("<" + share.userId.toString() + ">" + " : " + share.text));
       
            ps.executeUpdate();

            conn_mysql.close();
            share.chatseqno = share.chatseqno + 1;
            System.out.println("chatT 입력완료!");
        } catch (Exception e){
        	e.printStackTrace();
           
        }

		
	}
	
	public void ReturnNewSeqno() {
		ShareVar share = new ShareVar();
		
		String WhereDefault = "SELECT max(chatseqno) FROM yaneoduSQLD.chat"; 

	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
			
			while(rs.next()){
				share.chatseqno = Integer.parseInt(rs.getString(1)) + 1;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		
		}
		
	}
	
public ArrayList<ChatBean> SelectList(){
	ArrayList<ChatBean>arrayList = new ArrayList<ChatBean>();

		
		String WhereDefault = "select chattext, chatdatetime from chat";
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
			
			while(rs.next()){
				
				String chattext = rs.getString(1);
				System.out.println(chattext);
				
				String chatdatetime = rs.getString(2);
				System.out.println(chatdatetime);
				
				ChatBean bean = new ChatBean(chattext, chatdatetime);
				arrayList.add(bean);
			}
			
			
			conn_mysql.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	
	return arrayList;
		
	}


	

	


}
