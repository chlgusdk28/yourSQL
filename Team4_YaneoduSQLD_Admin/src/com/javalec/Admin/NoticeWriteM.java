package com.javalec.Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.Select.SelectQuiz;
import com.javalec.share.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;

public class NoticeWriteM extends JDialog {
	ShareVar shareVar = new ShareVar();
	private JTextField tfwId;
	private JLabel lblNewLabel_1;
	private JTextArea tfwContents;
	private JButton btnPrevious;
	private JButton btnInsert;

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JTextField tfwName;
	private JLabel lblNewLabel_2_1;
	private JTextField tfwTitle;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	
	
	
	
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NoticeWriteM dialog = new NoticeWriteM();
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
	public NoticeWriteM() {
		getContentPane().setBackground(new Color(51, 51, 51));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listInfo2();
			}
		});
		setBounds(100, 100, 640, 484);
		getContentPane().setLayout(null);
		getContentPane().add(getTfwId());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getTextArea_1());
		getContentPane().add(getBtnPrevious());
		getContentPane().add(getBtnInsert());
		
		tfwName = new JTextField();
		tfwName.setVisible(false);
		tfwName.setEditable(false);
		tfwName.setColumns(10);
		tfwName.setBounds(556, 46, 54, 26);
		getContentPane().add(tfwName);
		getContentPane().add(getLblNewLabel_2_1());
		getContentPane().add(getTfwTitle());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblNewLabel_2());
	}

	private void wrongnote() {
		
	}
	private JTextField getTfwId() {
		if (tfwId == null) {
			tfwId = new JTextField();
			tfwId.setVisible(false);
			tfwId.setEditable(false);
			tfwId.setBounds(556, 14, 54, 26);
			tfwId.setColumns(10);
		}
		return tfwId;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("내용");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(29, 164, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JTextArea getTextArea_1() {
		if (tfwContents == null) {
			tfwContents = new JTextArea();
			tfwContents.setBounds(29, 188, 575, 197);
		}
		return tfwContents;
	}
	private JButton getBtnPrevious() {
		if (btnPrevious == null) {
			btnPrevious = new JButton("이전");
			btnPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NoticeWriteM.this.dispose();
					NoticeM bd = new NoticeM();
					bd.setLocationRelativeTo(null);
					bd.setVisible(true);
				}
			});
			btnPrevious.setBounds(510, 397, 100, 29);
		}
		return btnPrevious;
	}
	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("등록");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertInfo();

				}
			});
			btnInsert.setBounds(393, 397, 109, 29);
		}
		return btnInsert;
	}
	
	
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("제목");
			lblNewLabel_2_1.setForeground(Color.WHITE);
			lblNewLabel_2_1.setBounds(29, 106, 61, 16);
		}
		return lblNewLabel_2_1;
	}
	private JTextField getTfwTitle() {
		if (tfwTitle == null) {
			tfwTitle = new JTextField();
			tfwTitle.setColumns(10);
			tfwTitle.setBounds(29, 126, 581, 26);
		}
		return tfwTitle;
	}
	
	public void listInfo2() {
		String WhereDefault = "select userid, username from user where userid = '" + shareVar.userId + "'";
       
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
            

           
            while(rs.next()){
            	tfwId.setText(rs.getString(1));
            	tfwName.setText(rs.getString(2));
            	
            	
            	
            }
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
	}
	
        
	private void insertInfo(){
//    		if(weakTitle()== true && weakContents()==true) {
    			
    			NoticeWriteAction bwa = new NoticeWriteAction(tfwId.getText(),tfwName.getText(),tfwTitle.getText(),tfwContents.getText());
				boolean msg = bwa.inputOK();
				NoticeWriteM.this.dispose();
				NoticeM bd = new NoticeM();
				bd.setVisible(true);
				bd.setLocationRelativeTo(null);
				bd.TableInit();
				bd.SearchAction();
    			
    			
    			if(msg == true) {
    				JOptionPane.showMessageDialog(NoticeWriteM.this, "등록완료!");
    			}
    			
    		}
//	else {
    			
//    		}
//    	}
        
	
	
//	private boolean weakTitle() {
//		
//		String getTitle = tfwTitle.getText().toString();
//		String trimTitle = tfwTitle.getText().trim().toString().replaceAll(" ", "");
//		
//		if(getTitle.trim().length() - trimTitle.trim().length() != 0 || getTitle.trim().length() == 0){
//			JOptionPane.showMessageDialog(null, "제목을 입력하세요.");
//			
//			return false;
//		}else {
//			
//			return true;
//		}
		
//	}
	
//	private boolean weakContents() {
//		
//		String getContents = tfwContents.getText().toString();
//		String trimContents = tfwContents.getText().trim().toString().replaceAll(" ", "");
//		
//		if(getContents.trim().length() - trimContents.trim().length() != 0 || getContents.trim().length() == 0){
//			JOptionPane.showMessageDialog(null, "내용을 입력하세요.");
//			
//			return false;
//		}else {
//			
//			return true;
//		}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("공지사항 작성");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(255, 38, 109, 36);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("관리자님 공지사항을 작성해주세요.");
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setBounds(230, 73, 188, 16);
		}
		return lblNewLabel_2;
	}
	}
	

