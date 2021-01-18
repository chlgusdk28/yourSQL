package com.javalec.LevelInfo;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.javalec.Result.ResultMain;
import com.javalec.ResultFunction.BadgeBlob;
import com.javalec.Section.ShowSection;
import com.javalec.share.ShareVar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelInfoMain extends JDialog {
	private JLabel lblBadgeT;
	private JButton btnOk;
	private JButton btnLevelSectio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelInfoMain dialog = new LevelInfoMain();
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
	public LevelInfoMain() {
		setTitle("레벨표");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				LevelInfoMain.this.setLocationRelativeTo(null);
				showAllBadge();
			}
		});
		setBounds(100, 100, 336, 435);
		getContentPane().setLayout(null);
		getContentPane().add(getLblBadgeT());
		getContentPane().add(getBtnOk());
		getContentPane().add(getBtnLevelSectio());

	}

	private JLabel getLblBadgeT() {
		if (lblBadgeT == null) {
			lblBadgeT = new JLabel("");
			lblBadgeT.setHorizontalAlignment(SwingConstants.CENTER);
			lblBadgeT.setBounds(0, 0, 336, 342);
		}
		return lblBadgeT;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("나가기");
			btnOk.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			btnOk.setBounds(192, 354, 117, 29);
		}
		return btnOk;
	}
	public void showAllBadge() { // ShareVar에서 받은 secondfilename을 통해 lbl에 뱃지 출력
		LevelInfoAction action = new LevelInfoAction();
		ShareVar shareVar = new com.javalec.share.ShareVar();
		System.out.println("share filename : " + shareVar.secondFilename);
		
		action.ShowBadge();
		String filePath = Integer.toString(shareVar.secondFilename);
		
		lblBadgeT.setIcon(new ImageIcon(filePath));
		lblBadgeT.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
		
	}
	private JButton getBtnLevelSectio() {
		if (btnLevelSectio == null) {
			btnLevelSectio = new JButton("레벨별 인원");
			btnLevelSectio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ShowSection section = new ShowSection();
					section.setVisible(true);
					LevelInfoMain.this.dispose();
				}
			});
			btnLevelSectio.setBounds(25, 354, 117, 29);
		}
		return btnLevelSectio;
	}
}
