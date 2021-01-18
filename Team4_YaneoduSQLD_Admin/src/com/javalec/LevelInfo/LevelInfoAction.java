package com.javalec.LevelInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.share.ShareVar;

public class LevelInfoAction {
	private final static String url_mysql = ShareVar.url_mysql;
	private final static String id_mysql = ShareVar.id_mysql;
	private final static String pw_mysql = ShareVar.pw_mysql;

	public void ShowBadge() { // 현재 레벨의 뱃지를 띄우는 메소드 (실행하면 filename이 + 1되면서 파일이 생성된다)
		ShareVar shareVar = new ShareVar();
		String WhereDefault = "select image from badge where level = 99";
		
		
        try{
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();
            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

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
	
	

}
