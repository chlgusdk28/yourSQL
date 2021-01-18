package com.javalec.Select;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.javalec.Admin.NoticeM;
import com.javalec.LevelInfo.LevelInfoMain;
import com.javalec.Login.login;
import com.javalec.Quiz.QuizMain;
import com.javalec.Quiz.SelectWrongNote;
import com.javalec.Quiz.WrongQuestion;
import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.ResultFunction.CalcExp;
import com.javalec.ResultFunction.ShowtfUpExp;
import com.javalec.Selectfunction.Notice;
import com.javalec.Selectfunction.ShowlblCheck;
import com.javalec.Selectfunction.Update;
import com.javalec.chat.ChatMain;
import com.javalec.share.ShareVar;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectQuiz extends JDialog {
	ShareVar shareVar = new ShareVar();
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JLabel lbLevel;
	private JLabel lbName;
	private JButton btnUpdate;
	private JRadioButton rbTen;
	private JButton btnData;
	private JButton btnBasic;
	private JButton btnUse;
	private JLabel lblNewLabel;
	private JRadioButton rbTwenty;
	private JRadioButton rbThirty;
	private JMenu menu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	int QuizNumber = 0;
	String QuizData = "";
	String QuizBasic = "";
	String QuizUse = "";
	int wrongSwitch = 0;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectQuiz dialog = new SelectQuiz();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JLabel lblNewLabel_1;
	private JComboBox cbQuizNumber;
	private JMenuItem menuItem_4;
	private JLabel lblBadge;
	private JLabel lblLicenceInfo;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel labelCheck;
	
	
	
	
	
	
//	public SelectQuiz(String quizData, String quizBasic, String quizUse) {
//		super();
//		QuizData = quizData;
//		QuizBasic = quizBasic;
//		QuizUse = quizUse;
//	}
	/**
	 * Create the dialog.
	 */
	public SelectQuiz() {
		setTitle("YaneoduSQLD");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				SelectQuiz.this.setLocationRelativeTo(null);
				showlblBadge();
				liveLevel();
				liveName();
				ShowtfUpExp exp = new ShowtfUpExp();
				showlblCheck();
			}
		});
		setBounds(100, 100, 460, 584);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(51, 51, 51));
		getContentPane().add(getLbLevel());
		getContentPane().add(getLbName());
		getContentPane().add(getBtnUpdate());
