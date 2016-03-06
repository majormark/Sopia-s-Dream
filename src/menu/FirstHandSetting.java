package menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dotsandboxesdemo.DotsMap;
import UI.*;

@SuppressWarnings("serial")
public class FirstHandSetting extends MyPanel implements ActionListener{
	private Frame frame;
	private ComplexButton first=new ComplexButton(Img.XIANSHOU1,Img.XIANSHOU2,55,63);
	private ComplexButton second=new ComplexButton(Img.HOUSHOU1,Img.HOUSHOU2,55,63);
	private MyButton cancel=new MyButton(Img.QUXIAO1,Img.QUXIAO2);
	int difficulty=0;
	int firstHand=0;
	
	public FirstHandSetting(Frame frame){
		super(frame);
		this.frame=frame;
		this.setLayout(null);
		this.add(first);
		first.setBounds(440,290,174,30);
		first.addActionListener(this);
		first.setActionCommand("first");
		this.add(second);
		second.setBounds(440,330,174,30);
		second.addActionListener(this);
		second.setActionCommand("second");
		this.add(cancel);
		cancel.setBounds(489,376,59,29);
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(Img.SET_BG, 0,0,this);
	}
	
	public void setDifficulty(int difficulty){
		this.difficulty=difficulty;
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand()=="first") firstHand=1;
		else if (e.getActionCommand()=="second") firstHand=2;
		
		if (e.getActionCommand()=="cancel"){
			frame.change(this, frame.difficultySetting);
		}
		else{
			frame.dm=new DotsMap(frame,true,difficulty,firstHand,0);
			frame.change(this, frame.dm);
		}
	}
}