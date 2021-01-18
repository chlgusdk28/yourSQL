package com.javalec.Quiz;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextArea;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.javalec.Loginfunction.Bean;
import com.javalec.Result.ResultMain;
import com.javalec.Select.SelectQuiz;
import com.javalec.share.ShareVar;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class WrongQuestion extends JDialog {
	Random random = new Random();
	private JLabel lbQuiz;
	private TextArea taQuiz;
	private JRadioButton rbtnQuestion1;
	private JRadioButton rbtnQuestion2;
	private JRadioButton rbtnQuestion3;
	private JRadioButton rbtnQuestion4;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private TextArea txSolve;
	private JLabel lbSolve;
	private JButton btnNext;
	private JButton btnOK;
	ShareVar shareVar = new ShareVar();
	WrongQuestionAction wrongQuestionAction = new WrongQuestionAction();
	int theNumber=0;
	int[] randomNum = new int [shareVar.cbData];
	String[] resultDB;
	int[] quizNumber;
	int randomMaxNum;
	int increase = 0;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	Thread thread;
	ArrayList<BeanQuiz> beanList = WrongQuestionAction.CallWrong(shareVar.userId);
	ArrayList<Integer> WrongRight = new ArrayList<Integer>();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WrongQuestion dialog = new WrongQuestion();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WrongQuestion() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("오답노트");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				lbSolve.setVisible(false);
				btnNext.setVisible(false);
				txSolve.setVisible(false);
				randomMaxNum = beanList.size();
				randomNum =	randomQuiz(beanList.size());
				taQuiz.setText(beanList.get(randomNum[theNumber]).getQuizcontents()+"\n\n"+beanList.get(randomNum[theNumber]).getQuestion1()+"\n"+beanList.get(randomNum[theNumber]).getQuestion2()+"\n"+beanList.get(randomNum[theNumber]).getQuestion3()+"\n"+beanList.get(randomNum[theNumber]).getQuestion4());
				lbQuiz.setText("문제 "+(theNumber+1));
				resultDB = new String[beanList.size()];
				quizNumber = new int [beanList.size()];
				for(int i = 0 ; i < resultDB.length ; i++) {
					resultDB[i]="";
				}
				for(int i = 0 ; i < quizNumber.length ; i++) {
					quizNumber[i]=0;
				}
			}
		});
		setBounds(100, 100, 506, 613);
		getContentPane().setLayout(null);
		getContentPane().add(getLbQuiz());
		getContentPane().add(getTaQuiz());
		getContentPane().add(getRbtnQuestion1());
		getContentPane().add(getRbtnQuestion2());
		getContentPane().add(getRbtnQuestion3());
		getContentPane().add(getRbtnQuestion4());
		getContentPane().add(getTxSolve());
		getContentPane().add(getLbSolve());
		getContentPane().add(getBtnNext());
		getContentPane().add(getBtnOK());
		setJMenuBar(getMenuBar());
	}
	private JLabel getLbQuiz() {
		if (lbQuiz == null) {
			lbQuiz = new JLabel("");
			lbQuiz.setBounds(6, 6, 61, 16);
		}
		return lbQuiz;
	}
	private TextArea getTaQuiz() {
		if (taQuiz == null) {
			taQuiz = new TextArea("",1,1,taQuiz.SCROLLBARS_VERTICAL_ONLY);
			taQuiz.setEditable(false);
			taQuiz.setBounds(43, 35, 404, 179);
		}
		return taQuiz;
	}
	private JRadioButton getRbtnQuestion1() {
		if (rbtnQuestion1 == null) {
			rbtnQuestion1 = new JRadioButton("1");
			rbtnQuestion1.setForeground(Color.BLACK);
			buttonGroup.add(rbtnQuestion1);
			rbtnQuestion1.setBounds(56, 220, 52, 23);
		}
		return rbtnQuestion1;
	}
	private JRadioButton getRbtnQuestion2() {
		if (rbtnQuestion2 == null) {
			rbtnQuestion2 = new JRadioButton("2");
			rbtnQuestion2.setForeground(Color.BLACK);
			buttonGroup.add(rbtnQuestion2);
			rbtnQuestion2.setBounds(164, 220, 52, 23);
		}
		return rbtnQuestion2;
	}
	private JRadioButton getRbtnQuestion3() {
		if (rbtnQuestion3 == null) {
			rbtnQuestion3 = new JRadioButton("3");
			rbtnQuestion3.setForeground(Color.BLACK);
			buttonGroup.add(rbtnQuestion3);
			rbtnQuestion3.setBounds(272, 220, 52, 23);
		}
		return rbtnQuestion3;
	}
	private JRadioButton getRbtnQuestion4() {
		if (rbtnQuestion4 == null) {
			rbtnQuestion4 = new JRadioButton("4");
			rbtnQuestion4.setForeground(Color.BLACK);
			buttonGroup.add(rbtnQuestion4);
			rbtnQuestion4.setBounds(380, 220, 52, 23);
		}
		return rbtnQuestion4;
	}
	private TextArea getTxSolve() {
		if (txSolve == null) {
			txSolve = new TextArea("",1,1,taQuiz.SCROLLBARS_VERTICAL_ONLY);
			txSolve.setEditable(false);
			txSolve.setBounds(51, 334, 388, 199);
		}
		return txSolve;
	}
	private JLabel getLbSolve() {
		if (lbSolve == null) {
			lbSolve = new JLabel("해설");
			lbSolve.setForeground(Color.WHITE);
			lbSolve.setBounds(56, 301, 61, 16);
		}
		return lbSolve;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("다음 문제");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lbSolve.setVisible(false);
					btnNext.setVisible(false);
					btnOK.setVisible(true);
					txSolve.setVisible(false);
					rbtnQuestion1.setSelected(false);
					rbtnQuestion2.setSelected(false);
					rbtnQuestion3.setSelected(false);
					rbtnQuestion4.setSelected(false);
					rbtnQuestion1.setForeground(Color.BLACK);
					rbtnQuestion2.setForeground(Color.BLACK);
					rbtnQuestion3.setForeground(Color.BLACK);
					rbtnQuestion4.setForeground(Color.BLACK);
					theNumber++;
					if(theNumber == beanList.size()) {							// 특정 숫자 넘어갔을때 다음 화면으로 넘기기
						dispose();
						ResultMain resultMain = new ResultMain();
						resultMain.setLocationRelativeTo(null);
						resultMain.setVisible(true);
						inputResult inputResult = new inputResult();
						for(int i = 0; i<WrongRight.size();i++) {
							inputResult.wrongResultAction(WrongRight.get(i));
							System.out.println(WrongRight.get(i));
						}
					}
					lbQuiz.setText("문제 "+(theNumber+1));
					randomMaxNum = beanList.size();
					taQuiz.setText(beanList.get(randomNum[theNumber]).getQuizcontents()+"\n\n"+beanList.get(randomNum[theNumber]).getQuestion1()+"\n"+beanList.get(randomNum[theNumber]).getQuestion2()+"\n"+beanList.get(randomNum[theNumber]).getQuestion3()+"\n"+beanList.get(randomNum[theNumber]).getQuestion4());
				}
			});
			btnNext.setBounds(325, 299, 117, 29);
		}
		return btnNext;
	}
	
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("확인");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(selectQuestion()==0) {
						JOptionPane.showMessageDialog(WrongQuestion.this, "번호를 선택해주세요.");
					}else {
						lbSolve.setVisible(true);
						btnNext.setVisible(true);
						btnOK.setVisible(false);
						txSolve.setVisible(true);
						txSolve.setText("정답 : "+beanList.get(randomNum[theNumber]).getAnswer()+"\n"+beanList.get(randomNum[theNumber]).getExplanation());
						if(selectQuestion() == beanList.get(randomNum[theNumber]).getAnswer()) {												// 정답인경우 오답인경우 나누기
							correctColor();
							resultDB[increase] = "O";
							shareVar.rightScore++;
							System.out.println(beanList.get(randomNum[theNumber]).getQuiznum());
							WrongRight.add(beanList.get(randomNum[theNumber]).getQuiznum());
						}else {
							resultDB[increase] = "X";
							shareVar.wrongScore++;
							if(rbtnQuestion1.getText().equals(Integer.toString(beanList.get(randomNum[theNumber]).getAnswer()))) {
								rbtnQuestion1.setForeground(Color.RED);
							}
							if(rbtnQuestion2.getText().equals(Integer.toString(beanList.get(randomNum[theNumber]).getAnswer()))) {
								rbtnQuestion2.setForeground(Color.RED);
							}
							if(rbtnQuestion3.getText().equals(Integer.toString(beanList.get(randomNum[theNumber]).getAnswer()))) {
								rbtnQuestion3.setForeground(Color.RED);
							}
							if(rbtnQuestion4.getText().equals(Integer.toString(beanList.get(randomNum[theNumber]).getAnswer()))) {
								rbtnQuestion4.setForeground(Color.RED);
							}
						}
						if(theNumber == beanList.size()-1) {
							btnNext.setText("결과");
						}
						quizNumber[increase]= beanList.get(randomNum[theNumber]).getQuiznum();
						increase ++;
					}
					
				}
			});
			btnOK.setBounds(325, 299, 117, 29);
		}
		return btnOK;
	}
	
	
	public int[] randomQuiz(int size) {
		int[] randomNumber = new int [size];
		for(int i = 0 ; i<size ; i++) {								// 범위 받아오기
			randomNumber[i] = random.nextInt(randomMaxNum);
			for(int l =0 ;l<i;l++) {
				if(randomNumber[i] == randomNumber[l]) {
					i--;
				}
			}
		}
		return randomNumber;
	}
	private int selectQuestion() {
		if(rbtnQuestion1.isSelected() == true) {
			return 1;
		}
		if(rbtnQuestion2.isSelected() == true) {
			return 2;
		}
		if(rbtnQuestion3.isSelected() == true) {
			return 3;
		}
		if(rbtnQuestion4.isSelected() == true) {
			return 4;
		}
		return 0;
	}
	private void correctColor() {
		if(rbtnQuestion1.isSelected() == true) {
			rbtnQuestion1.setForeground(Color.BLUE);
		}
		if(rbtnQuestion2.isSelected() == true) {
			rbtnQuestion2.setForeground(Color.BLUE);
		}
		if(rbtnQuestion3.isSelected() == true) {
			rbtnQuestion3.setForeground(Color.BLUE);
		}
		if(rbtnQuestion4.isSelected() == true) {
			rbtnQuestion4.setForeground(Color.BLUE);
		}
	}
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenu());
		}
		return menuBar;
	}
	private JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu("메뉴");
			menu.add(getMenuItem_1());
			menu.add(getMenuItem_2());
			menu.add(getMenuItem_3());
		}
		return menu;
	}
	private JMenuItem getMenuItem_1() {
		if (menuItem_1 == null) {
			menuItem_1 = new JMenuItem("문제 선택");
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SelectQuiz selectQuiz = new SelectQuiz();
					selectQuiz.setLocationRelativeTo(null);
					selectQuiz.setVisible(true);
					dispose();
				}
			});
		}
		return menuItem_1;
	}
	private JMenuItem getMenuItem_2() {
		if (menuItem_2 == null) {
			menuItem_2 = new JMenuItem("로그아웃");
			menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 메인화면 보여주기
					dispose();
				}
			});
		}
		return menuItem_2;
	}
	private JMenuItem getMenuItem_3() {
		if (menuItem_3 == null) {
			menuItem_3 = new JMenuItem("종료");
			menuItem_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return menuItem_3;
	}
	


}
