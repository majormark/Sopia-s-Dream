package menu;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dotsandboxesdemo.Property;
import UI.Img;
import UI.Mask;
import UI.MyButton;

@SuppressWarnings("serial")
public class MyPanel extends JPanel{
	Frame frame;
	protected Mask mask=new Mask();
	private MyButton mini=new MyButton(Img.MINI1,Img.MINI2);
	private MyButton close=new MyButton(Img.CLOSE1,Img.CLOSE2);
	private static Point origin = new Point(); 
	
	public MyPanel(Frame frame){
		
		this.frame=frame;
		this.setLayout(null);
		mask.setBounds(0, 0, 1052, 650);
		this.add(mask);
		this.setOpaque(false);
		this.setBounds(0,0,1052,650);
		
		MouseHandler mh=new MouseHandler();
		this.add(mini);
		mini.setBounds(995, 4 ,23, 22);
		mini.addActionListener(mh);
		mini.setActionCommand("mini");
		this.add(close);
		close.setBounds(1022, 5, 23, 21);
		close.addActionListener(mh);
		close.setActionCommand("close");
		
//		Toolkit     tk   =Toolkit.getDefaultToolkit()   ;  //获取工具箱
//		       Image   img   =   new ImageIcon("graphics/cursors.png").getImage();  
//		       Cursor     mycursor   =tk.createCustomCursor(img,   new   Point()   ,"xxx");   //创建鼠标样式   
//		this.setCursor(mycursor);
		
		 this.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
	                    origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
	                    origin.y = e.getY();
	            }
	            });
		    
		    this.addMouseMotionListener(new MouseMotionAdapter() {
	            public void mouseDragged(MouseEvent e) { 
	                    Point p = MyPanel.this.frame.getLocation();  //当鼠标拖动时获取窗口当前位置
	                    if (e.getY()<30)
	                    MyPanel.this.frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	            }
	            });
	}
	
	public void emerge(){
		mask.setState(0);
		Thread t=new Thread(mask);
		t.start();
	}
	
	public void vanish(){
		mask.setState(1);
		Thread t=new Thread(mask);
		t.start();
	}
	
	 class MouseHandler implements ActionListener  {
         public void actionPerformed(ActionEvent e){
        	 if (e.getActionCommand().equals("close")){
     			Property.save();
     			System.exit(0);
     		}
     		else{
     			frame.setExtendedState(Frame.ICONIFIED);
     		}
         }
 }
}
