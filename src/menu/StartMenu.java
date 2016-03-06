package menu;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dotsandboxesdemo.Media;
import UI.Img;
import UI.MyButton;

							//已经完工
@SuppressWarnings("serial")
public class StartMenu extends MyPanel implements ActionListener{
	
	private MyButton setting=new MyButton(Img.SETTING1,Img.SETTING2);
	private MyButton help=new MyButton(Img.HELP1,Img.HELP2);
	private MyButton quick=new MyButton(Img.QUICK1,Img.QUICK2);
	private MyButton team=new MyButton(Img.US1,Img.US2);
	private	MyButton quit=new MyButton(Img.EXIT1,Img.EXIT2);
	private	MyButton dream=new MyButton(Img.DREAM1,Img.DREAM2);
	private	Frame frame;
	public StartMenu(Frame frame){
		super(frame);
		this.frame=frame;
		this.setLayout(null);
		this.setOpaque(false);
		this.add(setting);
		setting.setBounds(920, 190, 92, 49);
		setting.addActionListener(this);
		setting.setActionCommand("setting");
		this.add(help);
		help.setBounds(828, 581, 68 ,44);
		help.addActionListener(this);
		help.setActionCommand("help");
		this.add(quick);
		quick.setBounds(875, 42, 137, 52);
		quick.addActionListener(this);
		quick.setActionCommand("quick");
		this.add(dream);
		dream.setBounds(875, 116, 137, 55);
		dream.addActionListener(this);
		dream.setActionCommand("dream");
		this.add(team);
		team.setBounds(900, 540, 111, 46);
		team.addActionListener(this);
		team.setActionCommand("team");
		this.add(quit);
		quit.setBounds(920, 257, 92, 48);
		quit.addActionListener(this);
		quit.setActionCommand("quit");
		}
	
	
	
		public void paintComponent(Graphics g){
			g.drawImage(Img.MENU_BG, 0, 0, null);
		}
		
		public void emergeAfterCG(){
			mask.setState(0);
			Thread t=new Thread(mask);
			t.start();
		}
		
		public void emerge(){
			mask.setState(0);
			Media.changeBGMto("mainMenu");
			Thread t=new Thread(mask);
			t.start();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="setting") {
				frame.optionPanel.setMenuBackGround();
				frame.change(this, frame.optionPanel);
			}
			else if(e.getActionCommand()=="help"){
				frame.change(this, frame.help);
			}
			else if(e.getActionCommand()=="quick"){
				frame.change(this, frame.quickStart);
			}
			else if(e.getActionCommand()=="dream"){
				frame.change(this, frame.dreamMode);
			}
			else if(e.getActionCommand()=="team"){
				frame.change(this, frame.team);
			}
			else if(e.getActionCommand()=="quit"){
				System.exit(0);
			}
		}
	}