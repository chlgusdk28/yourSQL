package com.javalec.Quiz;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.javalec.Result.ResultMain;
import com.javalec.share.ShareVar;

public class TimeAction extends Thread{
	ShareVar shareVar = new ShareVar();
	
	int y = 0;
    JLabel myLabel = null;
    public TimeAction(JLabel myLabel)
    {
        this.myLabel = myLabel;
 
    }
    
    public void run() {
    	ShareVar shareVar = new ShareVar(); 
        for(int i = 1 ; i<=shareVar.cbData*60 ; i++)
        {
            myLabel.setText(""+String.format("%2d", (y)/60)+" : "+ String.format("%2d", (y)%60));
            try {
                Thread.sleep(1000);
                
                
                if(y>=ShareVar.cbData*60) {
                	switch (y%2) {
					case 0:
						myLabel.setForeground(Color.RED);
						break;
					case 1:
						myLabel.setForeground(Color.BLACK);
					default:
						break;
					}        		
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            y++;
        }
        shareVar.totalTime +=y;


    }
	
}
