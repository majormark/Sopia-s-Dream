package UI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dotsandboxesdemo.Media;

@SuppressWarnings("serial")
public class MyButton extends JButton{
	protected Image image_on;                 //����ڰ�ť�ϵ�ͼƬ
	protected Image image_off;                //��겻�ڰ�ť�ϵ�ͼƬ
	protected boolean mouseOn;
	private boolean isLocked=false;
    
    public MyButton(Image off,Image on){
            mouseOn = false;
            //����ͼƬ
            this.image_off=off;
            this.image_on=on;
           
            //�������ã�������в�Ӱ��
            this.setOpaque(false);
            this.addMouseListener(new MouseHandler());
    }
    
    //���Ǵ˷��������Զ����ͼƬ
    public void paintComponent(Graphics g){
            if(mouseOn&&(!isLocked))
                    g.drawImage(image_on, 0, 0, this);
            else 
                    g.drawImage(image_off, 0, 0, this);
    }
    
    //���Ǵ˷��������Զ���ı߿�
    public void paintBorder(Graphics g){
            //��Ҫ�߿�
    }
    
    public void loseMouse(){
    	this.mouseOn=false;
    	repaint();
    }
    
    public void setLocked(){
    	this.isLocked=true;
    }
    
    public void setFocused(){
    	this.isLocked=false;
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
            	mouseOn=false;
                    repaint();
            }
            public void mousePressed(MouseEvent e){
            	if (MyButton.this.image_off==Img.QUXIAO1) Media.playCancel();
            	else if (MyButton.this.image_off==Img.QUEREN1) Media.playConfirm();
            	else Media.playMoveSoundForMe();
            }
    }
}