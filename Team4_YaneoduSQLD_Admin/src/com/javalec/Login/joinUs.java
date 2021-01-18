package com.javalec.Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.share.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;

public class joinUs extends JDialog {
	ShareVar shareVar = new ShareVar();
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	String userid;
	String userpw;
	String userbirth;
	String useremail;
	String username;
	public JComboBox cbBOX2;
	private JTextField tfID;
	private JTextField tfPW;
	private JTextField tfPWcheck;
	private JTextField tfEMAIL;
	private JTextField tfEMAIL2;
	private JTextField tfNAME;
	String mail;
	String year;
	String month;
	String day;
	int bdayCount1=0;
	int bdayCount2=0;
	int bdayCount3=0;
	int countCheckID = 0;
	int countCheckPW = 0;
	int countCheckEMAIL = 0;
	int countCheckNAME = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			joinUs dialog = new joinUs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public joinUs() {
		getContentPane().setBackground(Color.DARK_GRAY);
		
		setBounds(100, 100, 485, 596);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원가입 JoinUS");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel.setBounds(158, 26, 160, 38);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(79, 106, 24, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PW");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(80, 176, 24, 16);
		getContentPane().add(lblNewLabel_1_1);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(126, 102, 130, 26);
		getContentPane().add(tfID);
		
		JButton btnCheck1 = new JButton("중복체크");
		btnCheck1.setForeground(Color.DARK_GRAY);
		btnCheck1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countCheck();
				idCheckbtn();
				
			}
		});
		btnCheck1.setBounds(257, 100, 117, 29);
		getContentPane().add(btnCheck1);
		
		tfPW = new JTextField();
		tfPW.setColumns(10);
		tfPW.setBounds(127, 172, 130, 26);
		getContentPane().add(tfPW);
		
		tfPWcheck = new JTextField();
		tfPWcheck.setColumns(10);
		tfPWcheck.setBounds(127, 206, 130, 26);
		getContentPane().add(tfPWcheck);
		
		JLabel lblNewLabel_1_2 = new JLabel("PW확인");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(80, 210, 67, 16);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("생년월일");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setBounds(80, 260, 67, 16);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("EMAIL");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setBounds(81, 330, 55, 16);
		getContentPane().add(lblNewLabel_1_2_1);
		
		tfEMAIL = new JTextField();
		tfEMAIL.setColumns(10);
		tfEMAIL.setBounds(128, 325, 87, 26);
		getContentPane().add(tfEMAIL);
		
		JLabel label_1 = new JLabel("@");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(216, 330, 25, 16);
		getContentPane().add(label_1);
		
		tfEMAIL2 = new JTextField();
		tfEMAIL2.setColumns(10);
		tfEMAIL2.setBounds(227, 325, 96, 26);
		getContentPane().add(tfEMAIL2);
		
		JButton btnCheck2 = new JButton("이메일 중복체크");
		btnCheck2.setForeground(Color.DARK_GRAY);
		btnCheck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countCheck();
				emailCheckbtn();
			}
		});
		btnCheck2.setBounds(79, 360, 117, 29);
		getContentPane().add(btnCheck2);
		
		JLabel lblNewLabel_3 = new JLabel("이메일은 중복하여 사용하실 수 없습니다.");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(199, 366, 177, 16);
		getContentPane().add(lblNewLabel_3);
		
		tfNAME = new JTextField();
		tfNAME.setColumns(10);
		tfNAME.setBounds(122, 410, 130, 26);
		getContentPane().add(tfNAME);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("이름");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setBounds(79, 412, 24, 16);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel label = new JLabel("해당 정보로 회원가입 합니다.");
		label.setForeground(Color.WHITE);
		label.setBounds(154, 476, 170, 16);
		getContentPane().add(label);
		
		JButton btnJOIN = new JButton("회원가입");
		btnJOIN.setForeground(Color.DARK_GRAY);
		btnJOIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countCheck();
				listcheck();
				
				
			
			}
		});
		btnJOIN.setBounds(173, 497, 117, 29);
		getContentPane().add(btnJOIN);
		
		JComboBox cbBOX2 = new JComboBox();
		cbBOX2.setForeground(Color.DARK_GRAY);
		cbBOX2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbBOX2.getSelectedIndex() == 0) {
					tfEMAIL2.setText("");
				};
				if(cbBOX2.getSelectedIndex()>0) {
					mail = cbBOX2.getSelectedItem().toString();
					tfEMAIL2.setText(mail);
				};
			}
		});
		cbBOX2.setModel(new DefaultComboBoxModel(new String[] {"직접입력", "naver.com", "gmail.com", "hanmail.net", "yahoo.co.kr", "nate.com", "chol.com"}));
		cbBOX2.setBounds(329, 326, 106, 27);
		getContentPane().add(cbBOX2);
		
		JComboBox cb1 = new JComboBox();
		cb1.setForeground(Color.DARK_GRAY);
		cb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cb1.getSelectedItem().equals("----")) {
					bdayCount1=0;	
				}
				else {
					bdayCount1 = 1;
					year = cb1.getSelectedItem().toString();
					}
			
			}
		});
		cb1.setModel(new DefaultComboBoxModel(new String[] {"----", "1970", "1971", "1972", "1973", "1974", "1975", "1976", 
				"1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", 
				"1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", 
				"2005", "2006", "2007", "2008", "2009", "2010"}));
		
		cb1.setBounds(127, 255, 96, 27);
		getContentPane().add(cb1);
		
		JComboBox cb2 = new JComboBox();
		cb2.setForeground(Color.DARK_GRAY);
		cb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cb2.getSelectedItem().equals("--")) {
					bdayCount2=0;
				}
				else {
					bdayCount2 = 1;
					month = cb2.getSelectedItem().toString();
				}
			}
		});
		cb2.setModel(new DefaultComboBoxModel(new String[] {"--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		cb2.setBounds(230, 255, 67, 27);
		getContentPane().add(cb2);
		
		JComboBox cb3 = new JComboBox();
		cb3.setForeground(Color.DARK_GRAY);
		cb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (cb3.getSelectedItem().equals("--")) {
					bdayCount3=0;
				}else {
					bdayCount3=1;
					day = cb3.getSelectedItem().toString();
				}
			}
		});
		cb3.setModel(new DefaultComboBoxModel(new String[] {"--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cb3.setBounds(299, 255, 67, 27);
		getContentPane().add(cb3);
		
		JLabel lblNewLabel_3_1 = new JLabel("아이디에 공백을 사용하실 수 없습니다. 중복체크로 확인해주세요.");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_3_1.setBounds(79, 131, 295, 16);
		getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("생년월일을 선택해주세요. (Year, Month, Day)");
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_3_1_1.setBounds(79, 286, 205, 16);
		getContentPane().add(lblNewLabel_3_1_1);
		
		
	}
	private void joinus() {
		PreparedStatement ps = null;
		 try{
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			 @SuppressWarnings("unused")
			 Statement stmt_mysql = conn_mysql.createStatement();
			 String A = "insert into yaneodusqld.user (userid, userpw, userbirth, useremail, username) values (?, ?, ?, ?, ?)";
			 String email = tfEMAIL.getText()+"@"+tfEMAIL2.getText();
			 int bday = Integer.parseInt(year+month+day);
			 
			 ps = conn_mysql.prepareStatement(A);
			 ps.setString(1, tfID.getText().trim());
			 shareVar.userId = tfID.getText().trim();
			 System.out.println("share userid from joinus : " + shareVar.userId);
			 ps.setString(2, tfPW.getText().trim());
			 ps.setInt(3, bday);
			 ps.setString(4, email.trim());
			 ps.setString(5, tfNAME.getText().trim());
			 ps.executeUpdate();
			 conn_mysql.close();
			 
			 
		  JOptionPane.showMessageDialog(null,"회원가입 완료");
		  LevelTableAction action = new LevelTableAction();
		  action.InsertInfo();
		  joinUs.this.dispose();
//		  LevelTableAction action = new LevelTableAction();
//		  action.InsertInfo();
		  
		 } catch (Exception e){
			 JOptionPane.showMessageDialog(null,"가입 도중 문제가 발생했습니다. 관리자에게 문의해주세요.");
			
		 }

	}
		public void joinOrders(){
			String WhereDefault = "select userid, useremail from yaneodusqld.user";
			String WhereDefault2 = " where userid = '"+tfID.getText()+"' or useremail = '"+tfEMAIL.getText()+"@"+tfEMAIL2.getText()+"'";
            try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault+WhereDefault2);
		            while(rs.next()){
		            	userid = rs.getString(1);
		            	useremail = rs.getString(2);
		            }
	            	if (userid == null) {
	            		joinus();
	            		System.out.println("조인어스로 넘어감");
	            	}
	            	else{
			            if (userid.equals(tfID.getText())) {
			            	JOptionPane.showMessageDialog(null,"id 중복확인을 해주세요.");
			            	}
			            else if(userid.equals(tfID.getText()) && useremail.equals(tfEMAIL.getText())) {
			            	JOptionPane.showMessageDialog(null,"id및 이메일 중복확인을 해주세요.");
			            }
			            else if (useremail.equals(tfEMAIL.getText())) {
			            	JOptionPane.showMessageDialog(null,"이메일 중복확인을 해주세요.");
			            }
			            
	            	}  
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null,"조건 - 회원가입 오류");
	       }
	}
		
		private void listcheck() {
			if (tfID.getText().length()==0 || tfPW.getText().length()==0||tfEMAIL.getText().length()==0||tfNAME.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"모든 정보를 입력해주셔야 합니다.");
			}
			
			else {
				if(!tfPW.getText().equals(tfPWcheck.getText())) {
					JOptionPane.showMessageDialog(null,"비밀번호가 비밀번호 확인과 일치하지 않습니다.");
				}
				else if (bdayCount1+bdayCount2+bdayCount3 != 3) {
					JOptionPane.showMessageDialog(null,"생년월일을 입력해주세요.");
				}
				else if (countCheckID > 0 || countCheckPW > 0 || countCheckEMAIL > 0|| countCheckNAME > 0) {
					JOptionPane.showMessageDialog(null,"회원정보에 공백을 사용하실 수 없습니다.");
					countCheckID =0;
					countCheckPW=0;
					countCheckEMAIL=0;
					countCheckNAME=0;
				}
				else if (tfEMAIL2.getText().length()==0) {
	        		JOptionPane.showMessageDialog(null,"이메일 주소를 정확히 입력해주세요. ");
	        	}
				
				else if (tfID.getText().length() > 10) {
					JOptionPane.showMessageDialog(null,"아이디는 10자 이하만 가능합니다.");
				}
				else if (tfPW.getText().length() > 10) {
					JOptionPane.showMessageDialog(null,"비밀번호는 10자 이하만 가능합니다.");
				}
				else if (tfEMAIL.getText().length() > 10) {
					JOptionPane.showMessageDialog(null,"이메일은 20자 이하만 가능합니다.");
				}
				else if (tfNAME.getText().length() > 10) {
					JOptionPane.showMessageDialog(null,"이름은 10자 이하만 가능합니다.");
				}
				
				else {
					 joinOrders();
					 System.out.println("ddddddd");
				}
			}
		}

		private void idCheckbtn(){
			String WhereDefault = "select userid from yaneodusqld.user";
			String WhereDefault2 = " where userid = '"+tfID.getText()+"'";
		    try{
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		        Statement stmt_mysql = conn_mysql.createStatement();
		        ResultSet rs = stmt_mysql.executeQuery(WhereDefault+WhereDefault2);
		            while(rs.next()){
		            	userid = rs.getString(1);
		            }

		        	if (tfID.getText().equals(userid)){
		        		JOptionPane.showMessageDialog(null,"중복된 아이디입니다. 다른아이디를 입력해주세요.");
		        	} 
		        	else if (countCheckID >0) {
		    				JOptionPane.showMessageDialog(null,"아이디에 공백을 쓰실 수 없습니다.");
							countCheckID =0;
							countCheckPW=0;
							countCheckEMAIL=0;
							countCheckNAME=0;
		    		
		        	}
		        	else if (tfID.getText().length() > 15) {
		        		JOptionPane.showMessageDialog(null,"아이디는 10자 이하만 가능합니다.");	
		        	}
		        	
		        	else if(!tfID.getText().equals(userid) && tfID.getText().length()>0) {
		        		JOptionPane.showMessageDialog(null,"사용하실 수 있는 아이디입니다.");
		        	}
		        
		        	else {
		        		JOptionPane.showMessageDialog(null,"아이디를 입력해주세요.");
		        	}
		        conn_mysql.close();
		    }
		    catch (Exception e){
		        e.printStackTrace();
		    }	
		}
		private void emailCheckbtn(){
			String eemail = tfEMAIL.getText()+"@"+tfEMAIL2.getText();
			String WhereDefault = "select useremail from yaneodusqld.user";
			String WhereDefault2 = " where useremail = '"+eemail+"'";
		    try{
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		        Statement stmt_mysql = conn_mysql.createStatement();
		        ResultSet rs = stmt_mysql.executeQuery(WhereDefault+WhereDefault2);
		            while(rs.next()){
		            	useremail = rs.getString(1);
		            }
		        	if (eemail.equals(useremail)){
		        		JOptionPane.showMessageDialog(null,"중복된 이메일입니다. 다른이메일을 입력해주세요.");
		        	} 
		        	else if (tfEMAIL2.getText().length()==0) {
		        		JOptionPane.showMessageDialog(null,"이메일 주소를 정확히 입력해주세요. ");
		        	}
		        	else if (countCheckEMAIL > 0) {
		        		JOptionPane.showMessageDialog(null,"이메일에 공백을 쓰실 수 없습니다.");
						countCheckID =0;
						countCheckPW=0;
						countCheckEMAIL=0;
						countCheckNAME=0;
		        	}
		        	else if (eemail.length() > 20) {
		        		JOptionPane.showMessageDialog(null,"이메일은 20자 이하만 가능합니다.");	
		        	}
		        	else if(tfEMAIL.getText().length()==0) {
		        		JOptionPane.showMessageDialog(null,"이메일을 입력해주세요.");
		        	}
		        	else if(!eemail.equals(useremail) && eemail.length()>0) {
		        		JOptionPane.showMessageDialog(null,"사용하실 수 있는 이메일입니다.");
		        	}
		        	
		        conn_mysql.close();
		    }
		    catch (Exception e){
		        e.printStackTrace();
		    }	
		}

		private void countCheck() {
			for (int i = 0; i <tfPW.getText().length(); i++) {
				if(tfPW.getText().charAt(i) == ' ') {
					countCheckPW =+1;
				}
			}
		
			for (int i = 0; i <tfNAME.getText().length(); i++) {
				if(tfNAME.getText().charAt(i) == ' ') {
					countCheckNAME =+1;
				}
			}
			for (int i = 0; i <tfEMAIL.getText().length(); i++) {
				if(tfEMAIL.getText().charAt(i) == ' ') {
					countCheckEMAIL =+1;
				}
			}
			for (int i = 0; i <tfID.getText().length(); i++) {
				if(tfID.getText().charAt(i) == ' ') {
					countCheckID =+1;
				}
			}
	
	}
		
}		
		

