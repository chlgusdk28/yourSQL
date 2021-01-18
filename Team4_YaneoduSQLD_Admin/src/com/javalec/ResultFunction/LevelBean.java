package com.javalec.ResultFunction;

import com.mysql.cj.jdbc.Blob;

public class LevelBean {

	public static String user_userid = null;
	public static int level = 0;
	public static Double exp = 0.0;
	public static String leveldate = null;
	public static Blob bagde;
	
	public LevelBean() {
		// TODO Auto-generated constructor stub
	}
	
	public LevelBean(int level) {
		super();
		this.level = level;
	}
	
	public LevelBean(String user_userid, int level, Double exp, String leveldate) {
		super();
		this.user_userid = user_userid;
		this.level = level;
		this.exp = exp;
		this.leveldate = leveldate;
		
	}

	public LevelBean(int level, Double exp) {
		super();
		this.level = level;
		this.exp = exp;
	}






	public String getUser_userid() {
		return user_userid;
	}
	public void setUser_userid(String user_userid) {
		this.user_userid = user_userid;
	}
	public int getLevel() {
		
		return level;
	}
	public void setLevel(int level) {
		this.level = level;

	}
	public Double getExp() {
		return exp;
	}
	public void setExp(Double exp) {
		this.exp = exp;
	}
	public String getLeveldate() {
		return leveldate;
	}
	public void setLeveldate(String leveldate) {
		this.leveldate = leveldate;
	}
	public Blob getBagde() {
		return bagde;
	}
	public void setBagde(Blob bagde) {
		this.bagde = bagde;
	}
	
	
}
