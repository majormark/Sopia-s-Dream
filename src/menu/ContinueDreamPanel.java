package menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dotsandboxesdemo.Media;
import dotsandboxesdemo.Story;
import UI.Img;
import UI.MyButton;
import UI.ThreeStateButton;

@SuppressWarnings("serial")
public class ContinueDreamPanel extends MyPanel implements ActionListener {
	private Frame frame;
	private int x=0;
	private ThreeStateButton stage1 = new ThreeStateButton(Img.Stage1, Img.Stage1,1) ;
	private ThreeStateButton stage2 = new ThreeStateButton(Img.Stage2SUO, Img.Stage2,2) ;
	private ThreeStateButton stage3 = new ThreeStateButton(Img.Stage3SUO, Img.Stage3,3) ;
	private ThreeStateButton stage4 = new ThreeStateButton(Img.Stage4SUO, Img.Stage4,4) ;
	private ThreeStateButton stage5 = new ThreeStateButton(Img.Stage5SUO, Img.Stage5,5) ;
	private ThreeStateButton stage6 = new ThreeStateButton(Img.Stage6SUO, Img.Stage6,6) ;
	private ThreeStateButton stage7 = new ThreeStateButton(Img.Stage7SUO, Img.Stage7,7) ;
	private ThreeStateButton stage8 = new ThreeStateButton(Img.Stage8SUO, Img.Stage8,8) ;
	private MyButton leftArrow = new MyButton(Img.L_ARROW1,Img.L_ARROW2);
	private MyButton rightArrow = new MyButton(Img.R_ARROW1,Img.R_ARROW2);
	private MyButton back = new MyButton(Img.BACK1,Img.BACK2);
	
	public ContinueDreamPanel (Frame frame) { 
		super(frame);
	 this.frame = frame;	 
	 this.setLayout(null);
	 
	this.add(stage1);	
	stage1.setBounds(90, 42, 374, 230);
	stage1.addActionListener(this);
	stage1.setActionCommand("1");
	this.add(stage2);
	stage2.setBounds(589, 42, 374, 230);
	stage2.addActionListener(this);
	stage2.setActionCommand("2");
	this.add(stage3);
	stage3.setBounds(90, 355, 374, 230);
	stage3.addActionListener(this);
	stage3.setActionCommand("3");
	this.add(stage4);
	stage4.setBounds(589, 355, 374, 230);
	stage4.addActionListener(this);
	stage4.setActionCommand("4");
	this.add(stage5);
	stage5.setBounds(90+1052, 42, 374, 230);
	stage5.addActionListener(this);
	stage5.setActionCommand("5");
	this.add(stage6);
	stage6.setBounds(589+1052, 42, 374, 230);
	stage6.addActionListener(this);
	stage6.setActionCommand("6");
	this.add(stage7);
	stage7.setBounds(90+1052, 355, 374, 230);
	stage7.addActionListener(this);
	stage7.setActionCommand("7");
	this.add(stage8);
	stage8.setBounds(589+1052, 355, 374, 230);
	stage8.addActionListener(this);
	stage8.setActionCommand("8");
	this.add(back);	
	back.setBounds(17, 605, 116, 27);
	back.addActionListener(this);
	back.setActionCommand("back"); 
	this.add(leftArrow);
	leftArrow.setBounds(0, 0, 60, 650);
	leftArrow.addActionListener(this);
	leftArrow.setActionCommand("left"); 
	this.add(rightArrow);
	rightArrow.setBounds(992, 0, 60, 650);
	rightArrow.addActionListener(this);
	rightArrow.setActionCommand("right"); 
	}

