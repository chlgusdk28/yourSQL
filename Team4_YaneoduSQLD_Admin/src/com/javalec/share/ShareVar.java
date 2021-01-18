package com.javalec.share;

import javax.swing.JButton;

import com.javalec.Login.LevelTableAction;
import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.ResultFunction.CalcExp;
import com.javalec.ResultFunction.ShowlblSection;
import com.javalec.ResultFunction.ShowlblUSERLEVEL;
import com.javalec.ResultFunction.ShowtfUpExp;
import com.javalec.ResultFunction.UpdateNewLevelAndExp;

public class ShareVar {
	
	
	public static final String url_mysql = "jdbc:mysql://192.168.0.80/yaneodusqld?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";
	public static final String id_mysql = "root";
	public static final String pw_mysql = "qwer1234";

	public static String userId = "";
	public static String userName = "";
	public static String userPw = "";
	
	//--------------- for Admin ----------------
	public static int quizNumAdm=0;
	public static int answAdm = 0;
	public static String chapAdm;
	public static String ExplAdm;
	public static String Qt1Adm;
	public static String Qt2Adm;
	public static String Qt3Adm;
	public static String Qt4Adm;
	public static String QcAdm;
	//--------------- for Admin ----------------
	
	
	
	//--------------- for Select ---------------
	public static String QuizData;
	public static String QuizBasic;
	public static String QuizUse;
	
	public static int userLv = 0;
	public static int cbData = 0;
	public static int QuizNum;
	public static String beforeUserEmail = null;
	public static int beforeEmailCount= 0;
	public static int checkEmailCount = 0;
	public static int checkName = 100;
	public static int totalTime;
	
	//--------------- for Select ---------------
	
	
	
	
	//--------------- for result--------------
	public static int filename = 0;
	public static int secondFilename = 0;
	public static int rightScore = 0;
	public static int wrongScore = 0;
	public static int wrongNoteSwitch = 0;
	public ShareVar() {
	}
	
	CalcExp calcExp = new CalcExp(rightScore, wrongScore, cbData);
	LevelTableAction infoToLevelT = new LevelTableAction(userId);
	BadgeBlob badgeBlob = new BadgeBlob(userId);
	ShowlblUSERLEVEL showlblUSERLEVEL = new ShowlblUSERLEVEL(userId);
	ShowlblSection section = new ShowlblSection(userId);
	ShowtfUpExp exp = new ShowtfUpExp(userId);
	UpdateNewLevelAndExp andExp = new UpdateNewLevelAndExp(userId);

	//--------------- for result--------------
	
	
	
	
	//--------------- for chat ---------------
	public static String text = null;
	public static int chatseqno = 1;
	public static int chatSwitch1 = 0;
	public static int chatSwitch2 = 0;

	//--------------- for chat ---------------
	
	
	
	
	//--------------- for LevelInfo -----------------
	public static int levelInfoFileName = 0;
	//--------------- for LevelInfo -----------------
	
	
	//--------------- for Login -----------------
	public static int imgname = 1000;
	//--------------- for Login -----------------
	
	
	
	
	
}
