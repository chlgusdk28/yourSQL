package com.javalec.Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.share.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class searchPw extends JDialog {
	ShareVar shareVar = new ShareVar();

	private final JPanel contentPanel = new JPanel();
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JTextField tfEMAIL;
	private JTextField tfBDAY;
	private JTextField tfID;
	String id = "!@#$%^11111";
	String email = "!@#$%^11111";
	String bday= "!@#$%^11111";
	String pw= "!@#$%^11111";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			searchPw dialog = new searchPw();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public searchPw() {
		setBounds(100, 100, 450, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("EMAIL");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(70, 154, 61, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("생년월일");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(70, 200, 61, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			tfEMAIL = new JTextField();
			tfEMAIL.setColumns(10);
			tfEMAIL.setBounds(130, 149, 130, 26);
			contentPanel.add(tfEMAIL);
		}
		{
			tfBDAY = new JTextField();
			tfBDAY.setColumns(10);
			tfBDAY.setBounds(130, 195, 130, 26);
			contentPanel.add(tfBDAY);
		}
		{
			JButton btnNewButton = new JButton("ID 찾기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchId sid = new searchId();
					sid.setLocationRelativeTo(null);
					sid.setVisible(true);
				}
			});
			btnNewButton.setBounds(272, 268, 117, 29);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnPw = new JButton("PW 찾기");
			btnPw.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			btnPw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchPWaction();
					pwCheck();
				}
			});
			btnPw.setBounds(272, 124, 117, 73);
			contentPanel.add(btnPw);
		}
		{
			tfID = new JTextField();
			tfID.setColumns(10);
			tfID.setBounds(130, 104, 130, 26);
			contentPanel.add(tfID);
		}
		{
			JLabel lblId = new JLabel("ID");
			lblId.setForeground(Color.WHITE);
			lblId.setBounds(70, 109, 61, 16);
			contentPanel.add(lblId);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("PW찾기");
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
			lblNewLabel_2.setBounds(180, 52, 91, 26);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel label = new JLabel("아이디가 기억나지 않는다면 ? ");
			label.setForeground(Color.WHITE);
			label.setBounds(70, 271, 176, 16);
			contentPanel.add(label);
		}
		{
			JLabel label_1 = new JLabel("회원가입을 진행하고 싶으시다면?");
			label_1.setForeground(Color.WHITE);
			label_1.setBounds(70, 309, 176, 16);
			contentPanel.add(label_1);
		}
		{
			JButton btnPw_1 = new JButton("회원가입");
			btnPw_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					joinUs();
				}
			});
			btnPw_1.setBounds(272, 306, 117, 29);
			contentPanel.add(btnPw_1);
		}
	}
	
public void searchPWaction() {
	
	//비밀번호 확인수정해야함!!!!!
		PreparedStatement ps=null;
		//이메일과 생년월일 
		String WhereDefault = "select userid, useremail, userbirth, userpw from yaneoduSQLD.user";
		String WhereDefault2 = " where userid = '"+tfID.getText()+"'and useremail = '"+tfEMAIL.getText()+"' and userbirth = '"+tfBDAY.getText()+"'";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();
            ResultSet rs = stmt_mysql.executeQuery(WhereDefault+WhereDefault2);
            while(rs.next()){
            	id = rs.getString(1);
            	email = rs.getString(2);
            	bday = rs.getString(3);
            	pw = rs.getString(4);
            }
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
}

public void pwCheck() {
	if (id.equals(tfID.getText()) && email.equals(tfEMAIL.getText()) && bday.equals(tfBDAY.getText())) {
		JOptionPane.showMessageDialog(null, "당신의 비밀번호는 " + pw +" 입니다.");
	}
	
	else {
		JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
	}


};
public void	joinUs(){
	
	joinUs us = new joinUs();
	us.setLocationRelativeTo(null);
	us.setVisible(true);
}


}
