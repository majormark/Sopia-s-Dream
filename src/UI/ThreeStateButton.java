package UI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import dotsandboxesdemo.Media;
import dotsandboxesdemo.Property;

@SuppressWarnings("serial")
public class ThreeStateButton extends JButton{
	protected Image image_unlocked;               //鼠标在按钮上的图片
	protected Image image_locked;                //鼠标不在按钮上的图片
	public boolean isLocked;
	protected boolean mouseOn;
	protected int stage;  
	protected Image chosen=new ImageIcon("graphics/dream/xuanguan/xuanzhong.png").getImage();
	public ThreeStateButton(Image locked,Image unlocked, int stage){
		if(stage <= Property.progress) {
	     	   isLocked = false;
	        }
		else{
			isLocked = true;
		}
        //加载图片
        this.image_locked=locked;
        this.image_unlocked=unlocked;
       this.stage = stage;
        //必须设置！否则会有残影！
        this.setOpaque(false);
        this.addMouseListener(new MouseHandler());
        
}
	//覆盖此方法绘制自定义的边框
    public void paintBorder(Graphics g){
            //不要边框
    }
    public void paintComponent(Graphics g){
    
    	if(isLocked){
            g.drawImage(image_locked, 0, 0, this);
    	}
        else{
        	g.drawImage(image_unlocked, 0, 0, this);
        	if (mouseOn){
        		g.drawImage(chosen, 0, 0, this);
        	}
        }
    }
    
    
  //处理进入、离开图片范围的消息
    class MouseHandler extends MouseAdapter  {
            public void mouseExited(MouseEvent e){
                    mouseOn = false;
                    repaint();
            }
            public void mouseEntered(MouseEvent e){
                    mouseOn = true;
                    Media.playSound("mouseOn");
                    repaint();
            }
            public void mouseReleased(MouseEvent e){
                    //防止在按钮之外的地方松开鼠标
                    if(contains(e.getX(), e.getY()))
                            mouseOn = true;
                    else
                            mouseOn = false;
                    repaint();
            }
    }
}
