package menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dotsandboxesdemo.DotsMap;
import UI.ComplexButton;
import UI.Img;
import UI.MyButton;

								//已经完工
@SuppressWarnings("serial")
public class QuickStart extends MyPanel implements ActionListener {
	private Frame frame;
	private ComplexButton doubleMode=new ComplexButton(Img.SHUANGREN1,Img.SHUANGREN2,72,105);
	private ComplexButton singleMode=new ComplexButton(Img.RENJI1,Img.RENJI2,72,105);
	private MyButton cancel=new MyButton(Img.QUXIAO1,Img.QUXIAO2);
	
	public QuickStart(Frame frame) {
		super(frame);
		this.frame=frame;
		this.setLayout(null);
			
		this.add(doubleMode);
		doubleMode.setBounds(400, 330, 251, 30);
		doubleMode.addActionListener(this);
		doubleMode.setActionCommand("double");
		this.add(singleMode);
		singleMode.setBounds(400, 290, 251, 30);
		singleMode.addActionListener(this);
		singleMode.setActionCommand("single");
		this.add(cancel);
		cancel.setBounds(489, 376, 59, 29);
		cancel.addActionListener(this);
		cancel.setActionCommand("no");
   }
	
	public void paintComponent(Graphics g){
		g.drawImage(Img.SET_BG,0,0,this);
	}
	   
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("double")){
			frame.dm=new DotsMap(frame,false,0,1,0);
			frame.change(this, frame.dm);
			}
		else if(e.getActionCommand().equals("single")){
			frame.change(this, frame.difficultySetting);
			}
		else if(e.getActionCommand().equals("no")){
			frame.change(this, frame.startMenu);
		}
	}
}
