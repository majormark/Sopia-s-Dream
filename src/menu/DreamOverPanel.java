package menu;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import dotsandboxesdemo.DotsMap;
import dotsandboxesdemo.Media;
import dotsandboxesdemo.Property;
import dotsandboxesdemo.Story;
import UI.Img;
import UI.MyButton;

//梦境模式一关结束后出现的面板
@SuppressWarnings("serial")
public class DreamOverPanel extends MyPanel implements ActionListener,Runnable{
	 private MyButton mainMenu = new MyButton(Img.MainMenu1,Img.MainMenu2);
	 private MyButton anotherGame;
	 private int progress;
	 private int scoreA;
	 private int scoreB;
	 private Image bg;
	 private DotsMap dotsMap;
	 private float alpha;
	 
	 public DreamOverPanel(Frame frame,DotsMap dm,int progress,int scoreA,int scoreB,int winner) {
		super(frame);
		System.out.println(Property.progress);
		this.progress=progress;
		this.scoreA=scoreA;
		this.scoreB=scoreB;
		this.dotsMap=dm;
		
		if (winner==2){
			Media.playLose();
			anotherGame= new MyButton(Img.AGAIN1,Img.AGAIN2);
			bg=new ImageIcon("graphics/dream/result/"+progress+"L.png").getImage();
			anotherGame.setBounds(546, 410,97, 29);
			anotherGame.setActionCommand("again");
		}
		else{
			Media.playWin();
			anotherGame= new MyButton(new ImageIcon("graphics/dream/xiayizhang1.png").getImage(),new ImageIcon("graphics/dream/xiayizhang2.png").getImage());
			bg=new ImageIcon("graphics/dream/result/"+progress+"W.png").getImage();
			anotherGame.setBounds(559, 410, 78, 30);
			anotherGame.setActionCommand("next");
			if (Property.progress<=progress&&progress!=8){
				Property.progress=progress+1;
				Property.save();
			}
		}
   	 	this.add(anotherGame);
   	 	anotherGame.addActionListener(this);
   	 	this.add(mainMenu);
   	 	mainMenu.setBounds(420, 410, 70, 31);
   	 	mainMenu.setActionCommand("mainMenu");
   	 	mainMenu.addActionListener(this);
   	 	
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
			g2.drawImage(bg,0,0,this);
			g2.drawImage(Img.POINT_BLUE[scoreA], 553, 295, null);
			g2.drawImage(Img.POINT_RED[scoreB], 446, 295, null);
		}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="mainMenu"){
			dotsMap.cease();
			frame.remove(dotsMap);
			frame.changeGradually(this, frame.startMenu);
		}
		else if(e.getActionCommand()=="next"){
			dotsMap.setVisible(false);
			frame.remove(dotsMap);
			dotsMap=null;
			frame.story=new Story(frame,progress+1);
			frame.changeGradually(this,frame.story);
		}
		if(e.getActionCommand()=="again") {
			dotsMap.setVisible(false);
			frame.remove(dotsMap);
			dotsMap=null;
			frame.story=new Story(frame,progress);
			frame.changeGradually(this, frame.story);
			frame.story.skip();
		}
	}
}