package menu;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dotsandboxesdemo.DotsMap;
import dotsandboxesdemo.Media;
import UI.Img;
import UI.MyButton;

@SuppressWarnings("serial")
public class DoubleGameOverPanel extends MyPanel implements ActionListener,Runnable{
	
	private int player1Score;
	private int player2Score;
	private Frame frame;
	private int difficulty;
	private int firstHand;
	private MyButton again=new MyButton(Img.AGAIN1,Img.AGAIN2);
	private MyButton mainMenu=new MyButton(Img.MainMenu1,Img.MainMenu2);
	private DotsMap dotsMap;
	private float alpha;
	
	public DoubleGameOverPanel(Frame frame,DotsMap dotsMap,int difficulty,int firstHand, int p1s,int p2s) {
		super(frame);
		this.frame = frame;
		this.dotsMap=dotsMap;
		 this.difficulty = difficulty;
		 this.firstHand = firstHand;
		 this.player1Score=p1s;
		 this.player2Score=p2s;
		 this.add(again);
		 again.setBounds(546, 410, 97, 29);
		 again.addActionListener(this);
		 again.setActionCommand("again");
		 this.add(mainMenu);
		 mainMenu.setBounds(420, 410, 70, 31);
		 mainMenu.addActionListener(this);
		 mainMenu.setActionCommand("mainMenu");
		 
		 Media.playWin();
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
	
		public void paintComponent(Graphics g){
			Graphics2D g2=(Graphics2D)g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2.drawImage(Img.DOUBLE_BG,0,0,this);
			g2.drawImage(Img.POINT_BLUE[player2Score], 553, 295, null);
			g2.drawImage(Img.POINT_RED[player1Score], 446, 295, null);
			g2.drawImage(Img.DOUBLE_OVER,397,357,this);
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("again")){
				dotsMap.cease();
				frame.remove(dotsMap);
				dotsMap=null;
				frame.dm=new DotsMap(frame,false,difficulty,firstHand,0);
				frame.change(this, frame.dm);
			}
			else if(e.getActionCommand().equals("mainMenu")){
				frame.remove(dotsMap);
				frame.changeGradually(this, frame.startMenu);
				}
		}
}
