package UI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class OnOffOption extends JButton{
	private Image image_chosen;               //����ڰ�ť�ϵ�ͼƬ
    private Image image_blank;                //��겻�ڰ�ť�ϵ�ͼƬ
                //��갴�°�ťʱ��ͼƬ
    private boolean isChosen;
    private int delta;
     
    public OnOffOption(Image blank,Image chosen,boolean isChosen,int delta){
           
            this.isChosen = isChosen;
            //����ͼƬ
            this.image_chosen=chosen;
            this.image_blank=blank;
            this.delta=delta;
           
            //�������ã�������в�Ӱ��
            this.setOpaque(false);
            
            this.addMouseListener(new MouseHandler());
          
    }
    
    //���Ǵ˷��������Զ����ͼƬ
    public void paintComponent(Graphics g){
            if(isChosen)
                    g.drawImage(image_chosen, 0, 0, this);
            else g.drawImage(image_blank, delta, 0, this);
    }
    
    //���Ǵ˷��������Զ���ı߿�
    public void paintBorder(Graphics g){
            //��Ҫ�߿�
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
        //���ж��Ļ���Խ�磬�����֮��Ҳ�ἤ���������
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