package UI;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LineShape extends JPanel implements Runnable{
	private Image staticImage;
	private Image grayImage;
	private Image curImage;
	private LineShape lastLine;
	private float alpha;
	
	public LineShape(Image sta,Image gray,LineShape lastLine){
		this.curImage=sta;
		this.staticImage=sta;
		this.grayImage=gray;
		this.lastLine=lastLine;
		this.setOpaque(false);
	}
	
	public void run(){
		int i;
		for (i=0;i<10;i++){
			alpha=(float)0.1*i;
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			repaint();
		}
		
		if (lastLine!=null) {
			lastLine.changeGray();
		}
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(alpha)));
		g2.drawImage(curImage, 0,0,this);
		if (this.curImage==this.grayImage){
			Graphics2D g22=(Graphics2D)g;
			g22.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(1-alpha)));
			g22.drawImage(staticImage, 0,0,this);
		}
	}
	
	public void changeGray(){
		this.curImage=this.grayImage;
		int i;
		for (i=0;i<10;i++){
			alpha=(float)0.1*i;
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			repaint();
		}
	}
}
