package UI;

import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class ComplexButton extends MyButton{
	private int delta;
	private int width;
	
	public ComplexButton(Image off,Image on,int delta,int width) {
		super(off,on);
		this.delta=delta;
		this.width=width;
	}
	
	 public void loseMouse(){
	    	this.mouseOn=false;
	    }
	
	public boolean contains(int x, int y){
         //���ж��Ļ���Խ�磬�����֮��Ҳ�ἤ���������
         if(!super.contains(x, y))
                 return false;
         if (x>=delta&&x<=(delta+width)) return true;
         else return false;
         }
	 
	public void paintComponent(Graphics g){
        if(mouseOn)
                g.drawImage(image_on, 0, 0, this);
        else 
                g.drawImage(image_off, delta, 0, this);
        }
}
