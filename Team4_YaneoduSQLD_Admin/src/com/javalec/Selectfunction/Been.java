package com.javalec.Selectfunction;

public class Been {

	//field
	String id;
	String title;
	String contents;
	String date;
	public static int seqno = 0;
	
	
	
	
	//construtor
	public Been() {
		
	}
	





	public Been(int seqno) {
		super();
		this.seqno = seqno;
	}






	public Been(String id, String title, String contents, String date) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.date = date;
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getContents() {
		return contents;
	}





	public void setContents(String contents) {
		this.contents = contents;
	}





	public String getDate() {
		return date;
	}





	public void setDate(String date) {
		this.date = date;
	}
	


	
	
	
	
	
	
	
	
	

}
