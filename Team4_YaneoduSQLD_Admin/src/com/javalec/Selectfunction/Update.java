package com.javalec.Selectfunction;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.javalec.Login.login;
import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.Select.SelectQuiz;
import com.javalec.chat.ChatMain;
import com.javalec.share.ShareVar;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;

public class Update extends JDialog {
	ShareVar shareVar = new ShareVar();
	private JMenuBar mnMenu;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenu 메뉴;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JLabel lbLevel;
	private JLabel lblNewLabel_4;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfEmail;
	private JButton btnUpdate;
	private JButton btnNewButton;
	private JPasswordField tfPassword;
	private JPasswordField tfPasswordCheck;

	
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JLabel lblLevel;
	private JComboBox cbBirthYear;
	private JComboBox cbBirthMonth;
	private JComboBox cbBirthDay;
	private JLabel badge;
	private JButton button;
	private JButton btnNewButton_1;
	String useremail;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update dialog = new Update();
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
	public Update() {
		getContentPane().setBackground(new Color(51, 51, 51));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			showlblBadge();
			listInfo();
			liveLevel();
		
//			liveBadge();
			
			}
		});
		setBounds(100, 100, 460, 640);
		getContentPane().setLayout(null);
		getContentPane().add(getLbLevel());
		getContentPane().add(getLblNewLabel_4());
		getContentPane().add(getLabel());
		getContentPane().add(getLabel_1());
		getContentPane().add(getLabel_2());
		getContentPane().add(getLabel_3());
		getContentPane().add(getLabel_4());
		getContentPane().add(getTfId());
		getContentPane().add(getTfName());
		getContentPane().add(getTfEmail());
		getContentPane().add(getBtnUpdate());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getTfPassword());
		getContentPane().add(getTfPasswordCheck());
		getContentPane().add(getLblLevel());
		getContentPane().add(getCbBirthYear());
		getContentPane().add(getCbBirthMonth());
		getContentPane().add(getCbBirthDay());
		getContentPane().add(getLabel_5_1());
		getContentPane().add(getButton());
		getContentPane().add(getBtnNewButton_1());
		
		setJMenuBar(getMenuBar());

	}

	
	
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(get메뉴());
		}
		return menuBar;
	}
	private JMenu get메뉴() {
		if (메뉴 == null) {
			메뉴 = new JMenu("메뉴");
			메뉴.add(getMntmNewMenuItem());
			메뉴.add(getMntmNewMenuItem_1());
			메뉴.add(getMntmNewMenuItem_2());
		}
		return 메뉴;
	}
	private JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem("정보 공유");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ChatMain chatMain = new ChatMain();
					chatMain.setVisible(true);
				}
			});
		}
		return mntmNewMenuItem;
	}
	private JMenuItem getMntmNewMenuItem_1() {
		if (mntmNewMenuItem_1 == null) {
			mntmNewMenuItem_1 = new JMenuItem("로그아웃");
			mntmNewMenuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					login login = new login();
					login.setVisible(true);
				}
			});
		}
		return mntmNewMenuItem_1;
	}
	private JMenuItem getMntmNewMenuItem_2() {
		if (mntmNewMenuItem_2 == null) {
			mntmNewMenuItem_2 = new JMenuItem("종료");
			mntmNewMenuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Update.this.dispose();
					
					}
			});
		}
		return mntmNewMenuItem_2;
	}
	private JLabel getLbLevel() {
		if (lbLevel == null) {
			lbLevel = new JLabel("0");
			lbLevel.setForeground(Color.WHITE);
			lbLevel.setBounds(235, 118, 61, 16);
		}
		return lbLevel;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("아이디 :");
			lblNewLabel_4.setForeground(Color.WHITE);
			lblNewLabel_4.setBounds(89, 173, 61, 16);
		}
		return lblNewLabel_4;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("이름 :");
			label.setForeground(Color.WHITE);
			label.setBounds(89, 231, 61, 16);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("비밀번호 :");
			label_1.setForeground(Color.WHITE);
			label_1.setBounds(89, 281, 61, 16);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("비밀번호 확인 :");
			label_2.setForeground(Color.WHITE);
			label_2.setBounds(89, 330, 78, 16);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("생년월일 :");
			label_3.setForeground(Color.WHITE);
			label_3.setBounds(89, 459, 61, 16);
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("이메일주소 :");
			label_4.setForeground(Color.WHITE);
			label_4.setBounds(89, 379, 78, 16);
		}
		return label_4;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setEditable(false);
			tfId.setBounds(173, 168, 193, 26);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(173, 226, 193, 26);
		}
		return tfName;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(173, 374, 193, 26);
		}
		return tfEmail;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.setForeground(Color.BLACK);
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateInfo();
					
				}
			});
			btnUpdate.setBounds(144, 526, 93, 29);
		}
		return btnUpdate;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("이전");
			btnNewButton.setForeground(Color.BLACK);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gotoMain();
					Update.this.dispose();
					SelectQuiz selectQuiz = new SelectQuiz();
					selectQuiz.setLocationRelativeTo(null);
					selectQuiz.setVisible(true);
				}
			});
			btnNewButton.setBounds(232, 526, 93, 29);
		}
		return btnNewButton;
	}
	private JPasswordField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JPasswordField();
			tfPassword.setBounds(173, 276, 193, 26);
		}
		return tfPassword;
	}
	private JPasswordField getTfPasswordCheck() {
		if (tfPasswordCheck == null) {
			tfPasswordCheck = new JPasswordField();
			tfPasswordCheck.setBounds(173, 325, 193, 26);
		}
		return tfPasswordCheck;
	}

	private void gotoMain() {
		SelectQuiz main = new SelectQuiz();
		main.setLocationRelativeTo(null);
		
		
		
	}
	
	private void listInfo() {
      
		

		
        //tfSelection.setText(stSequence);
        String WhereDefault = "select userid, username, userbirth, useremail from user "; 
        String WhereDefault2 = "where userid = '" + shareVar.userId + "'" ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
            

           
            while(rs.next()){
            	tfId.setText(rs.getString(1));
            	tfName.setText(rs.getString(2));
            	cbBirthYear.setSelectedItem(rs.getString(3).substring(0,4));
            	cbBirthMonth.setSelectedItem(rs.getString(3).substring(4,6));
            	cbBirthDay.setSelectedItem(rs.getString(3).substring(6,8));
            	tfEmail.setText(rs.getString(4));
            	
            	
            }
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
	}
	
	private void updateInfo(){
		if(weakPassword() == true && inCorrectName() == true && checkEmail() == true &&  checkPassword() == true && overlapCheck()==true) {
			
			String checkpw = tfPasswordCheck.getText().toString();
			String id = tfId.getText().toString();
			String name = tfName.getText().toString();
			String pw = tfPassword.getText().toString();
			String birth = cbBirthYear.getSelectedItem().toString();
			String birthmonth = cbBirthMonth.getSelectedItem().toString();
			String birthday = cbBirthDay.getSelectedItem().toString();
			String email = tfEmail.getText().toString();
			UpdateAction updateyo = new UpdateAction(id, name, pw, birth+birthmonth+birthday, email);
			boolean msg = updateyo.updateAction2();
			
			
			if(msg == true) {
				JOptionPane.showMessageDialog(Update.this, "수정완료!");
			}
			
		}else {
			
		}
	}

	
	private boolean weakPassword() {
		
		String getpw = tfPassword.getText().toString();
		String trimpw = tfPassword.getText().trim().toString().replaceAll(" ", "");
		
		if(getpw.length() - trimpw.length() != 0 || getpw.length() == 0){
			JOptionPane.showMessageDialog(Update.this, "비밀번호에는 공백을 사용할 수 없습니다.");
			
			return false;
		}else {
			
			return true;
		}
		
		
		
	}
	

	private boolean checkPassword() {
		
		String checkpw = tfPasswordCheck.getText().toString();
		String getpw = tfPassword.getText().toString();
		if(getpw.equals(checkpw)) {
			
			return true;
		}else {
			JOptionPane.showMessageDialog(Update.this, "비밀번호가 서로 다릅니다.");
			
			return false;
		}
			
	}
	
	
	
	private boolean inCorrectName() {
		String getName = tfName.getText().toString();
		String trimName = tfName.getText().trim().toString().replaceAll(" ", "");
		
		if(getName.length() - trimName.length() != 0 || getName.length() == 0){
			JOptionPane.showMessageDialog(Update.this, "이름에는 공백을 사용할 수 없습니다.");
			
			return false;
		}else {
			
			return true;
		}
	}
	
	private boolean checkEmail() {
		String getEmail = tfEmail.getText().toString();
		String trimEmail = tfEmail.getText().trim().toString().replaceAll(" ", "");
		
		if(getEmail.length() - trimEmail.length() != 0 || getEmail.length() == 0){
			JOptionPane.showMessageDialog(Update.this, "이메일을 확인해주세요.");
			
			return false;
		}else {
			
			return true;
		}
	}
	
	
	
	private void liveLevel() {
		 String WhereDefault = "select level from level "; 
	        String WhereDefault2 = "where user_userid = '" + shareVar.userId + "'" ;
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();

	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

	            while(rs.next()){
	            	lbLevel.setText(rs.getString(1));
	            	
	            }
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
		
	}
	
	
		
	
	private JLabel getLblLevel() {
		if (lblLevel == null) {
			lblLevel = new JLabel("Level");
			lblLevel.setForeground(Color.WHITE);
			lblLevel.setBackground(Color.WHITE);
			lblLevel.setBounds(182, 117, 62, 18);
		}
		return lblLevel;
	}
	private JComboBox getCbBirthYear() {
		
		if (cbBirthYear == null) {
			cbBirthYear = new JComboBox();
			cbBirthYear.setForeground(Color.BLACK);
			cbBirthYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			cbBirthYear.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			cbBirthYear.setModel(new DefaultComboBoxModel(new String[] {"1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"}));
			cbBirthYear.setBounds(173, 456, 74, 27);
		}
		return cbBirthYear;
	}
	private JComboBox getCbBirthMonth() {
		
		if (cbBirthMonth == null) {
			cbBirthMonth = new JComboBox();
			cbBirthMonth.setForeground(Color.BLACK);
			cbBirthMonth.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			cbBirthMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
			cbBirthMonth.setBounds(243, 456, 62, 27);
		}
		return cbBirthMonth;
	}
	private JComboBox getCbBirthDay() {
		if (cbBirthDay == null) {
			cbBirthDay = new JComboBox();
			cbBirthDay.setForeground(Color.BLACK);
			cbBirthDay.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			cbBirthDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			cbBirthDay.setBounds(303, 456, 62, 27);
		}
		return cbBirthDay;
	}
	private JLabel getLabel_5_1() {
		if (badge == null) {
			badge = new JLabel("");
			badge.setBounds(175, 28, 78, 75);
		}
		return badge;
	}
	
	private JButton getButton() {
		if (button == null) {
			button = new JButton("탈퇴하기");
			button.setForeground(Color.BLACK);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int delete = JOptionPane.showConfirmDialog(Update.this, "탈퇴 하시겠습니까", "탈퇴 메세지", JOptionPane.YES_NO_OPTION);
					if(JOptionPane.YES_OPTION == delete) {
						saveData();
						deleteLevelAction();
						deleteUserAction();
						dispose();
						login login = new login();
						login.setVisible(true);
						login.setLocationRelativeTo(null);
					} else if (JOptionPane.NO_OPTION == delete || JOptionPane.CLOSED_OPTION == delete){
						return;
					}
					
//					DeleteConfirm deleteConfirm = new DeleteConfirm();
//					deleteConfirm.setLocationRelativeTo(null);
//					deleteConfirm.setVisible(true);
					
					
					
//					saveData();
//					deleteLevelAction();
//					deleteUserAction();
//					dispose();
//					login login = new login();
//					login.setLocationRelativeTo(null);
//					login.setVisible(true);
				
				}
			});
			button.setBounds(355, 20, 79, 40);
		}
		return button;
	}
	
	
	
	public boolean saveData() {
		PreparedStatement ps = null;
		 try{
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			 @SuppressWarnings("unused")
			 Statement stmt_mysql = conn_mysql.createStatement();
			 
			 String A = "insert into yaneodusqld.savedata (duser_id, dusername, duseremail, duserbirth, ddeletetime) values (?, ?, ?, ?, now())";
			 
			 ps = conn_mysql.prepareStatement(A);
			 
			 
			 ps.setString(1, tfId.getText());
			 ps.setString(2, tfName.getText());
			 ps.setString(3, tfEmail.getText());
			 ps.setString(4, cbBirthYear.getSelectedItem().toString()+cbBirthMonth.getSelectedItem().toString()+cbBirthDay.getSelectedItem().toString());
			 ps.executeUpdate();
			 
			 conn_mysql.close();
			 JOptionPane.showMessageDialog(Update.this, "탈퇴완료");
		 } catch (Exception e){
			 JOptionPane.showMessageDialog(Update.this, "탈퇴실패");
			 return false;
		 }
		 return true; 
		 
		 
	}
	public boolean deleteLevelAction() {
		PreparedStatement ps = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			@SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String A = "delete from level where user_userid = '"+ shareVar.userId + "'";
			
			ps = conn_mysql.prepareStatement(A);
			
			
			ps.executeUpdate();
			
			conn_mysql.close();
							
		} catch (Exception e){
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean deleteUserAction() {
        PreparedStatement ps = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

            String A = "delete from user where userid =  '"+ shareVar.userId + "'";

            ps = conn_mysql.prepareStatement(A);
            
            
            ps.executeUpdate();

            conn_mysql.close();
            				
        } catch (Exception e){
          
        	e.printStackTrace();
            return false;
        }
        return true;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("이메일 중복확인");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(overlapCheck()== true && shareVar.checkEmailCount == 0) {
						shareVar.checkEmailCount = 1;
						JOptionPane.showMessageDialog(Update.this,"사용하실 수 있는 이메일입니다.");
					}
				}
			});
			btnNewButton_1.setBounds(173, 403, 193, 29);
		}
		return btnNewButton_1;
	}
	
	
	public boolean overlapCheck() {
		
		
		String email = tfEmail.getText();
		UpdateAction ua = new UpdateAction(email);
		
		
		if(ua.overlapCheckAction()==true) {
			
			return true;
		} else {
			return false;
			
			
		}
		
	
	}

	public void showlblBadge() { // ShareVar에서 받은 secondfilename을 통해 lbl에 뱃지 출력
		BadgeBlob badgeBlob = new BadgeBlob();
		com.javalec.share.ShareVar shareVar = new com.javalec.share.ShareVar();
		
		badgeBlob.ShowBadge();
		String filePath = Integer.toString(shareVar.secondFilename);
		
		badge.setIcon(new ImageIcon(filePath));
		badge.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
		
	}





}


