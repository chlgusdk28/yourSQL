package com.javalec.Login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.share.ShareVar;

public class ShowImg {
	private final static String url_mysql = ShareVar.url_mysql;
	private final static String id_mysql = ShareVar.id_mysql;
	private final static String pw_mysql = ShareVar.pw_mysql;

	
	public void Showimg() { // 현재 레벨의 뱃지를 띄우는 메소드 (실행하면 filename이 + 1되면서 파일이 생성된다)
		ShareVar shareVar = new ShareVar();
		String WhereDefault = "select img from img ";
		String WhereDefault2 = "where img_num = " + 1;
		
		
        try{
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();
            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

            while(rs.next()){
            	// File
            	ShareVar.imgname = ShareVar.imgname + 1;
            	File file = new File(Integer.toString(ShareVar.imgname));
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
