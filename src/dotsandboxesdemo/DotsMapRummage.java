package dotsandboxesdemo;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import UI.Img;
import menu.DreamOverPanel;
import menu.Frame;

@SuppressWarnings("serial")
public class DotsMapRummage extends DotsMap implements Runnable{
	private int hiddenRow;
	private int hiddenCol;
	private Image hiddenImage;
	
	
	public DotsMapRummage(Frame frame,int progress){
		super(frame, true, 1,1, progress);
		this.hiddenRow=(int)(Math.random()*5);
		this.hiddenCol=(int)(Math.random()*5);
		if (progress==2){
			this.hiddenImage=new ImageIcon("graphics/dream/diary.png").getImage();
		}
		else if(progress==6){
			this.hiddenImage=new ImageIcon("graphics/dream/xianglian.png").getImage();
		}
		else{
			this.hiddenImage=new ImageIcon("graphics/dream/mozhang.png").getImage();
		}
	}
	
	public void checkWinner(){
		if (scoreA+scoreB==25){
			if (scoreA<scoreB||(!isHiddenGot())){
				DreamOverPanel dop=new DreamOverPanel(frame,this,progress,scoreA,scoreB,2);
				frame.putOn(this,dop);
			}
			else{
				DreamOverPanel dop=new DreamOverPanel(frame,this,progress,scoreA,scoreB,1);
				frame.putOn(this,dop);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, this);
		g.drawImage(Img.SOPHIA, 0,0,this);
		paintBorder(g);
		 g.drawImage(Img.BG_RECT, 402, 0, null);
		paintBoxes(g);
		
		
			g.drawImage(hiddenImage, 461 + hiddenCol * 107,
					57 + hiddenRow * 107, null);
		
		blueFire.setNum(scoreA);
		redFire.setNum(scoreB);
		
		 if (isWhite) {
		 if (horizon) {
		 g.drawImage(Img.LINE_WH_H, 465 + whiteY * 106, 42
					 + 106 * whiteX, null);
		 }
		 else {
		 g.drawImage(Img.LINE_WH_V, 446 + 106 * whiteY, 61
					 + 107 * whiteX, null);
		 }
		 }
		 
		g.drawImage(Img.DOTS, 402, 0, null);
		g.drawImage(Img.FRAME, 402, 0, null);
	}

	private boolean isHiddenGot(){
		if (bd.getWhoseBox()[hiddenRow][hiddenCol]==1) return true;
		else return false;
	}
}
