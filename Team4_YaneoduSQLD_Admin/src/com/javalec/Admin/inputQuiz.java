package com.javalec.Admin;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.TextArea;

public class inputQuiz extends JDialog {
	private JButton btnInput;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_1_1;
	private JLabel label_1_1_1;
	private JLabel label_1_1_1_1;
	private JLabel label_1_1_1_1_1;
	private JLabel lblNewLabel;
	private JLabel label_2;
	private JTextField tfQuestion1;
	private JTextField tfQuestion2;
	private JTextField tfQuestion3;
	private JTextField tfQuestion4;
	private JTextField tfAnswer;
	private JComboBox cbChapter;
	private JLabel lblNewLabel_1;
	private TextArea taQuiz;
	private TextArea taContents;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputQuiz dialog = new inputQuiz();
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
	public inputQuiz() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 597, 612);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnInput());
		getContentPane().add(getLabel());
		getContentPane().add(getLabel_1());
		getContentPane().add(getLabel_1_1());
		getContentPane().add(getLabel_1_1_1());
		getContentPane().add(getLabel_1_1_1_1());
		getContentPane().add(getLabel_1_1_1_1_1());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLabel_2());
		getContentPane().add(getTfQuestion1());
		getContentPane().add(getTfQuestion2());
		getContentPane().add(getTfQuestion3());
		getContentPane().add(getTfQuestion4());
		getContentPane().add(getTfAnswer());
		getContentPane().add(getCbChapter());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getTaQuiz());
		getContentPane().add(getTaContents());

	}

	private JButton getBtnInput() {
		if (btnInput == null) {
			btnInput = new JButton("입력완료");
			btnInput.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inputQuizAction inputQuizAction = new inputQuizAction();
					boolean rs = inputQuizAction.inputContents(taQuiz.getText(), ("1번 "+tfQuestion1.getText()), ("2번 "+tfQuestion2.getText()), ("3번 "+tfQuestion3.getText()), ("4번 "+tfQuestion4.getText()), tfAnswer.getText() , taContents.getText(), cbChapter.getSelectedItem().toString());
					if(rs == true){
				          JOptionPane.showMessageDialog(inputQuiz.this, "문제 입력 완료");                    
					}else{
				          JOptionPane.showMessageDialog(inputQuiz.this, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!");                    
					}
					taQuiz.setText("");
					taContents.setText("");
					tfAnswer.setText("");
					tfQuestion1.setText("");
					tfQuestion2.setText("");
					tfQuestion3.setText("");
					tfQuestion4.setText("");
				}
			});
			btnInput.setBounds(435, 531, 117, 29);
		}
		return btnInput;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("문제");
			label.setForeground(Color.WHITE);
			label.setBounds(66, 49, 61, 16);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("문항 1 : ");
			label_1.setForeground(Color.WHITE);
			label_1.setBounds(66, 202, 61, 16);
		}
		return label_1;
	}
	private JLabel getLabel_1_1() {
		if (label_1_1 == null) {
			label_1_1 = new JLabel("문항 2 : ");
			label_1_1.setForeground(Color.WHITE);
			label_1_1.setBounds(66, 235, 61, 16);
		}
		return label_1_1;
	}
	private JLabel getLabel_1_1_1() {
		if (label_1_1_1 == null) {
			label_1_1_1 = new JLabel("문항 3 : ");
			label_1_1_1.setForeground(Color.WHITE);
			label_1_1_1.setBounds(66, 268, 61, 16);
		}
		return label_1_1_1;
	}
	private JLabel getLabel_1_1_1_1() {
		if (label_1_1_1_1 == null) {
			label_1_1_1_1 = new JLabel("문항 4 : ");
			label_1_1_1_1.setForeground(Color.WHITE);
			label_1_1_1_1.setBounds(66, 301, 61, 16);
		}
		return label_1_1_1_1;
	}
	private JLabel getLabel_1_1_1_1_1() {
		if (label_1_1_1_1_1 == null) {
			label_1_1_1_1_1 = new JLabel("정답 : ");
			label_1_1_1_1_1.setForeground(Color.WHITE);
			label_1_1_1_1_1.setBounds(68, 339, 61, 16);
		}
		return label_1_1_1_1_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("해설 ");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(66, 377, 61, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("챕터 : ");
			label_2.setForeground(Color.WHITE);
			label_2.setBounds(343, 49, 61, 16);
		}
		return label_2;
	}
	private JTextField getTfQuestion1() {
		if (tfQuestion1 == null) {
			tfQuestion1 = new JTextField();
			tfQuestion1.setBounds(111, 197, 436, 26);
			tfQuestion1.setColumns(10);
		}
		return tfQuestion1;
	}
	private JTextField getTfQuestion2() {
		if (tfQuestion2 == null) {
			tfQuestion2 = new JTextField();
			tfQuestion2.setColumns(10);
			tfQuestion2.setBounds(111, 230, 436, 26);
		}
		return tfQuestion2;
	}
	private JTextField getTfQuestion3() {
		if (tfQuestion3 == null) {
			tfQuestion3 = new JTextField();
			tfQuestion3.setColumns(10);
			tfQuestion3.setBounds(111, 263, 436, 26);
		}
		return tfQuestion3;
	}
	private JTextField getTfQuestion4() {
		if (tfQuestion4 == null) {
			tfQuestion4 = new JTextField();
			tfQuestion4.setColumns(10);
			tfQuestion4.setBounds(111, 296, 436, 26);
		}
		return tfQuestion4;
	}
	private JTextField getTfAnswer() {
		if (tfAnswer == null) {
			tfAnswer = new JTextField();
			tfAnswer.setColumns(10);
			tfAnswer.setBounds(113, 334, 72, 26);
		}
		return tfAnswer;
	}
	private JComboBox getCbChapter() {
		if (cbChapter == null) {
			cbChapter = new JComboBox();
			cbChapter.setModel(new DefaultComboBoxModel(new String[] {"데이터 모델링", "sql 기본", "sql 활용"}));
			cbChapter.setBounds(378, 45, 166, 27);
		}
		return cbChapter;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("정답은 해당 답의 번호만 입력해주세요 (1~4)");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(197, 339, 244, 16);
		}
		return lblNewLabel_1;
	}
	private TextArea getTaQuiz() {
		if (taQuiz == null) {
			taQuiz = new TextArea("",1,1,taQuiz.SCROLLBARS_VERTICAL_ONLY);
			taQuiz.setBounds(66, 79, 486, 100);
		}
		return taQuiz;
	}
	private TextArea getTaContents() {
		if (taContents == null) {
			taContents = new TextArea("",1,1,taQuiz.SCROLLBARS_VERTICAL_ONLY);
			taContents.setBounds(66, 406, 486, 100);
		}
		return taContents;
	}
}
