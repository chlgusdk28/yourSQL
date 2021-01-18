package com.javalec.Result;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.LevelInfo.LevelInfoMain;
import com.javalec.Login.LevelTableAction;
import com.javalec.Login.login;
import com.javalec.Quiz.SelectWrongNote;
import com.javalec.Quiz.WrongQuestion;
import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.ResultFunction.CalcExp;
import com.javalec.ResultFunction.LevelBean;
import com.javalec.ResultFunction.ShowlblSection;
import com.javalec.ResultFunction.ShowlblUSERLEVEL;
import com.javalec.ResultFunction.ShowtfUpExp;
import com.javalec.ResultFunction.UpdateNewLevelAndExp;
import com.javalec.Select.SelectQuiz;
import com.javalec.Selectfunction.Notice;
import com.javalec.chat.ChatMain;
import com.javalec.share.ShareVar;
import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResultMain extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblBadge;
	private JLabel lblUSERLEVEL;
	private JLabel lblSection;
	private JLabel lblCorrect;
	private JTextField tfCorrect;
	private JLabel lblInCorrect;
	private JTextField tfIncorrect;
	private JTextField tfUpExp;
	private JButton btnReGame;
	private JButton btnInCorrectNote;
	private JMenuItem menuItem_Level;
	private JMenuItem mntm_Constructor;
	private JMenu menu_WrongNote;
	
	int openSwitch = 0;
	private JMenu menu_1;
	
	
	// 해아햘 것!!!!!!
	// 1. 뱃지 테이블 생성 => 초기회원의 경우 sharevar를 통해 받은 id이용해서 intsertinfo실행하고 만들어진 user의 level이용해서 뱃지테이블에서 받아넣기 (OK)
	// 2. 생성된 테이블에서 뱃지 불러와서 라벨에 띄우기 (OK)
	// 3. 기능 구현 완료된 메소드에 모두 완료 메세지 띄우기
	// 4. 정답,오답 개수 띄우기!
	// 5. 랜덤유저만들어서 user에 insert하기!
	// 6. 필요없는 클래스 및 메소드 정리하기!
	// 7. 채팅 구현하기!
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResultMain dialog = new ResultMain();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResultMain() {
		setTitle("YaneoduSQLD");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ShareVar shareVar = new ShareVar();
				ResultMain.this.setLocationRelativeTo(null);
				if(shareVar.wrongNoteSwitch == 0) {
					ShowAll();
					tfUpExp.setText("오답노트는 경험치 증감이 없습니다.");
				}else {
					ShowAll();
					
				}
				
			}
		});
		setBounds(100, 100, 693, 613);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblBadge());
		contentPanel.add(getLblUSERLEVEL());
		contentPanel.add(getLblSection());
		contentPanel.add(getLblCorrect());
		contentPanel.add(getTfCorrect());
		contentPanel.add(getLblInCorrect());
		contentPanel.add(getTfIncorrect());
		contentPanel.add(getTfUpExp());
		contentPanel.add(getBtnReGame());
		contentPanel.add(getBtnInCorrectNote());
		{
			JMenuBar menuBar = new JMenuBar();
			menuBar.setForeground(Color.BLACK);
			setJMenuBar(menuBar);
			
			JMenu mnMenu = new JMenu("메뉴");
			menuBar.add(mnMenu);
			mnMenu.add(getMenuItem_1_2());
			mnMenu.add(getMenuItem_1_1());
			JMenuItem mntmLogout = new JMenuItem("로그아웃");
			mntmLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ResultMain.this.dispose();
					login login = new login();
					login.setVisible(true);
				}
			});
			mnMenu.add(mntmLogout);
			
			JMenuItem menuItem_Exit = new JMenuItem("종료");
			menuItem_Exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int choice = JOptionPane.showConfirmDialog(ResultMain.this, "정말 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.NO_OPTION) {
					}
					if(choice == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			});
			mnMenu.add(menuItem_Exit);
			
			JMenu menu = new JMenu("정보공유");
			menu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ChatMain chatMain = new ChatMain();
					chatMain.setVisible(true);
				}
			});
			menu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				}
			});
			menuBar.add(getMenu_WrongNote());
			menuBar.add(menu);
			menuBar.add(getMenu_1());

			
		}
		
	}
	private JLabel getLblBadge() {
		if (lblBadge == null) {
			lblBadge = new JLabel("");
			lblBadge.setBounds(235, 50, 65, 90);
			lblBadge.setIcon(null);
		}
		return lblBadge;
	}
	private JLabel getLblUSERLEVEL() {
		if (lblUSERLEVEL == null) {
			lblUSERLEVEL = new JLabel("");
			lblUSERLEVEL.setBounds(317, 29, 192, 130);
			lblUSERLEVEL.setForeground(Color.ORANGE);
			lblUSERLEVEL.setFont(new Font("Dialog", Font.BOLD, 60));
		}
		return lblUSERLEVEL;
	}
	private JLabel getLblSection() {
		if (lblSection == null) {
			lblSection = new JLabel("");
			lblSection.setForeground(Color.WHITE);
			lblSection.setBounds(25, 149, 652, 48);
			lblSection.setHorizontalAlignment(SwingConstants.CENTER);
			lblSection.setFont(new Font("Dialog", Font.BOLD, 17));
		}
		return lblSection;
	}
	private JLabel getLblCorrect() {
		if (lblCorrect == null) {
			lblCorrect = new JLabel("정답 개수");
			lblCorrect.setBounds(128, 209, 156, 48);
			lblCorrect.setForeground(new Color(51, 51, 255));
			lblCorrect.setFont(new Font("Dialog", Font.BOLD, 30));
			lblCorrect.setBackground(Color.WHITE);
		}
		return lblCorrect;
	}
	private JTextField getTfCorrect() {
		if (tfCorrect == null) {
			tfCorrect = new JTextField();
			tfCorrect.setBounds(296, 209, 244, 48);
			tfCorrect.setHorizontalAlignment(SwingConstants.CENTER);
			tfCorrect.setForeground(new Color(51, 51, 255));
			tfCorrect.setFont(new Font("Dialog", Font.BOLD, 35));
			tfCorrect.setEditable(false);
			tfCorrect.setColumns(10);
			tfCorrect.setBackground(Color.WHITE);
		}
		return tfCorrect;
	}
	private JLabel getLblInCorrect() {
		if (lblInCorrect == null) {
			lblInCorrect = new JLabel("오답 개수");
			lblInCorrect.setBounds(128, 295, 165, 48);
			lblInCorrect.setForeground(new Color(204, 51, 51));
			lblInCorrect.setFont(new Font("Dialog", Font.BOLD, 30));
		}
		return lblInCorrect;
	}
	private JTextField getTfIncorrect() {
		if (tfIncorrect == null) {
			tfIncorrect = new JTextField();
			tfIncorrect.setBounds(296, 295, 244, 48);
			tfIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
			tfIncorrect.setForeground(new Color(204, 0, 0));
			tfIncorrect.setFont(new Font("Dialog", Font.BOLD, 35));
			tfIncorrect.setEditable(false);
			tfIncorrect.setColumns(10);
			tfIncorrect.setBackground(Color.WHITE);
		}
		return tfIncorrect;
	}
	private JTextField getTfUpExp() {
		if (tfUpExp == null) {
			tfUpExp = new JTextField();
			tfUpExp.setForeground(Color.WHITE);
			tfUpExp.setBackground(Color.DARK_GRAY);
			tfUpExp.setBounds(63, 391, 556, 48);
			tfUpExp.setOpaque(false);
			tfUpExp.setHorizontalAlignment(SwingConstants.CENTER);
			tfUpExp.setFont(new Font("Dialog", Font.BOLD, 15));
			tfUpExp.setEditable(false);
			tfUpExp.setDisabledTextColor(new Color(204, 204, 204));
			tfUpExp.setColumns(10);
		}
		return tfUpExp;
	}
	private JButton getBtnReGame() {
		if (btnReGame == null) {
			btnReGame = new JButton("문제 선택");
			btnReGame.setBounds(211, 470, 124, 60);
			btnReGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SelectQuiz quiz = new SelectQuiz();
					quiz.setVisible(true);
					ResultMain.this.dispose();
					
				}
			});
			btnReGame.setForeground(Color.BLACK);
			btnReGame.setFont(new Font("Dialog", Font.BOLD, 15));
			btnReGame.setBackground(new Color(102, 204, 255));
		}
		return btnReGame;
	}
	private JButton getBtnInCorrectNote() {
		if (btnInCorrectNote == null) {
			btnInCorrectNote = new JButton("오답노트");
			btnInCorrectNote.setBounds(347, 470, 124, 60);
			btnInCorrectNote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				ResultMain.this.dispose();
				ShareVar shareVar = new ShareVar();
				shareVar.wrongNoteSwitch = 0;
				WrongQuestion question = new WrongQuestion();
				SelectWrongNote note = new SelectWrongNote();
				
				if(note.CallWrong() == 0) {
					JOptionPane.showMessageDialog(ResultMain.this, "오답이 없습니다!");
				}else {
					question.setVisible(true);
					question.setLocationRelativeTo(null);
					
				}
				
				}
			});
			btnInCorrectNote.setFont(new Font("Dialog", Font.BOLD, 15));
			btnInCorrectNote.setBackground(new Color(102, 204, 255));
		}
		return btnInCorrectNote;
	}
	
	private void ShowAll() {
		// 순서 중요! 
		// ----------회원가입 경우---------
		// 1.회원가입 시 받은 id를 sharevar로 넘겨 받은 후  insertinfotolevelT에서 받아 levelT에 데이터 입력하기!
		// 2.sharevar에서 받은 id를 이용해 showlbluserlevel에서 ReturnLevelBean을 통해 levelbean으로 해당 유저의 id,level,exp,leveldate 뿌려주기
		// 3.levelbean의 level을 showlblbadge의 showBadge에게 건네주고 showbadge는 해당 레벨의 뱃지 path를 리턴한다.
		// 4.리턴 받은 path를 이용해서 badgeblob의 updateblob를 실행!
		// ----------회원가입 경우---------
		
		// -----------로그인 경우-----------
		// 5-1.로그인 한 id를 sharevar를 통해 받는다. (이미 sharevar에 저장된 userid에 로그인 시 받은 id를 덮어씌운다!!)
		// 5-2.sharevar에서 받은 id를 이용해 showlbluserlevel에서 ReturnLevelBean을 통해 levelbean으로 해당 유저의 id,level,exp,leveldate 뿌려주기
		// -----------로그인 경우-----------
		
		// 6-1.게임을 진행 (문제수, 정답수, 오답수를 sharevar를 통해 받는다 => 정답, 오답은 그냥 변수둬서 count하기!
		// 6-2.마지막 문제 풀었을 때 해당 id의 level, exp에 문제수, 정답수, 오답수를 이용해 계산한 결과를 업데이트 시킨다! (레벨업의 경우는 먼저 갱신!)
		// 7.resultmain에는 최종적으로 해당 id의 level,exp를 출력한다!
		
		// ** badgeblob은 회원가입할 때랑 레벨업할 때 !!

		ShareVar shareVar = new ShareVar();
		LevelBean bean = new LevelBean(); // levelbean에서 가져오기 위해 선언
		ShowlblSection section = new ShowlblSection(); // 상위 몇 %
		ShowlblUSERLEVEL level = new ShowlblUSERLEVEL();
		ShowtfUpExp exp = new ShowtfUpExp(); // 현재 exp
		UpdateNewLevelAndExp andExp= new UpdateNewLevelAndExp();
		LevelTableAction infoToLevelT = new LevelTableAction();
		
		// ex)회원가입하면 이어서 넣을 것들
		// 1.회원가입 한 id를 sharevar를 통해 받는다
		// 2.levelT에 디폴트값 넣어주기
//		infoToLevelT.InsertInfo();
		// 3.blob 넣어주기
		// 4.returnlevelbean에 userid 넣어서 기본 정보 뿌려주기!
		level.ReturnLevelBean(); // 초기 정보 levelbean으로 뿌리기!
		int beforeLevel = bean.getLevel();
		System.out.println("beforeLevel : " + bean.getLevel());
		CalcExp calcExp = new CalcExp(bean.getLevel(), bean.getExp()); // exp계산!
		// 5.문제수, 정답수, 오답수를 받아서 exp 계산!
		// 경험치 계산 => levelup하면 db업데이트 후 업데이트된 level, exp를 levelbean으로 보내서 이 경로에 따라 badgeblob이 실행되어 뱃지 변경!
		double quizExp = calcExp.ResultExp(); // 경험치 변화량
		calcExp.LevelUp();
		andExp.UpdateLeveT();
		// 6. badge 출력
		showlblBadge(); // 손쉽게 출력하기!
		// 7. level 출력
		System.out.println("afterLevel : " + bean.getLevel());
		if(beforeLevel != bean.getLevel()) {
			JOptionPane.showMessageDialog(ResultMain.this, "Level Up!");
		}
		lblUSERLEVEL.setText("LV." + bean.getLevel());// 레벨을 띄우는 클래스 (*****확인******)
		// 8. 상위 몇 %?
		lblSection.setText(section.returnPercent()); // 상위 몇 %인지 리턴받음 (******확인******)
		// 9. 정답, 오답 개수 출력
		tfCorrect.setText(Integer.toString(shareVar.rightScore) + "개");
		tfIncorrect.setText(Integer.toString(shareVar.wrongScore) + "개");
		
		// 10. sharevar의 정답, 오답, 퀴즈수 초기화!
		shareVar.cbData = 0;
		shareVar.rightScore = 0;
		shareVar.wrongScore = 0;
		
		
		// 11. 레벨에 따른 메세지 출력 
		if(bean.getLevel() == 10) {
			tfUpExp.setText("축하드립니다! 가장 높은 레벨에 도달하셨습니다! SQLD에 도전해보세요!");
		}else {

			tfUpExp.setText(calcExp.OutputSPChar() + (String.format("%.1f", Math.abs(quizExp))) + "%" + " => 현재 경험치는 " + String.format("%.1f", exp.NowExp()) + "%입니다!"); // (******확인******)
			
		}
		
	}
	
	public void showlblBadge() { // ShareVar에서 받은 secondfilename을 통해 lbl에 뱃지 출력
		BadgeBlob badgeBlob = new BadgeBlob();
		com.javalec.share.ShareVar shareVar = new com.javalec.share.ShareVar();
		
		badgeBlob.ShowBadge();
		String filePath = Integer.toString(shareVar.secondFilename);
		
		lblBadge.setIcon(new ImageIcon(filePath));
		lblBadge.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
		
	}
	private JMenuItem getMenuItem_1_1() {
		if (menuItem_Level == null) {
			menuItem_Level = new JMenuItem("레벨표");
			menuItem_Level.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LevelInfoMain infoMain = new LevelInfoMain();
					infoMain.setVisible(true);
					
				}
			});
		}
		return menuItem_Level;
	}
	private JMenuItem getMenuItem_1_2() {
		if (mntm_Constructor == null) {
			mntm_Constructor = new JMenuItem("제작자");
			mntm_Constructor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(ResultMain.this,"Copyright 2020. 김대환, 이민우, 최현아, 정영재 All Rights Reserved.");
				}
			});
		}
		return mntm_Constructor;
	}
	private JMenu getMenu_WrongNote() {
		if (menu_WrongNote == null) {
			menu_WrongNote = new JMenu("오답노트");
			menu_WrongNote.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				ShareVar shareVar = new ShareVar();
				shareVar.wrongNoteSwitch = 0;
				WrongQuestion question = new WrongQuestion();
				SelectWrongNote note = new SelectWrongNote();
				if(note.CallWrong() == 0) {
					JOptionPane.showMessageDialog(ResultMain.this, "오답이 없습니다!");

				}else {
					ResultMain.this.dispose();
					question.setVisible(true);
					question.setLocationRelativeTo(null);
					
				}
				}
			});
		}
		return menu_WrongNote;
	}
	private JMenu getMenu_1() {
		if (menu_1 == null) {
			menu_1 = new JMenu("공지사항");
			menu_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Notice notice = new Notice();
					notice.setVisible(true);
				}
			});
		}
		return menu_1;
	}
}
