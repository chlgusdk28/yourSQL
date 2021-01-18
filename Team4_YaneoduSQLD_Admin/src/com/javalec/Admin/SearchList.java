package com.javalec.Admin;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.share.ShareVar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchList extends JDialog {
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JScrollPane scrollPane;
	private JTable table;
	private final DefaultTableModel Outer_table = new DefaultTableModel();
	static int num;
	static String sear;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchList dialog = new SearchList(num, sear);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 * @param i 
	 */
	public SearchList(int i, String search) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				if(i==0) {
					SearchActionNum(search);
				}
				if(i==1) {
					searchActionQuiz(search);
				}
			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());

	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 6, 438, 266);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					TableClick();
					dispose();
				}
			});
			table.setModel(Outer_table);
		}
		return table;
	}
	private void tableInit() {
		int i = Outer_table.getRowCount();
		Outer_table.addColumn("퀴즈 번호");
		Outer_table.addColumn("퀴즈");
		Outer_table.setColumnCount(2);
		for(int j = 0;j<i;j++ ) {
			Outer_table.removeRow(0);														// 0번만 지운다.
		}
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);							// 테이블 크기를 마움대로 바꾸지 못함
		// 데이타 들어갈 부분을 정의
		int vIndex = 0;
		TableColumn col= table.getColumnModel().getColumn(vIndex);
		int width = 70;
		col.setPreferredWidth(width);
		vIndex = 1;
		col= table.getColumnModel().getColumn(vIndex);
		width = 300;
		col.setPreferredWidth(width);
	}
	private void SearchActionNum(String search){
		SearchListAction searchListAction = new SearchListAction();
		ArrayList<BeanAdmin> beanList = searchListAction.ConditionQueryNum(search);
		int listCount = beanList.size();
		for(int i = 0; i < listCount ; i++) {
			String[] qTxt = {Integer.toString(beanList.get(i).getQuiznum()), beanList.get(i).getQuizcontents()};
			Outer_table.addRow(qTxt);
		}
	}
	private void searchActionQuiz(String search) {
		SearchListAction searchListAction = new SearchListAction();
		ArrayList<BeanAdmin> beanList = searchListAction.ConditionQueryQuiz(search);
		int listCount = beanList.size();
		for(int i = 0; i < listCount ; i++) {
			String[] qTxt = {Integer.toString(beanList.get(i).getQuiznum()), beanList.get(i).getQuizcontents()};
			Outer_table.addRow(qTxt);
		}
	}
	private void TableClick() {
		int i = table.getSelectedRow();
		String wkSequence = (String)table.getValueAt(i, 0);
		
		ShareVar.quizNumAdm = Integer.parseInt(wkSequence);
		SearchListAction searchListAction = new SearchListAction(ShareVar.quizNumAdm);
		
		BeanAdmin bean = searchListAction.tableClick();
		ShareVar.answAdm = bean.getAnswer();
		ShareVar.chapAdm = bean.getChapter();
		ShareVar.ExplAdm = bean.getExplanation();
		ShareVar.Qt1Adm = bean.getQuestion1();
		ShareVar.Qt2Adm = bean.getQuestion2();
		ShareVar.Qt3Adm = bean.getQuestion3();
		ShareVar.Qt4Adm = bean.getQuestion4();
		ShareVar.QcAdm = bean.getQuizcontents();
		UpdateQuiz updateQuiz = new UpdateQuiz();
		updateQuiz.setLocationRelativeTo(null);
		updateQuiz.setVisible(true);
	}
}
