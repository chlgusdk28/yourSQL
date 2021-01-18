package com.javalec.Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.Select.SelectQuiz;
import com.javalec.Selectfunction.Been;
import com.javalec.share.ShareVar;
import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NoticeM extends JDialog {
	ShareVar shareVar = new ShareVar();
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	private JScrollPane scrollPane;
	private JTable Inner_Table;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	
	int clickSwitch = 0;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NoticeM dialog = new NoticeM();
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
	public NoticeM() {
		getContentPane().setBackground(new Color(51, 51, 51));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TableInit();
				SearchAction();
			}
		});
		setBounds(100, 100, 700, 360);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getBtnNewButton_1_1());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setEnabled(false);
			scrollPane.setBounds(50, 86, 600, 169);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}
	private JTable getInner_Table() {
		if (Inner_Table == null) {
			Inner_Table = new JTable();
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1){
						TableClick();
						 int i = Inner_Table.getSelectedRow();
					     String tkSequence = (String)Inner_Table.getValueAt(i, 0);
					     System.out.println("tkSeqence : " + tkSequence);
					     Been been = new Been(Integer.parseInt(tkSequence));
					     dispose();
					}
				}
			});
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.setModel(Outer_Table); // <--***************************************************
		}
		
		return Inner_Table;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("작성하기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NoticeM.this.dispose();
					NoticeWriteM bw = new NoticeWriteM();
					bw.setLocationRelativeTo(null);
					bw.setVisible(true);
					dispose();
					
				}
			});
			btnNewButton.setBounds(400, 267, 172, 29);
		}
		return btnNewButton;
	}
		public void TableInit(){
			int i = Outer_Table.getRowCount();
			Outer_Table.addColumn("번호");
	        Outer_Table.addColumn("제목");
	        Outer_Table.addColumn("작성일자");
	        Outer_Table.setColumnCount(3);
	        for(int j = 0 ; j < i ; j++){
	            Outer_Table.removeRow(0);
	        }
	        Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);
	        int vColIndex = 0;
	        TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        int width = 40;
	        col.setPreferredWidth(width);
	        
	      
	        vColIndex = 1;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 160;
	        col.setPreferredWidth(width);
	        
	        vColIndex = 2;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 240;
	        col.setPreferredWidth(width);
	        
		}
	
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("공지사항");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblNewLabel.setBounds(315, 34, 76, 29);
		}
		return lblNewLabel;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("이전");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				NoticeM.this.dispose();
				AdminMain adminMain = new AdminMain();
				adminMain.setVisible(true);
				adminMain.setLocationRelativeTo(null);
				}
			});
			btnNewButton_1.setBounds(572, 267, 86, 29);
		}
		return btnNewButton_1;
	}
	public void SearchAction(){
		
		
		String WhereDefault = "select board_seqno, board_title, board_date from board order by board_date desc";
//		String WhereDefault2 = " where board_id";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);
            while(rs.next()){
                String[] qTxt = {rs.getString(1), rs.getString(2), rs.getString(3)};
               
               
                
                Outer_Table.addRow(qTxt);
            }
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
	
	}

	private void TableClick() {
			NoticeReadUDM br = new NoticeReadUDM();
			br.setLocationRelativeTo(null);
			br.setVisible(true);
	}
}