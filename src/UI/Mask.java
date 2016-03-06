package UI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Mask extends JLabel implements Runnable{
	float alpha=0;
	int state=0;
	
	public Mask(){
		this.setBounds(0,0,1052,650);
	}
	
	public void run(){
		int i;
		//1代表要消失的时候
		if (state==1){
			for (i=1;i<=10;i++){
				alpha=(float)(i*0.1);
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				repaint();
			}
		}
		//0代表要出现的时候
		else{
			for (i=19;i>=0;i--){
				alpha=(float)(i*0.05);
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				repaint();
			}
		}
	}
	
	public void setState(int state){
		this.state=state;
		this.alpha=state;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.fillRect(0, 0, 1052, 650);
	}
}