	public void paintComponent(Graphics g){
		g.drawImage(Img.DREAM_BG,0,0,this);
		g.drawImage(Img.DREAM_p1,x,0,this);
		g.drawImage(Img.DREAM_p2,1052+x,0,this);
	}
	public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand()=="1"){		
    	   Media.playLegalStageChosen();
			frame.story=new Story(frame, 1);
			frame.changeGradually(this, frame.story);
		}
       else if(e.getActionCommand()=="2"){
    	   if (!stage2.isLocked){
    		   Media.playLegalStageChosen();
    		   frame.story=new Story(frame, 2);
   			frame.changeGradually(this, frame.story);
    	   }
    	   else{
    		   Media.playIllegalSoundWhenLocked();
    	   }
		}
       else if(e.getActionCommand()=="3"){
    	   if (!stage3.isLocked){
    		   Media.playLegalStageChosen();
    		   frame.story=new Story(frame, 3);
   			frame.changeGradually(this, frame.story);
    	   }
    	   else{
    		   Media.playIllegalSoundWhenLocked();
    	   }
		}
       else if(e.getActionCommand()=="4"){	
    	   if (!stage4.isLocked){
    		   Media.playLegalStageChosen();
    		   frame.story=new Story(frame, 4);
   			frame.changeGradually(this, frame.story);
    	   }
    	   else{
    		   Media.playIllegalSoundWhenLocked();
    	   }
		}
       else if(e.getActionCommand()=="5"){			
    	   if (!stage5.isLocked){
    		   Media.playLegalStageChosen();
    		   frame.story=new Story(frame, 5);
   			frame.changeGradually(this, frame.story);
    	   }
    	   else{
    		   Media.playIllegalSoundWhenLocked();
    	   }
		}
       else if(e.getActionCommand()=="6"){
    	   if (!stage6.isLocked){
    		   Media.playLegalStageChosen();
    		   frame.story=new Story(frame, 6);
   			frame.changeGradually(this, frame.story);
    	   }
    	   else{
    		   Media.playIllegalSoundWhenLocked();
    	   }
		}
       else if(e.getActionCommand()=="7"){
    	   if (!stage7.isLocked){
    		   Media.playLegalStageChosen();
    		   frame.story=new Story(frame, 7);
   			frame.changeGradually(this, frame.story);
    	   }
    	   else{
    		   Media.playIllegalSoundWhenLocked();
    	   }
		}
       else if(e.getActionCommand()=="8"){		
    	   if (!stage8.isLocked){
    		   Media.playLegalStageChosen();
    		   frame.story=new Story(frame, 8);
   			frame.change(this, frame.story);
    	   }
    	   else{
    		   Media.playIllegalSoundWhenLocked();
    	   }
		}
       else if(e.getActionCommand()=="back"){						
			frame.change(this, frame.startMenu);			
       }
       else if(e.getActionCommand() == "right") {
			new RightThread().start();
       }
       else if(e.getActionCommand() == "left") {
	   	    new LeftThread().start();
      }
       
	}
	
	class LeftThread extends Thread {//´Ó×óÍùÓÒÒÆ¶¯
		public void run() {
			int x1 = stage1.getX();
			int x2 = stage2.getX();
			int x3 = stage3.getX();
			int x4 = stage4.getX();
			int y1 = stage1.getY();
			int y2 = stage2.getY();
			int y3 = stage3.getY();
			int y4 = stage4.getY();
			int x5 = stage5.getX();
			int x6 = stage6.getX();
			int x7 = stage7.getX();
			int x8 = stage8.getX();
			int y5 = stage5.getY();
			int y6 = stage6.getY();
			int y7 = stage7.getY();
			int y8 = stage8.getY();
			if (x1>-960)
				return;
			for (int x = 0; x <=Frame.WIN_W ; x=x+2*(int)Math.sqrt(x)+5) {
				stage1.setLocation(x1+x,y1);
				stage2.setLocation(x2+x,y2);
				stage3.setLocation(x3+x,y3);
				stage4.setLocation(x4+x,y4);
				stage5.setLocation(x5+x,y5);
				stage6.setLocation(x6+x,y6);
				stage7.setLocation(x7+x,y7);
				stage8.setLocation(x8+x,y8);
				ContinueDreamPanel.this.x=x-1052;
				repaint();
				
				try {
					sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
 	}
	class RightThread extends Thread {//´ÓÓÒÍù×óÒÆ¶¯
		public void run(){
			int x1 = stage1.getX();
			int x2 = stage2.getX();
			int x3 = stage3.getX();
			int x4 = stage4.getX();
			int y1 = stage1.getY();
			int y2 = stage2.getY();
			int y3 = stage3.getY();
			int y4 = stage4.getY();
			int x5 = stage5.getX();
			int x6 = stage6.getX();
			int x7 = stage7.getX();
			int x8 = stage8.getX();
			int y5 = stage5.getY();
			int y6 = stage6.getY();
			int y7 = stage7.getY();
			int y8 = stage8.getY();
			if (x5<1142)
				return;
			for (int x = 0; x <=Frame.WIN_W ; x=x+2*(int)Math.sqrt(x)+5) {
				stage1.setLocation(x1-x,y1);
				stage2.setLocation(x2-x,y2);
				stage3.setLocation(x3-x,y3);
				stage4.setLocation(x4-x,y4);
				stage5.setLocation(x5-x,y5);
				stage6.setLocation(x6-x,y6);
				stage7.setLocation(x7-x,y7);
				stage8.setLocation(x8-x,y8);
				ContinueDreamPanel.this.x=-x;

				repaint();
				
				try {
					sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
