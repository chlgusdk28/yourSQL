package com.javalec.Selectfunction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.ResultFunction.LevelBean;
import com.javalec.ResultFunction.ShowlblUSERLEVEL;
import com.javalec.share.ShareVar;

public class ShowlblCheck {
	private final static String url_mysql = ShareVar.url_mysql;
	private final static String id_mysql = ShareVar.id_mysql;
	private final static String pw_mysql = ShareVar.pw_mysql;

	
	public void ShowCheck() {  // 뱃지테이블에서 추출


		
		String WhereDefault = "select img from img where img_num = 2";
		
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();
            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
            ShareVar shareVar = new ShareVar();
            
            while(rs.next()){
            	// File
            	
            	shareVar.checkName = shareVar.checkName + 1;
            	File file = new File(Integer.toString(shareVar.checkName));
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
