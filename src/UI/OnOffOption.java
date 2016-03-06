package UI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class OnOffOption extends JButton{
	private Image image_chosen;               //鼠标在按钮上的图片
    private Image image_blank;                //鼠标不在按钮上的图片
                //鼠标按下按钮时的图片
    private boolean isChosen;
    private int delta;
     
    public OnOffOption(Image blank,Image chosen,boolean isChosen,int delta){
           
            this.isChosen = isChosen;
            //加载图片
            this.image_chosen=chosen;
            this.image_blank=blank;
            this.delta=delta;
           
            //必须设置！否则会有残影！
            this.setOpaque(false);
            
            this.addMouseListener(new MouseHandler());
          
    }
    
    //覆盖此方法绘制自定义的图片
    public void paintComponent(Graphics g){
            if(isChosen)
                    g.drawImage(image_chosen, 0, 0, this);
            else g.drawImage(image_blank, delta, 0, this);
    }
    
    //覆盖此方法绘制自定义的边框
    public void paintBorder(Graphics g){
            //不要边框
    }
    
    
    public void setChosen(){
    	this.isChosen=true;
    	repaint();
    }
    
    public void setBlank(){
    	this.isChosen=false;
    	repaint();
    }
    
    public boolean contains(int x, int y){
        //不判定的话会越界，在组件之外也会激发这个方法
        if(!super.contains(x, y))
                return false;
        if (x>=delta&&x<=(delta+25)) return true;
        else return false;
        }
	 
    class MouseHandler extends MouseAdapter  {
            public void mousePressed(MouseEvent e){
                    isChosen=true;
                    repaint();
            }
    }
}