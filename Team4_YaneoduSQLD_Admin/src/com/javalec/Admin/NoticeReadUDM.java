package com.javalec.Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.Selectfunction.Been;
import com.javalec.share.ShareVar;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Font;

public class NoticeReadUDM extends JDialog {
	ShareVar shareVar = new ShareVar();

	private final JPanel contentPanel = new JPanel();
	private JTextArea tfbContents;
	private JLabel tfTitle;
	private JTextField tfbTitle;
	private JLabel lblNewLabel_1;
	private JButton btnPrevious;

	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NoticeReadUDM dialog = new NoticeReadUDM();
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
	public NoticeReadUDM() {
		setTitle("공지사항 작성");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listInfo1();
			}
		});
		setBounds(100, 100, 460, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 51, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getTfbContents());
		contentPanel.add(getTfTitle());
		contentPanel.add(getTfbTitle());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getBtnPrevious());
		contentPanel.add(getBtnUpdate());
		contentPanel.add(getBtnDelete());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getLblNewLabel_2());
	}
	private JTextArea getTfbContents() {
		if (tfbContents == null) {
			tfbContents = new JTextArea();
			tfbContents.setBounds(18, 176, 424, 184);
		}
		return tfbContents;
	}
	private JLabel getTfTitle() {
		if (tfTitle == null) {
			tfTitle = new JLabel("제목");
			tfTitle.setForeground(Color.WHITE);
			tfTitle.setBounds(18, 98, 61, 16);
		}
		return tfTitle;
	}
	private JTextField getTfbTitle() {
		if (tfbTitle == null) {
			tfbTitle = new JTextField();
			tfbTitle.setBounds(18, 118, 424, 26);
			tfbTitle.setColumns(10);
		}
		return tfbTitle;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("내용");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(18, 156, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JButton getBtnPrevious() {
		if (btnPrevious == null) {
			btnPrevious = new JButton("이전");
			btnPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					NoticeM boardM = new NoticeM();
					boardM.setVisible(true);
					boardM.setLocationRelativeTo(null);
				}
			});
			btnPrevious.setBounds(325, 372, 117, 29);
		}
		return btnPrevious;
	}
	
	public void listInfo1() {
		Been seqno = new Been();
		
		String WhereDefault = "select board_title, board_contents from board "; 
        String WhereDefault2 = "where board_seqno =" + seqno.seqno;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
           
            while(rs.next()){

            	tfbTitle.setText(rs.getString(1));
            	tfbContents.setText(rs.getString(2));
            	
            }
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
	}
	
	public JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				boardUpdate();
				
				
				
				
				}
			});
			btnUpdate.setBounds(18, 372, 71, 29);
		}
		return btnUpdate;
	}
	public JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boardDelete();
 					NoticeM bd = new NoticeM();
 					bd.setLocationRelativeTo(null);
 					bd.setVisible(true);
 					dispose();
//				Been seqno = new Been();
//				BoardReadUDAction uda = new BoardReadUDAction();
//				uda.boardDelete();
				
				}
			});
			btnDelete.setBounds(92, 372, 71, 29);
		}
		return btnDelete;
	}
	

	public void boardUpdate() {
	
		if(weakTitle()==true && weakContents()==true) {
			
			
		String title = tfbTitle.getText();
		String contents = tfbContents.getText();
		NoticeReadUDActionM uda = new NoticeReadUDActionM(title,contents);
		boolean msg = uda.boardUpdate();
		
		if(msg == true) {
			JOptionPane.showMessageDialog(NoticeReadUDM.this, "수정완료");
		}else{
			JOptionPane.showMessageDialog(NoticeReadUDM.this, "수정실패");
			
		}
		
		}
	}
	public boolean boardDelete() {
        PreparedStatement ps = null;
        Been seqno = new Been();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

            String A = "delete from board where board_seqno = ? ";

            ps = conn_mysql.prepareStatement(A);
            
            ps.setInt(1, seqno.seqno);
            ps.executeUpdate();

            conn_mysql.close();
        } catch (Exception e){
           JOptionPane.showMessageDialog(NoticeReadUDM.this, "삭제실패");
        	e.printStackTrace();
            return false;
        }
        JOptionPane.showMessageDialog(NoticeReadUDM.this, "삭제완료"); 				
        return true;
	}
	
private boolean weakTitle() {
		
		String getTitle = tfbTitle.getText().toString();
		String trimTitle = tfbTitle.getText().trim().toString().replaceAll(" ", "");
		
		if(getTitle.length() - trimTitle.length() != 0 || getTitle.length() == 0){
			JOptionPane.showMessageDialog(NoticeReadUDM.this, "제목을 입력하세요.");
			
			return false;
		}else {
			
			return true;
		}
		
	}
	
	private boolean weakContents() {
		
		String getContents = tfbContents.getText().toString();
		String trimContents = tfbContents.getText().trim().toString().replaceAll(" ", "");
		
		if(getContents.length() - trimContents.length() != 0 || getContents.length() == 0){
			JOptionPane.showMessageDialog(NoticeReadUDM.this, "내용을 입력하세요.");
			
			return false;
		}else {
			
			return true;
		}
	}
//	public void boardDelete() {
//		
//		
//		BoardReadUDAction uda1 = new BoardReadUDAction(Been.seqno);
//		
//		boolean msg = uda1.boardDelete();
//		
//		if(msg == true) {
//			JOptionPane.showMessageDialog(null, "삭제완료");
//	
//	
//		}
//	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("공지사항 수정");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(165, 38, 117, 29);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("해당 페이지에서 공지사항을 수정할 수 있습니다.");
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setBounds(128, 68, 198, 16);
		}
		return lblNewLabel_2;
	}
}