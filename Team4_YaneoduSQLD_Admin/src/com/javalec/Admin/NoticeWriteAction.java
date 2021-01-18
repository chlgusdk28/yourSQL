package com.javalec.Admin;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JFrame;

import com.javalec.share.ShareVar;

public class NoticeWriteAction {
	ShareVar shareVar = new ShareVar();

	private JFrame frame;
	public static String board_id;
	public static String board_name;
	public static String board_title;
	public static String board_contents;
	
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
					NoticeWriteAction window = new NoticeWriteAction();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NoticeWriteAction() {
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
	
	
	



	public NoticeWriteAction(String board_id, String board_name, String board_title, String board_contents) {
		super();
		this.board_id = board_id;
		this.board_name = board_name;
		this.board_title = board_title;
		this.board_contents = board_contents;
		System.out.println(this.board_id);
		System.out.println(this.board_name);
		System.out.println(this.board_title);
		System.out.println(this.board_contents);
	}

	public boolean inputOK() {
		PreparedStatement ps = null;
		 try{
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			 @SuppressWarnings("unused")
			 Statement stmt_mysql = conn_mysql.createStatement();
			 
			 String A = "insert into yaneodusqld.board (board_id, board_name, board_title, board_contents, board_date) values (?, ?, ?, ?, now())";
			 
			 ps = conn_mysql.prepareStatement(A);
			 
			 
			 ps.setString(1, board_id);
			 ps.setString(2, board_name);
			 ps.setString(3, board_title);
			 ps.setString(4, board_contents);
			 ps.executeUpdate();
			 
			 conn_mysql.close();
		 } catch (Exception e){
			 return false;
		 }
		 return true; 
	}
}
