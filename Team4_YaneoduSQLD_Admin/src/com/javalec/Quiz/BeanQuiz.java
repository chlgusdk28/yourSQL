package com.javalec.Quiz;

public class BeanQuiz {

	// Field
	int quiznum;
	String quizcontents;
	String question1;
	String question2;
	String question3;
	String question4;
	int answer;
	String explanation;
	
	
	// Constructor
	
	
	public BeanQuiz() {
		
	}


	public BeanQuiz(int quiznum, String quizcontents, String question1, String question2, String question3,
			String question4, int answer, String explanation) {
		super();
		this.quiznum = quiznum;
		this.quizcontents = quizcontents;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.answer = answer;
		this.explanation = explanation;
	}

	// Method

	public int getQuiznum() {
		return quiznum;
	}


	public void setQuiznum(int quiznum) {
		this.quiznum = quiznum;
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
	
	
	
	
}
