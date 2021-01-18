package com.javalec.ResultFunction;

import java.util.ArrayList;

import com.javalec.share.ShareVar;

public class CalcExp {
	
	// Field
	public static int level = 0;
	public static double exp = 0;
	
	//----------- test ----------- //
	public static int countCorrect = 0;
	public static int countInCorrect = 0;
	public static int countQuiz = 0;
	//----------- test ----------- //
	
	
	// Contstructor
	public CalcExp(int level, double exp) {
		super();
		this.level = level;
		this.exp = exp;
	}

	public CalcExp(int countCorrect, int countInCorrect, int countQuiz) { // sharevar를 통해 받아야 함!
		super();
		this.countCorrect = countCorrect;
		this.countInCorrect = countInCorrect;
		this.countQuiz = countQuiz;
			
		
	}
	
	public CalcExp() {
		
	}
	
	// Method
	
	public double Expcoe() { // 레벨별 경험치 계수
		double expcoe = 0;
		switch(level) {
		
		case 1:
			expcoe = 3.3083123; // 31문제
			break;
		case 2:
			expcoe = 3.1982322; // 32문제
			break;
		case 3:
			expcoe = 3.093148; // 33문제
			break;
		case 4:
			expcoe = 2.92381; // 34문제
			break;
		case 5:
			expcoe = 2.82294; // 35문제	
			break;
		case 6:
			expcoe = 2.72139; // 36문제
			break;
		case 7:
			expcoe = 2.62123; // 38문제
			break;
		case 8:
			expcoe = 2.51029; // 39문제
			break;
		case 9:
			expcoe = 1.92823; // 50문제
			break;
		case 10:
			expcoe = 0; // 경험치에 0을 곱해서 계속 0으로 초기화!
			break;
	
		}
		return expcoe;
	}
	
	public int correctAllOrIncorrectAll() {
		
		int returnAddiExp = 0;
		
		switch(countQuiz) { // 만점이거나 모두 틀렸을 경우 문제수에 따라 +, -exp 추가 분배!
		case 10:
			returnAddiExp = 5;
			break;
		case 20:
			returnAddiExp = 10;
			break;
		case 30:
			returnAddiExp = 15;
			break;
		}
		return returnAddiExp;
	}
	
	public double CalcExpUp() { // 정답이 더 많을 경우!
		double calcExpUp = 0;
		
		
		
		if(countCorrect == countQuiz) {
			calcExpUp = (countCorrect * Expcoe()) + correctAllOrIncorrectAll();
		}else {
			calcExpUp = countCorrect * Expcoe();				
		}
		return calcExpUp;
	}
	
	public double CalcExpDown() { // 오답이 더 많을 경우!
		double calcExpDown = 0;
		
		
		if(countInCorrect == countQuiz) {
			calcExpDown = -(countInCorrect * Expcoe()) - correctAllOrIncorrectAll();
		}else {
			calcExpDown = -(countInCorrect * Expcoe());		
			
		}
		return calcExpDown;
	}
	
	public double CalcExpZero() { // 정답 == 오답일 경우!
		double calcExpZero = 0;
		return calcExpZero;
	}
	
	public double ResultExp() { // 최종 계산해서 리턴으로 값을 보낸다.
		
		double resultExp = 0;
		
		LevelBean bean = new LevelBean();
		
		if(bean.getLevel() >= 10) {
			resultExp = 0;
		}
		else if(bean.getLevel() < 10) {
			if(countCorrect > countInCorrect) {
				resultExp = CalcExpUp();
			}
			if(countInCorrect > countCorrect) {
				resultExp = CalcExpDown();
			}
			if(countCorrect == countInCorrect) {
				resultExp = CalcExpZero();
			}
			
		}
		return resultExp;
	}
	
	public ArrayList<String> LevelUp() {
		double finalExp = 0;
		ShareVar ShareVar = new ShareVar(); // 필요한가?
		ArrayList<String>arrayList = new ArrayList<String>(); // string으로 리턴한다!!
		LevelBean bean = new LevelBean();
		
		if(ShareVar.wrongNoteSwitch == 0) {
			finalExp = 0;
			System.out.println("0일 때 finalExp : " + finalExp);
		}else {
			finalExp = exp + ResultExp();
			System.out.println("정상 때 finalExp : " + finalExp);
			
		}
		
		
		// 순서
		// 1.문제를 다 푼 후 문제수, 정답수, 오답수를 받는다.
		// 2.levelbean에 있는 level, exp를 가져온다.
		// 3-1.기존 레벨이 10이라면 exp는 0으로 초기화한다.
		// 3-2.그외의 레벨에서 경험치 0이하가 되면 0으로 초기화!
		// 3-3.경험치가 100이상이 되면 레벨을 한 단계 올리고 exp초과분을 exp에 저장!
		// 3-3-1.한 단계 올린 레벨이 10이라면 exp를 0으로 초기화!
		// 3-3-2.10이 아니라면 exp초과분을 계수 적용시켜서 저장!
		
		
		if(finalExp >= 100) {
			arrayList.add(Integer.toString(bean.getLevel() + 1)); // 레벨업
			arrayList.add(Double.toString((finalExp - 100) * 0.9)); // 레벨업하고 남은 경험치 계수 적용해서 올려주기!
			
		}else if(finalExp <= 0) {
			arrayList.add(Integer.toString(bean.getLevel())); // 기존 레벨
			arrayList.add(Double.toString(0)); // 0으로 초기화
		}else {
			arrayList.add(Integer.toString(bean.getLevel())); // 기존 레벨
			arrayList.add(Double.toString(finalExp)); // 변화된 경험치
		}
		
		
		return arrayList;
	}
	
	public String OutputSPChar() {
		String spChar = null;
		if(countCorrect > countInCorrect) {
			spChar = "▲";
		}else if(countCorrect < countInCorrect) {
			spChar = "▼";
		}else {
			spChar = "-";
		}
		return spChar;
	}

	


}