//		getContentPane().add(getRbTen());
		getContentPane().add(getBtnData());
		getContentPane().add(getBtnBasic());
		getContentPane().add(getBtnUse());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getCbQuizNumber());
		getContentPane().add(getLblBadge());
		getContentPane().add(getLblLicenceInfo());
		getContentPane().add(getLblNewLabel_4());
		getContentPane().add(getLblNewLabel_2());
		getContentPane().add(getLblNewLabel_3());
		getContentPane().add(getLabelCheck());
		setJMenuBar(getMenuBar());

	}
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnMenu());
			menuBar.add(getMenu());
			
			JMenu mnBoardAndChat = new JMenu("정보공유");
			mnBoardAndChat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("시작");
					ChatMain chatMain = new ChatMain();
					chatMain.setVisible(true);
				}
			});
			
			menuBar.add(mnBoardAndChat);
			
			JMenu menu_1 = new JMenu("공지사항");
			menu_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Notice m = new Notice();
					m.setVisible(true);
				}
			});
			menuBar.add(menu_1);
		}
		return menuBar;
	}
	private JMenu getMnMenu() {
		if (mnMenu == null) {
			mnMenu = new JMenu("메뉴");
			mnMenu.add(getMenuItem_4());
			
			JMenuItem menuItem = new JMenuItem("레벨표");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LevelInfoMain infoMain = new LevelInfoMain();
					infoMain.setVisible(true);
				}
			});
			mnMenu.add(menuItem);
			mnMenu.add(getMenuItem_1());
			mnMenu.add(getMenuItem_2());
		}
		return mnMenu;
	}
	private JMenuItem getMenuItem_1() {
		if (menuItem_1 == null) {
			menuItem_1 = new JMenuItem("로그아웃");
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int logout = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
					if(JOptionPane.YES_OPTION == logout) {
					login login = new login();
					login.setVisible(true);
					login.setLocationRelativeTo(null);
					SelectQuiz.this.dispose();
					} else if (JOptionPane.NO_OPTION == logout || JOptionPane.CLOSED_OPTION == logout){
						return;
				}
					
				}
			});
		}
		return menuItem_1;
	}
	private JMenuItem getMenuItem_2() {
		if (menuItem_2 == null) {
			menuItem_2 = new JMenuItem("종료");
			menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				}
			});
		}
		return menuItem_2;
	}
	private JLabel getLbLevel() {
		if (lbLevel == null) {
			lbLevel = new JLabel("");
			lbLevel.setForeground(Color.WHITE);
			lbLevel.setHorizontalAlignment(SwingConstants.LEFT);
			lbLevel.setFont(new Font("Lucida Grande", Font.BOLD, 12));
			lbLevel.setBounds(185, 24, 150, 32);
		}
		return lbLevel;
	}
	private JLabel getLbName() {
		if (lbName == null) {
			lbName = new JLabel("name");
			lbName.setForeground(Color.WHITE);
			lbName.setHorizontalAlignment(SwingConstants.TRAILING);
			lbName.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lbName.setBounds(83, 68, 69, 24);
		}
		return lbName;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("내 정보");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SelectQuiz.this.dispose();
					gotoUpdate();
				}
			});
			btnUpdate.setBounds(269, 68, 95, 29);
		}
		return btnUpdate;
	}

	private JButton getBtnData() {
		if (btnData == null) {
			btnData = new JButton("chapter 1 : 데이터 모델링");
			btnData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shareVar.wrongNoteSwitch = 1;
					showContents();
					quizNumber();
					dataQuizContents("데이터 모델링");
					SelectQuiz.this.dispose();
				}
			}
			);
			btnData.setBounds(83, 328, 293, 48);
		}
		return btnData;
	}
		
	private JButton getBtnBasic() {
		if (btnBasic == null) {
			btnBasic = new JButton("chapter 2 : SQL 기본");

			btnBasic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shareVar.wrongNoteSwitch = 1;
					showContents();
					quizNumber();
					dataQuizContents("SQL 기본");
					SelectQuiz.this.dispose();
					}
				}
			);
			btnBasic.setBounds(83, 388, 293, 48);
		}
		return btnBasic;
	}
	private JButton getBtnUse() {
		if (btnUse == null) {
			btnUse = new JButton("chapter 3 : SQL 활용");
			btnUse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shareVar.wrongNoteSwitch = 1;
					showContents();
					quizNumber();
					dataQuizContents("SQL 활용");;
					SelectQuiz.this.dispose();
				}
			});
			btnUse.setBounds(83, 448, 293, 48);
		}
		return btnUse;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("문항 수 :");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(149, 296, 61, 16);
		}
		return lblNewLabel;
	}

	
	private void gotoUpdate() {
		Update ui = new Update();
		ui.setLocationRelativeTo(null);
		ui.setVisible(true);
		
	}
	private JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu("오답노트");
			menu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					WrongQuestion question = new WrongQuestion();
					SelectWrongNote note = new SelectWrongNote();
					shareVar.wrongNoteSwitch = 0;
					
					if(note.CallWrong() == 0) {
						JOptionPane.showMessageDialog(null, "오답이 없습니다!");

					}else {
						dispose();
						question.setVisible(true);
						question.setLocationRelativeTo(null);
					}
				}
			});
		}
		return menu;
	}
	
		private void liveLevel() {
		 String WhereDefault = "select level, exp from level "; 
	        String WhereDefault2 = "where user_userid = '" + shareVar.userId + "'" ;
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();

	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

	            while(rs.next()){
	            	String lv = rs.getString(1);
	            	String exp = String.format("%3.4s", rs.getString(2));
	            	lbLevel.setText("LV." + lv + " Exp(" + exp + "%)");
	            	
	            }
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
		
	}
	
	private void liveName() {
		 String WhereDefault = "select username from user "; 
	        String WhereDefault2 = "where userid = '" + shareVar.userId + "'" ;
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();

	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

	            while(rs.next()){
	            	lbName.setText(rs.getString(1));
	            	
	            }
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
		
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("님 반갑습니다");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblNewLabel_1.setBounds(160, 68, 123, 25);
		}
		return lblNewLabel_1;
	}
	private JComboBox getCbQuizNumber() {
		if (cbQuizNumber == null) {
			cbQuizNumber = new JComboBox();
			cbQuizNumber.setBackground(UIManager.getColor("Button.disabledText"));
		
						
			
			cbQuizNumber.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30"}));
			cbQuizNumber.setBounds(224, 292, 69, 24);
			
			
		}
		return cbQuizNumber;
	}
	private void showContents () {
		if(Integer.parseInt(cbQuizNumber.getSelectedItem().toString())==10 || Integer.parseInt(cbQuizNumber.getSelectedItem().toString())==20 || Integer.parseInt(cbQuizNumber.getSelectedItem().toString())==30) {
		
			QuizMain main = new QuizMain();
			main.setVisible(true);
		}
		
	}
	public void dataQuizContents(String chapter) {
		shareVar.QuizBasic = chapter;
	}
	public void quizNumber() {
		shareVar.cbData = Integer.parseInt(cbQuizNumber.getSelectedItem().toString());
	}
	private JMenuItem getMenuItem_4() {
		if (menuItem_4 == null) {
			menuItem_4 = new JMenuItem("제작자 정보");
			menuItem_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,"Copyright 2020. 김대환, 이민우, 최현아, 정영재 All Rights Reserved.");
				}
			});
		}
		return menuItem_4;
	}
	private JLabel getLblBadge() {
		if (lblBadge == null) {
			lblBadge = new JLabel("");
			lblBadge.setForeground(Color.WHITE);
			lblBadge.setBounds(111, 6, 61, 52);
		}
		return lblBadge;
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
	
	private JLabel getLblLicenceInfo() {
		if (lblLicenceInfo == null) {
			lblLicenceInfo = new JLabel("");
			lblLicenceInfo.setVerticalAlignment(SwingConstants.BOTTOM);
			lblLicenceInfo.setBounds(90, 150, 282, 174);
			lblLicenceInfo.setText("<html>kdata(한국데이터산업진흥원)에서 주관하는 시험.<br><br>SQLD는 SQLD개발자를 의미하는 뜻으로,<br><br> 해당 자격증은 데이터베이스 SQL국가공인 자격증이다.<html>");
			lblLicenceInfo.setForeground(Color.ORANGE);
			lblLicenceInfo.setFont(new Font("Dialog", Font.PLAIN, 11));
			lblLicenceInfo.setBounds(90, 150, 282, 98);
		}
		return lblLicenceInfo;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("SQLD 자격증이란?");
			lblNewLabel_4.setForeground(Color.ORANGE);
			lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 20));
			lblNewLabel_4.setBounds(149, 131, 180, 32);
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("---------------------------------------");
			lblNewLabel_2.setForeground(Color.ORANGE);
			lblNewLabel_2.setBounds(71, 102, 361, 22);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("---------------------------------------");
			lblNewLabel_3.setForeground(Color.ORANGE);
			lblNewLabel_3.setBounds(71, 257, 361, 22);
		}
		return lblNewLabel_3;
	}
	private JLabel getLabelCheck() {
		if (labelCheck == null) {
			labelCheck = new JLabel("");
			labelCheck.setBounds(68, 115, 69, 48);
		}
		return labelCheck;
	}
	public void showlblCheck() { // ShareVar에서 받은 secondfilename을 통해 lbl에 뱃지 출력
		
		ShowlblCheck check = new ShowlblCheck();
		check.ShowCheck();
		String filePath = Integer.toString(shareVar.checkName);
		
		labelCheck.setIcon(new ImageIcon(filePath));
		labelCheck.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
		
	}
}