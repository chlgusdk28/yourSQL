package com.javalec.Loginfunction;

public class Bean {
	
	// Field
	String id;
	String pw;
	String Name;
	String level;
	
	// Constructor
	

	public Bean(String id, String Name, String pw, String level) {
		super();
		this.id = id;
		this.Name = Name;
		this.pw = pw;
		this.pw = level;
	
	}
	
	public Bean(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

}
