package com.javalec.Admin;

public class BeanAdmin {

	
	String search;
	int quiznum;
	String quizcontents;
	String question1;
	String question2;
	String question3;
	String question4;
	int answer;
	String explanation;
	String chapter;
	
	public BeanAdmin() {
		// TODO Auto-generated constructor stub
	}
	


	public BeanAdmin(int quiznum, String quizcontents) {
		super();
		this.quiznum = quiznum;
		this.quizcontents = quizcontents;
	}



	public BeanAdmin(String quizcontents, String question1, String question2, String question3, String question4,
			int answer, String explanation, String chapter) {
		super();
		this.quizcontents = quizcontents;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.answer = answer;
		this.explanation = explanation;
		this.chapter = chapter;
	}



	public int getQuiznum() {
		return quiznum;
	}



	public void setQuiznum(int quiznum) {
		this.quiznum = quiznum;
	}



	public BeanAdmin(String search) {
		super();
		this.search = search;
	}


	public String getSearch() {
		return search;
	}


	public void setSearch(String search) {
		this.search = search;
	}


	public String getQuizcontents() {
		return quizcontents;
	}


	public void setQuizcontents(String quizcontents) {
		this.quizcontents = quizcontents;
	}


	public String getQuestion1() {
		return question1;
	}


	public void setQuestion1(String question1) {
		this.question1 = question1;
	}


	public String getQuestion2() {
		return question2;
	}


	public void setQuestion2(String question2) {
		this.question2 = question2;
	}


	public String getQuestion3() {
		return question3;
	}


	public void setQuestion3(String question3) {
		this.question3 = question3;
	}


	public String getQuestion4() {
		return question4;
	}


	public void setQuestion4(String question4) {
		this.question4 = question4;
	}


	public int getAnswer() {
		return answer;
	}


	public void setAnswer(int answer) {
		this.answer = answer;
	}


	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	public String getChapter() {
		return chapter;
	}


	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	
	
	
}
