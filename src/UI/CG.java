package UI;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import dotsandboxesdemo.Media;
import menu.Frame;
import menu.MyPanel;
import menu.StartMenu;

@SuppressWarnings("serial")
public class CG extends MyPanel implements Runnable{
		Image[]images=new Image[39];
		int index=-1;
		Image logo=new ImageIcon("graphics/logo/logo.jpg").getImage();
		float alpha=0;
		StartMenu startMenu;
		Frame frame;
	
		public CG(StartMenu sm,Frame frame){
			super(frame);
			this.startMenu=sm;
			this.frame=frame;
			int i;
			for (i=0;i<39;i++){
				images[i] = new ImageIcon("graphics/logo/ph00" + (i+1) + ".png").getImage();
			}
			Thread t=new Thread(this);
			t.start();
		}
		public void run(){
			repaint();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			for (index=0;index<=38;index++){
				repaint();
				try {
					Thread.sleep(66);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			Media.changeBGMto("mainMenu");
			int i;
			
			for (i=0;i<=40;i++){
				if (i<=10){
					alpha=(float)(i*0.1);
				}
				if (i>=30){
					alpha=(float)((40-i)*0.1);
				}
				repaint();
				try {
					Thread.sleep(66);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if (i==38) {
					frame.startMenu.emergeAfterCG();
					frame.add(frame.startMenu);
					this.setVisible(false);
					frame.remove(this);
					
					frame.startMenu.setVisible(true);
				}
			}
			
		}
		
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1052, 650);
			if (index<39&&index>=0){
				g.drawImage(images[index],251,177,this);
			}
			else{
				Graphics2D g2=(Graphics2D)g;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(alpha)));
				g2.drawImage(logo,0,0,this);
			}
		}
	}