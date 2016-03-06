package UI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import dotsandboxesdemo.Media;

@SuppressWarnings("serial")
public class MyButton extends JButton{
	protected Image image_on;                 //鼠标在按钮上的图片
	protected Image image_off;                //鼠标不在按钮上的图片
	protected boolean mouseOn;
	private boolean isLocked=false;
    
    public MyButton(Image off,Image on){
            mouseOn = false;
            //加载图片
            this.image_off=off;
            this.image_on=on;
           
            //必须设置！否则会有残影！
            this.setOpaque(false);
            this.addMouseListener(new MouseHandler());
    }
    
    //覆盖此方法绘制自定义的图片
    public void paintComponent(Graphics g){
            if(mouseOn&&(!isLocked))
                    g.drawImage(image_on, 0, 0, this);
            else 
                    g.drawImage(image_off, 0, 0, this);
    }
    
    //覆盖此方法绘制自定义的边框
    public void paintBorder(Graphics g){
            //不要边框
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