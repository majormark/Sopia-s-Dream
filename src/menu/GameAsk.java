package menu;

import java.awt.Graphics;
import java.awt.event.*;

import dotsandboxesdemo.DotsMap;
import dotsandboxesdemo.DotsMapRiddle;
import dotsandboxesdemo.DotsMapRummage;
import UI.Img;
import UI.MyButton;

@SuppressWarnings("serial")
public class GameAsk extends MyPanel implements ActionListener{
	private Frame frame;
	private MyButton yes = new MyButton(Img.YES1,Img.YES2);
	private MyButton no = new MyButton(Img.NO1,Img.NO2);
	private String choose;
	private Boolean haveAI;
	private int difficulty;
	private int firstHand;
	private int progress;
	private DotsMap dotsmap;
	
	public GameAsk (Frame frame,DotsMap dotsmap, String choose,boolean haveAI, int difficulty,int firstHand, int progress) {
		super(frame);
		this.frame = frame;
		this.dotsmap = dotsmap;
		this.choose = choose;
		this.haveAI=haveAI;
		this.difficulty=difficulty;
		this.firstHand=firstHand;
		this.progress = progress;
		this.add(yes);	
		yes.setBounds(573, 339, 62, 30);
		yes.addActionListener(this);
		yes.setActionCommand("yes");
		this.add(no);
		no.setBounds(422, 339, 59, 30);
		no.addActionListener(this);
		no.setActionCommand("no");
	}
	public void paintComponent(Graphics g){
		if(choose.equals("again")) {
			g.drawImage(Img.ASKAGAIN,0,0,this);
		}
		if(choose.equals("mainMenu")) {
			g.drawImage(Img.ASKMAIN,0,0,this);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(choose.equals("again")) {
			if(e.getActionCommand().equals("yes")){
				dotsmap.cease();
				if (progress==2||progress==6||progress==8){
					frame.dm=new DotsMapRummage(frame,progress);
				}
				else if (progress==4){
					frame.dm=new DotsMapRiddle(frame);
				}
				else{
					frame.dm=new DotsMap(frame,haveAI,difficulty,firstHand,progress);
				}
				frame.remove(dotsmap);
				frame.change(this, frame.dm);
			}
			if(e.getActionCommand().equals("no")) {
				frame.remove(this);
				frame.repaint();
				dotsmap.setFocused();
			}
		}
		
		if(choose.equals("mainMenu")) {
			if(e.getActionCommand().equals("yes")){
				dotsmap.cease();
				frame.remove(dotsmap);
				frame.changeGradually(this, frame.startMenu);
			}
			if(e.getActionCommand().equals("no")) {
				frame.remove(this);
				frame.repaint();
				dotsmap.setFocused();
			}
		}
	}
	
}
