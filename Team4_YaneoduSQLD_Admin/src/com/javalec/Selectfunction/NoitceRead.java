package com.javalec.Selectfunction;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.share.ShareVar;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;

public class NoitceRead extends JDialog {
	ShareVar shareVar = new ShareVar();

	private final JPanel contentPanel = new JPanel();
	private JTextArea tfbContents;
	private JLabel tfTitle;
	private JTextField tfbTitle;
	private JLabel lblNewLabel_1;
	private JTextField tfbId;
	private JTextField tfbDate;
	private JButton btnPrevious;

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NoitceRead dialog = new NoitceRead();
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
	public NoitceRead() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listInfo1();
			}
		});
		setBounds(100, 100, 460, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 51, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getTfbContents());
		contentPanel.add(getTfTitle());
		contentPanel.add(getTfbTitle());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getTfbId());
		contentPanel.add(getTextField_1_1());
		contentPanel.add(getBtnPrevious());
	}
	private JTextArea getTfbContents() {
		if (tfbContents == null) {
			tfbContents = new JTextArea();
			tfbContents.setEditable(false);
			tfbContents.setBounds(18, 160, 424, 350);
		}
		return tfbContents;
	}
	private JLabel getTfTitle() {
		if (tfTitle == null) {
			tfTitle = new JLabel("제목");
			tfTitle.setForeground(Color.WHITE);
			tfTitle.setBounds(18, 82, 61, 16);
		}
		return tfTitle;
	}
	private JTextField getTfbTitle() {
		if (tfbTitle == null) {
			tfbTitle = new JTextField();
			tfbTitle.setEditable(false);
			tfbTitle.setBounds(18, 102, 424, 26);
			tfbTitle.setColumns(10);
		}
		return tfbTitle;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("내용");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(18, 140, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfbId() {
		if (tfbId == null) {
			tfbId = new JTextField();
			tfbId.setVisible(false);
			tfbId.setEditable(false);
			tfbId.setColumns(10);
			tfbId.setBounds(18, 37, 180, 26);
		}
		return tfbId;
	}
	private JTextField getTextField_1_1() {
		if (tfbDate == null) {
			tfbDate = new JTextField();
			tfbDate.setVisible(false);
			tfbDate.setEditable(false);
			tfbDate.setColumns(10);
			tfbDate.setBounds(262, 37, 180, 26);
		}
		return tfbDate;
	}
	private JButton getBtnPrevious() {
		if (btnPrevious == null) {
			btnPrevious = new JButton("이전");
			btnPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NoitceRead.this.dispose();
					
				}
			});
			btnPrevious.setBounds(325, 521, 117, 29);
		}
		return btnPrevious;
	}
	
	public void listInfo1() {
		
		Been seqno = new Been();
		System.out.println(seqno.seqno);
		String WhereDefault = "select board_id, board_date, board_title, board_contents from board "; 
        String WhereDefault2 = "where board_seqno =" + seqno.seqno;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
            

           
            while(rs.next()){
            	tfbId.setText(rs.getString(1));
            	tfbDate.setText(rs.getString(2));
            	tfbTitle.setText(rs.getString(3));
            	tfbContents.setText(rs.getString(4));
            	
            	
            }
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
	}
	
}
