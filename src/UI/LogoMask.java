package UI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dotsandboxesdemo.DotsMap;

@SuppressWarnings("serial")
public class LogoMask extends JLabel implements Runnable{
	float beta=0;
	Image[]images=new Image[20];
	int index=0;
	DotsMap dm;
	
	public LogoMask(DotsMap dm){
		this.dm=dm;
		this.setBounds(0,0,1052,650);
		int i;
		for (i=0;i<20;i++){
			images[i]=new ImageIcon("graphics/logo/logo"+i+".png").getImage();
		}
	}
	
	public void run(){
		int i;
		for (i=0;i<=19;i++){
			repaint();
			if (i<=5){
				beta=(float)(i*0.2);
			}
			else if(i>=15){
				beta=(float)((19-i)*0.2);
			}
			index=i;
			try {
				Thread.sleep(134);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		dm.setFocused();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		if (index<=4){
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 1052, 650);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, beta));
		g2.drawImage(images[index], 0,0,this);
	}
}
