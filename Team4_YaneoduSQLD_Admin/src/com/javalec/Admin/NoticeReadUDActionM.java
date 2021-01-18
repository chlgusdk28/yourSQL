package com.javalec.Admin;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JFrame;

import com.javalec.Selectfunction.Been;
import com.javalec.share.ShareVar;

public class NoticeReadUDActionM {

	private JFrame frame;
	public String title;
	public String contents;
	public int seqno;
	
	
	
	

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticeReadUDActionM window = new NoticeReadUDActionM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	
	
	
	public NoticeReadUDActionM(int seqno) {
		super();
		this.seqno = seqno;
	}









	public NoticeReadUDActionM(String title, String contents) {
		super();
		this.title = title;
		this.contents = contents;
	}









	/**
	 * Create the application.
	 */
	public NoticeReadUDActionM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	public boolean boardUpdate() {
		PreparedStatement ps = null;
		Been seqno = new Been();
		System.out.println("update : " + seqno);

		try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

	        String A = "update board set board_title = ?, board_contents = ? ";
	        String B = " where board_seqno =" + "'"+seqno.seqno+"'";

	       

	        ps = conn_mysql.prepareStatement(A+B);
	        
	        
	        ps.setString(1, title);
	        ps.setString(2, contents);
	      
	        
	       
	        ps.executeUpdate();

	        conn_mysql.close();
	        
	        
	        return true;
	       	} catch (Exception e){
	        e.printStackTrace();
	        return false;
	    }
		}
	
	
	
	
	
	
	
	
	
}
