package menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dotsandboxesdemo.Story;
import UI.ComplexButton;
import UI.Img;
import UI.MyButton;

@SuppressWarnings("serial")
public class DreamModePanel extends MyPanel implements ActionListener{
	private Frame frame;
	private ComplexButton newstart=new ComplexButton(Img.NEWDREAM1,Img.NEWDREAM2,72,105);
	private ComplexButton continuegame=new ComplexButton(Img.CONTINUEDREAM1,Img.CONTINUEDREAM2,72,105);
	private MyButton cancel=new MyButton(Img.QUXIAO1,Img.QUXIAO2);
	
	public DreamModePanel(Frame frame){
		super(frame);
		this.frame=frame;
		this.setLayout(null);		        
		this.add(newstart);
		this.add(continuegame);
		this.add(cancel);
		newstart.setBounds(400, 290, 251, 30);
		newstart.addActionListener(this);
		newstart.setActionCommand("newstart");
		cancel.setBounds(489, 376, 59, 29);
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		continuegame.setBounds(400, 330, 251, 30);
		continuegame.addActionListener(this);
		continuegame.setActionCommand("continuegame");
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(Img.SET_BG,0,0,this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="cancel"){
			frame.change(this, frame.startMenu);
		}
		if(e.getActionCommand()=="newstart"){
			frame.story=new Story(frame, 1);
			frame.changeGradually(this, frame.story);
		}
		if(e.getActionCommand()=="continuegame"){
			ContinueDreamPanel cdp=new ContinueDreamPanel(frame);
			frame.change(this, cdp);
		}
	}
 }
