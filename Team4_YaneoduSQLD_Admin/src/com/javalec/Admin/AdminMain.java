package com.javalec.Admin;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminMain extends JDialog {
	private JButton btnInput;
	private JButton btnUpdate;
	private JButton button;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain dialog = new AdminMain();
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
	public AdminMain() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("관리창");
		setBounds(100, 100, 450, 261);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnInput());
		getContentPane().add(getBtnUpdate());
		getContentPane().add(getButton());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblNewLabel_1());

	}
	private JButton getBtnInput() {
		if (btnInput == null) {
			btnInput = new JButton("문제 입력");
			btnInput.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inputQuiz inputQuiz = new inputQuiz();
					inputQuiz.setLocationRelativeTo(null);
					inputQuiz.setVisible(true);
					dispose();
				}
			});
			btnInput.setBounds(25, 121, 117, 73);
		}
		return btnInput;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("문제 수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UpdateQuiz updateQuiz = new UpdateQuiz();
					updateQuiz.setLocationRelativeTo(null);
					updateQuiz.setVisible(true);
					dispose();
				}
			});
			btnUpdate.setBounds(166, 121, 117, 73);
		}
		return btnUpdate;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("공지사항 관리");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NoticeM boardM = new NoticeM();
					boardM.setVisible(true);
					boardM.setLocationRelativeTo(null);
					dispose();
				}
			});
			button.setBounds(307, 121, 117, 73);
		}
		return button;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("관리자님 안녕하세요. 반갑습니다.");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(254, 16, 178, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("관리자 페이지");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(177, 69, 95, 34);
		}
		return lblNewLabel_1;
	}
}
