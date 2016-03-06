package menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.*;
								//已经完工
@SuppressWarnings("serial")
public class DifficultySetting extends MyPanel implements ActionListener{
	private Frame frame;
	private ComplexButton easy=new ComplexButton(Img.JIANDAN1,Img.JIANDAN2,55,57);
	private ComplexButton normal=new ComplexButton(Img.ZHONGDENG1,Img.ZHONGDENG2,55,57);
	private ComplexButton hard=new ComplexButton(Img.KUNNAN1,Img.KUNNAN2,55,57);
	private MyButton cancel=new MyButton(Img.QUXIAO1,Img.QUXIAO2);
	private int difficulty=0;
	
	public DifficultySetting(Frame frame){
		super(frame);
		this.frame=frame;
		this.setLayout(null);
		
		this.add(easy);
		easy.setBounds(445, 281, 160 ,27);
		easy.addActionListener(this);
		easy.setActionCommand("easy");
		this.add(normal);
		normal.setBounds(445,311,160,28);
		normal.addActionListener(this);
		normal.setActionCommand("normal");
		this.add(hard);
		hard.setBounds(445,342,160,26);
		hard.addActionListener(this);
		hard.setActionCommand("hard");
		this.add(cancel);
		cancel.setBounds(489,376,59,29);
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(Img.SET_BG, 0,0,this);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand()=="easy") difficulty=1;
		else if (e.getActionCommand()=="normal") difficulty=2;
		else if (e.getActionCommand()=="hard") difficulty=3;
		
		if (e.getActionCommand()=="cancel"){
			frame.change(this, frame.quickStart);
		}
		else{
			frame.firstHandSetting.setDifficulty(difficulty);
			frame.change(this, frame.firstHandSetting);
		}
	}
}