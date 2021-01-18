package com.javalec.chat;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.naming.RefAddr;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.Result.ResultMain;
import com.javalec.share.ShareVar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class ChatMain extends JDialog {
	public JTextField tfChat;
	private JButton btnInsert;
	private JButton btnOK;
	private JScrollPane scrollPane;
	private JTable Inner_table;
	
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	
	public int count = 0;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatMain dialog = new ChatMain();
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
	 */
	public ChatMain() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("정보 공유");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ChatMain.this.setLocationRelativeTo(null);
				TableInit();
				SearchAction();
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						if(count < 1) {
							scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
							count++;
						}else {
							timer.cancel();
						}
					}
					
				};
				timer.schedule(task, 100, 1000);
			}
		});
		setBounds(100, 100, 670, 509);
		getContentPane().setLayout(null);
		getContentPane().add(getTfChat());
		getContentPane().add(getBtnInsert());
		getContentPane().add(getBtnOK());
		getContentPane().add(getScrollPane());
		
		JButton btnNewButton = new JButton("새로고침");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableInit();
				SearchAction();
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				
			}
		});
		btnNewButton.setBounds(20, 398, 105, 31);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("나가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(544, 436, 98, 31);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("※한번 더 엔터를 치거나 새로고침을 누르면 화면이 갱신됩니다.");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setBounds(31, 331, 395, 22);
		getContentPane().add(lblNewLabel);
		getContentPane().add(getLblNewLabel_1());

	}

	public JTextField getTfChat() {
		if (tfChat == null) {
			tfChat = new JTextField();
			tfChat.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						
						btnOK.doClick();
						TableInit();
						SearchAction();
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						
			          }
					
				}
			});
			tfChat.setBounds(65, 365, 467, 26);
			tfChat.setColumns(10);
		}
		return tfChat;
	}
	public JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("입력");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfChat.getText().length() != 0) {
						ChatAction action = new ChatAction();
						action.ReturnNewSeqno();
						
						ShareVar shareVarTest = new ShareVar();
						shareVarTest.text = tfChat.getText().toString();
						
						action.InsertInfo();
						tfChat.setText(null);
						System.out.println("Ok!!!");
						
						TableInit();
						SearchAction();
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

					}else {
						
						TableInit();
						SearchAction();
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
					}
				}
			});
			btnInsert.setBounds(544, 365, 98, 29);
		}
		return btnInsert;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfChat.getText().length() != 0) {
						ChatAction action = new ChatAction();
						action.ReturnNewSeqno();
						
						ShareVar shareVarTest = new ShareVar();
						shareVarTest.text = tfChat.getText().toString();
						
						action.InsertInfo();
						tfChat.setText(null);
						TableInit();
						SearchAction();
						
						System.out.println("Ok!!!");
						
						TableInit();
						SearchAction();
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

					}else {
						
						
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
					}
				}
			});
			
			btnOK.setBounds(307, 367, 117, 29);
		}
		return getBtnInsert();
		
	
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(31, 19, 608, 300);
			scrollPane.setViewportView(getInner_table());
		}
		return scrollPane;
	}
	private JTable getInner_table() {
		if (Inner_table == null) {
			Inner_table = new JTable();
			Inner_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_table.setEnabled(false);
			Inner_table.setModel(Outer_Table); // <--***************************************************
		}
		return Inner_table;
	}
	public void TableInit(){
        int i = Outer_Table.getRowCount();
        
        Outer_Table.addColumn("");
        Outer_Table.addColumn("");
        Outer_Table.setColumnCount(2);

        for(int j = 0 ; j < i ; j++){
            Outer_Table.removeRow(0);
        }

        Inner_table.setAutoResizeMode(Inner_table.AUTO_RESIZE_OFF);
        

        int vColIndex = 0;
        TableColumn col = Inner_table.getColumnModel().getColumn(vColIndex);
        int width = tfChat.toString().length(); // 입력한 값의 length까지 출력하기
        col.setPreferredWidth(width);
        
        vColIndex = 1;
        col = Inner_table.getColumnModel().getColumn(vColIndex);
        width = 150;
        col.setPreferredWidth(width);

	}
	public void SearchAction(){
		ChatAction chataction = new ChatAction();
		ArrayList<ChatBean>arrayList = chataction.SelectList();
		
		int listCount = arrayList.size();
		
		for (int index = 0; index < listCount; index++) {
			String[] qTxt = {arrayList.get(index).getChattext(), arrayList.get(index).getChatdatetime()};
			Outer_Table.addRow(qTxt);
		}

	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Chat");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(31, 370, 39, 16);
		}
		return lblNewLabel_1;
	}
}
