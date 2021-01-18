package com.javalec.Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.javalec.Admin.AdminMain;
import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.Select.SelectQuiz;
import com.javalec.share.ShareVar;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class login extends JDialog {
	ShareVar shareVar = new ShareVar();
	
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;

	private JFrame frame;
	private JTextField tfId;
	private JPasswordField tfPw;
	String id = "";
	String name = "";
	String pw = "";
	int level;

	private final JPanel contentPanel = new JPanel();
	private JTextField tfID;
	private JPasswordField tfPW;
	private JLabel lblYaneodu;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			login dialog = new login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				login.this.setLocationRelativeTo(null);
				
				showlblYaneodu();
				
			}
		});
		setTitle("YaneoduSQLD");
		setBounds(100, 100, 600, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfID = new JTextField();
			tfID.setColumns(10);
			tfID.setBounds(197, 306, 130, 26);
			contentPanel.add(tfID);
		}
		{
			tfPW = new JPasswordField();
			tfPW.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SaveAction();
					//로그인시 정보가 일치하면 id, pw, name, lv를 쉐어바에 저장하는 메소드
					loginAction();	
					//저장된 쉐어바 메소드와 비교하여 일치하면 로그인이 진행.
				}
			});
			tfPW.setBounds(197, 343, 130, 26);
			contentPanel.add(tfPW);
		}
		{
			JButton btnLogin = new JButton("Login");
			btnLogin.setForeground(new Color(51, 51, 51));
			btnLogin.setBackground(new Color(255, 255, 255));
			btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SaveAction();
					//로그인시 정보가 일치하면 id, pw, name, lv를 쉐어바에 저장하는 메소드
					loginAction();	
					//저장된 쉐어바 메소드와 비교하여 일치하면 로그인이 진행.
				}
			});
			btnLogin.setBounds(339, 306, 117, 63);
			contentPanel.add(btnLogin);
		}
		{
			JButton btnNewButton_1 = new JButton("ID 찾기");
			btnNewButton_1.setForeground(new Color(51, 51, 51));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchId();
					// 아이디 찾기 창이 열림 
				}
			});
			btnNewButton_1.setBounds(122, 401, 117, 29);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton_1_1 = new JButton("PW 찾기");
			btnNewButton_1_1.setForeground(new Color(51, 51, 51));
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchPw();
					// 패스워드 찾기 창이 열림 
				}
			});
			btnNewButton_1_1.setBounds(241, 401, 117, 29);
			contentPanel.add(btnNewButton_1_1);
		}
		{
			JButton btnNewButton_1_1_1 = new JButton("JOINUS");
			btnNewButton_1_1_1.setForeground(new Color(51, 51, 51));
			btnNewButton_1_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					joinUs();
					//회원가입 창이 열림 
				}
			});
			btnNewButton_1_1_1.setBounds(358, 401, 117, 29);
			contentPanel.add(btnNewButton_1_1_1);
		}
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(159, 311, 26, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblPw = new JLabel("PW");
			lblPw.setForeground(new Color(255, 255, 255));
			lblPw.setBounds(159, 349, 26, 16);
			contentPanel.add(lblPw);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("√ 해당 프로그램은 SQLD 자격증을 목표로 공부하는");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(148, 448, 273, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("학생들을 위해 만들어진 프로그램입니다.");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(164, 465, 273, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("* 오류 및 문의는 team4@gamil.com 으로 보내주세요.");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			lblNewLabel_1.setBounds(159, 499, 331, 16);
			contentPanel.add(lblNewLabel_1);
		}
		contentPanel.add(getLblNewLabel_3());
		contentPanel.add(getLblYaneodu());
	}

		// 쉐어에 데이터를 저장하는 작업.
		public void SaveAction(){
			System.out.println("saveAction userid : " + shareVar.userId);
			System.out.println("saveAction tfID : " + tfID.getText().trim());
			PreparedStatement ps=null;
			String WhereDefault = "select userid, username, userpw from yaneodusqld.user ";
			String WhereDefault2 = " where userid = '" + tfID.getText().trim() + "'";
            try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault+WhereDefault2);
	            while(rs.next()){
	            	id = rs.getString(1);
                	name = rs.getString(2);
                	pw = rs.getString(3);
                	ShareVar.userId = id;
                	System.out.println("saveAction userid : " + shareVar.userId);
                	
                	ShareVar.userName = name;
                	ShareVar.userPw = pw;
	            }
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
		}
		public void loginAction() {
			
			if(tfID.getText().equals("admin")&&tfPW.getText().equals("admin")) {
	        	AdminMain adminMain = new AdminMain();
	        	adminMain.setLocationRelativeTo(null);
	        	adminMain.setVisible(true);
	        	dispose();
	        } else if(ShareVar.userId.equals(tfID.getText())&&ShareVar.userPw.equals(tfPW.getText())&&tfID.getText().length()>0) {
			        	JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
//			        	LevelTableAction action = new LevelTableAction();
//			        	action.InsertInfo();
			        	login.this.dispose();
			        	SelectQuiz quiz = new SelectQuiz();
			        	quiz.setVisible(true);
		        } 
		        else if(tfID.getText().length()==0 || tfPW.getText().length()==0) {
		        		JOptionPane.showMessageDialog(null, "아이디 및 패스워드를 입력해주세요.");
		        }
		        else if(ShareVar.userId.equals(tfID.getText())&& !ShareVar.userPw.equals(tfPW.getText())) {
		    
		        		JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다.");
		        }
		        else{
		        		JOptionPane.showMessageDialog(null, "아이디를 확인해주세요.");
		        }	
		    }
		
		public void	searchId(){
		
			searchId sid = new searchId();
			sid.setLocationRelativeTo(null);
			sid.setVisible(true);
		}
		
		public void	searchPw(){
			
			searchPw sPw = new searchPw();
			sPw.setLocationRelativeTo(null);
			sPw.setVisible(true);
		}
		public void	joinUs(){
			
			joinUs us = new joinUs();
			us.setLocationRelativeTo(null);
			us.setVisible(true);
		}
	private JLabel getLblYaneodu() {
		if (lblYaneodu == null) {
			lblYaneodu = new JLabel("");
			lblYaneodu.setVerticalAlignment(SwingConstants.TOP);
			lblYaneodu.setIcon(null);
			lblYaneodu.setBounds(0, 0, 600, 578);
		}
		return lblYaneodu;
	}
	public void showlblYaneodu() { // ShareVar에서 받은 secondfilename을 통해 lbl에 뱃지 출력
		ShowImg lblimg = new ShowImg();
		com.javalec.share.ShareVar shareVar = new com.javalec.share.ShareVar();
		
		lblimg.Showimg();
		String filePath = Integer.toString(shareVar.imgname);
		
		lblYaneodu.setIcon(new ImageIcon(filePath));
		lblYaneodu.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
		
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("EXIT");
			lblNewLabel_3.setBackground(Color.DARK_GRAY);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					login.this.dispose();		
				}
			});
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setBounds(513, 519, 61, 35);
		}
		return lblNewLabel_3;
	}
}
