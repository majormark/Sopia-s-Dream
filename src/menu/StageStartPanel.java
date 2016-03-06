package menu;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

import dotsandboxesdemo.DotsMap;
import dotsandboxesdemo.DotsMapRiddle;
import dotsandboxesdemo.DotsMapRummage;
import dotsandboxesdemo.Story;
import UI.Img;
import UI.MyButton;

@SuppressWarnings("serial")
public class StageStartPanel extends MyPanel implements ActionListener,Runnable{
	private Frame frame;
	private MyButton begin = new MyButton(Img.BEGIN1,Img.BEGIN2);
	private Image image;
	private int progress;
	private Story story;
	private float alpha;
	
	public StageStartPanel(Frame frame,Story story,int progress) {
		super(frame);
		this.frame = frame;
		this.story=story;
		this.progress = progress;
		this.add(begin);
		begin.setBounds(471, 364, 109, 28);
		begin.addActionListener(this);
		
		switch(progress){
		case 1:
			this.image=new ImageIcon("graphics/dream/start/putong.png").getImage();
			break;
		case 2:
			this.image=new ImageIcon("graphics/dream/start/riji.png").getImage();
			break;
		case 3:
			this.image=new ImageIcon("graphics/dream/start/putong.png").getImage();
			break;
		case 4:
			this.image=new ImageIcon("graphics/dream/start/wenzi.png").getImage();
			break;
		case 5:
			this.image=new ImageIcon("graphics/dream/start/putong.png").getImage();
			break;
		case 6:
			this.image=new ImageIcon("graphics/dream/start/xianglian.png").getImage();
			break;
		case 7:
			this.image=new ImageIcon("graphics/dream/start/putong.png").getImage();
			break;
		case 8:
			this.image=new ImageIcon("graphics/dream/start/mozhang.png").getImage();
			break;
		default:
			break;
		}
		
		Thread t=new Thread(this);
		t.start();
	}
	
	public void run(){
		int i;
		for (i=0;i<=20;i++){
			alpha=(float)(i*0.05);
			repaint();
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(image,0,0,this);
	}
	public void actionPerformed(ActionEvent e) {
		story.setVisible(false);
		frame.remove(story);
		frame.story=null;
		switch(progress){
		case 2:
			frame.dm=new DotsMapRummage(frame,progress);
			break;
		case 4:
			frame.dm=new DotsMapRiddle(frame);
			break;
		case 6:
			frame.dm=new DotsMapRummage(frame,progress);
			break;
		case 8:
			frame.dm=new DotsMapRummage(frame,progress);
			break;
		default:
			frame.dm=new DotsMap(frame,true,1,1,progress);
			break;
		}
		frame.change(this, frame.dm);
	}
}
