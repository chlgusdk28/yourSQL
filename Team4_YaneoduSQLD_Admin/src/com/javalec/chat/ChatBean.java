package com.javalec.chat;

public class ChatBean {
	int chatseqno = 0;
	String chattext = null;
	String chatdatetime = null;
	
	
	
	public ChatBean(String chattext1, String chatdatetime1) {
		chattext = chattext1;
		
		chatdatetime = chatdatetime1;
	}



	public int getChatseqno() {
		return chatseqno;
	}



	public void setChatseqno(int chatseqno) {
		this.chatseqno = chatseqno;
	}



	public String getChattext() {
		return chattext;
	}



	public void setChattext(String chattext) {
		this.chattext = chattext;
	}



	public String getChatdatetime() {
		return chatdatetime;
	}



	public void setChatdatetime(String chatdatetime) {
		this.chatdatetime = chatdatetime;
	}
	
	
	
	
	

}
