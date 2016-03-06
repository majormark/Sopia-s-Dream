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
	protected Image image_unlocked;               //����ڰ�ť�ϵ�ͼƬ
	protected Image image_locked;                //��겻�ڰ�ť�ϵ�ͼƬ
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
        //����ͼƬ
        this.image_locked=locked;
        this.image_unlocked=unlocked;
       this.stage = stage;
        //�������ã�������в�Ӱ��
        this.setOpaque(false);
        this.addMouseListener(new MouseHandler());
        
}
	//���Ǵ˷��������Զ���ı߿�
    public void paintBorder(Graphics g){
            //��Ҫ�߿�
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
    
    
  //������롢�뿪ͼƬ��Χ����Ϣ
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
                    //��ֹ�ڰ�ť֮��ĵط��ɿ����
                    if(contains(e.getX(), e.getY()))
                            mouseOn = true;
                    else
                            mouseOn = false;
                    repaint();
            }
    }
}
