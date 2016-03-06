package dotsandboxesdemo;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import menu.DreamOverPanel;
import menu.Frame;
import menu.WordsPanel;

@SuppressWarnings("serial")
public class DotsMapRiddle extends DotsMap implements Runnable{
	private Image[][]myBox= new Image[5][5];
	private Image[][]ruinedBox= new Image[5][5];
	
	public DotsMapRiddle(Frame frame){
		super(frame, true, 1, 1, 4);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				myBox[i][j] = new ImageIcon("graphics/dream/yincangwenzi/win/User_" + i + j + ".jpg").getImage();
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				ruinedBox[i][j] = new ImageIcon("graphics/dream/yincangwenzi/lose/User_" + i + j + ".jpg").getImage();
			}
		}
	}
	
	protected void paintBoxes(Graphics g) {
		whoseBox=bd.getWhoseBox();
		int i, j;
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				if (whoseBox[i][j] == 1) {
					g.drawImage(myBox[i][j], 461 + j * 107,
							57 + i * 107, null);
				}
				if (whoseBox[i][j] == 2) {
					g.drawImage(ruinedBox[i][j], 461 + j * 107,
							57 + i * 107, null);
				}
			}
		}
	}
	
	protected void checkWinner(){
		if (scoreA+scoreB==25){
			if (scoreA>scoreB){
				WordsPanel wp=new WordsPanel(frame,this,scoreA,scoreB);
				frame.putOn(this,wp);
			}
			else{
				DreamOverPanel dop=new DreamOverPanel(frame, this, 4, scoreA, scoreB, 2);
				frame.putOn(this,dop);
			}
		}
	}
}
