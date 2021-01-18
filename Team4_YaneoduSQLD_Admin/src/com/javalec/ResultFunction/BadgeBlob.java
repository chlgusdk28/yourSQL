package com.javalec.ResultFunction;

import java.awt.Image;
import java.io.ByteArrayOutputStream;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.text.Utilities;

import com.javalec.share.ShareVar;



public class BadgeBlob {
	private final static String url_mysql = ShareVar.url_mysql;
	private final static String id_mysql = ShareVar.id_mysql;
	private final static String pw_mysql = ShareVar.pw_mysql;
	
	// Field
	public static String loginid = null;
	public static int level = 0;
	// Constructor
	
	public BadgeBlob(String getloginid) {
		loginid = getloginid;
		
		
	}
	
	public BadgeBlob() {
		
	}
	
	public BadgeBlob(int getlevel) {
		level = getlevel;
	}
	
	public BadgeBlob(int getlevel, String getloginid) {
		loginid = getloginid;
		level = getlevel;
	}
	
	
	
	// Method

	
public void UpdateBlob(String[] args) {  // userid에 맞는 레벨별 뱃지 DB에 넣기
	ShareVar shareVar = new ShareVar();
        LevelBean bean = new LevelBean();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);

            
            String filename = "level" + bean.getLevel();
            File f = new File(Integer.toString(ShareVar.secondFilename));    
            FileInputStream fis = new FileInputStream(f);
            
            String where1 = "update level set badgename = ?, badgeimage = ? ";
            String where2 = "where user_userid = ?"; // => 회원가입 완료하면 그 아이디를 대입하기!
            
            stmt = con.prepareStatement(where1 + where2);
           
            
            
            stmt.setString(1, filename);
            stmt.setBinaryStream(2, fis,(int)f.length());
            stmt.setString(3, loginid); 
            int rownum = stmt.executeUpdate();
            
            if(rownum > 0){
                System.out.println("***********뱃지 DB입력 성공***********");
            }else
            {
                System.out.println("뱃지 DB입력 실패");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
                //사용한 객체 close
                try {
                    if(con != null) con.close();
                    if(stmt != null) stmt.close();
                } catch (Exception e) {
                    
                }
            
        }
}

		public void ShowBadge() { // 현재 레벨의 뱃지를 띄우는 메소드 (실행하면 filename이 + 1되면서 파일이 생성된다)
			ShareVar shareVar = new ShareVar();
			String WhereDefault = "select badgeimage from level ";
			String WhereDefault2 = "where user_userid = '" + loginid + "'" ;
			
			
	        try{
	        	
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

	            while(rs.next()){
	            	// File
	            	ShareVar.secondFilename = ShareVar.secondFilename + 1;
	            	File file = new File(Integer.toString(ShareVar.secondFilename));
	            	FileOutputStream output = new FileOutputStream(file);
	            	InputStream input = rs.getBinaryStream(1);
	                byte[] buffer = new byte[1024];
	                while (input.read(buffer) > 0) {
	                    output.write(buffer);
	                }
	            	
	            }
	            
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
			
	
		}
		
		
		
		// badge 순서
		// 1.회원가입하면 기본 뱃지 insert
		// 2.레벨업하면 다음 뱃지 update
		
		//회원가입 => insertinfo => 뱃지테이블에서 1레벨 뱃지 뽑기 => 1레벨 뱃지 db에 저장 => userid에 맞는 뱃지 출력
		//레벨업 => levelbean에 레벨+1해서 저장해주기 = > 뱃지테이블에서 다음레벨 뱃지 뽑기 => 뽑은 뱃지 userid에 맞는 행에 뱃지 넣기 => userid에 맞는 뱃지 출력
		
		public void GetFromBadgeT() {  // 뱃지테이블에서 추출
			ShareVar shareVar = new ShareVar();
			ShowlblUSERLEVEL showlblUSERLEVEL = new ShowlblUSERLEVEL(loginid);
			showlblUSERLEVEL.ReturnLevelBean();

			LevelBean levelbean = new LevelBean();
			
			String WhereDefault = "select image from badge ";
			String WhereDefault2 = "where level = " + levelbean.getLevel() ;
			
			
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
	            
	            while(rs.next()){
	            	// File
	            	
	            	ShareVar.secondFilename = ShareVar.secondFilename + 1;
	            	File file = new File(Integer.toString(ShareVar.secondFilename));
	            	FileOutputStream output = new FileOutputStream(file);
	            	InputStream input = rs.getBinaryStream(1);
	                byte[] buffer = new byte[1024];
	                while (input.read(buffer) > 0) {
	                    output.write(buffer);
	                }
	            	
	            }
	            
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
			
			UpdateBlob(null);
			
		}


}



			  