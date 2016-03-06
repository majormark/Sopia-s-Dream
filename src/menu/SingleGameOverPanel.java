package menu;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

import dotsandboxesdemo.DotsMap;
import dotsandboxesdemo.Media;
import UI.Img;
import UI.MyButton;

@SuppressWarnings("serial")
public class SingleGameOverPanel extends MyPanel implements ActionListener,Runnable{
	private int playerScore;
	private int computerScore;
	private Frame frame;
	 private int difficulty;
	 private int firstHand;
	 private int winner;
	private MyButton again=new MyButton(Img.AGAIN1,Img.AGAIN2);
	private MyButton mainMenu=new MyButton(Img.MainMenu1,Img.MainMenu2);
	private DotsMap dotsMap;
	private float alpha;
	
	public SingleGameOverPanel (Frame frame,DotsMap dotsMap,int difficulty,int firstHand,int winner,int ps,int cs) {
		super(frame);
		this.dotsMap=dotsMap;
	 this.frame = frame;
	 this.difficulty = difficulty;
	 this.firstHand = firstHand;
	 this.winner = winner;
	 this.playerScore=ps;
	 this.computerScore=cs;
	 
	 this.add(again);
	 again.setBounds(546, 410, 97, 29);
	 again.addActionListener(this);
	 again.setActionCommand("again");
	 this.add(mainMenu);
	 mainMenu.setBounds(420, 410, 70, 31);
	 mainMenu.addActionListener(this);
	 mainMenu.setActionCommand("mainMenu");
	 
	 if (winner==1) Media.playWin();
	 else Media.playLose();
	 
	 Thread t=new Thread(this);
	 t.start();
	}
	
	public void run(){
		int i;
		for (i=0;i<=20;i++){
			alpha=(float)(i*0.05);
			repaint();
			try {
				Thread.sleep(67);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	//score[1]是玩家， score[2]是电脑
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2.drawImage(Img.SINGLE_BG,0,0,this);
			g2.drawImage(Img.POINT_BLUE[playerScore], 553, 295, this);
			g2.drawImage(Img.POINT_RED[computerScore], 446, 295, this);
		
		if(winner == 1) {
			g2.drawImage(Img.SINGLE_WIN,476,349,this);
		}
		if(winner == 2) {
			g2.drawImage(Img.SINGLE_LOSE,457,349,this);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("again")){
			dotsMap.cease();
			frame.remove(dotsMap);
			dotsMap=null;
			frame.dm=new DotsMap(frame,true,difficulty,firstHand,0);
			frame.change(this, frame.dm);
		}
		else if(e.getActionCommand().equals("mainMenu")){
			frame.remove(dotsMap);
			frame.changeGradually(this, frame.startMenu);
		}
	}
}
