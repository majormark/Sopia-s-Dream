package menu;

import java.awt.CardLayout;
import java.awt.Point;
import javax.swing.*;
import dotsandboxesdemo.DotsMap;
import dotsandboxesdemo.Story;
import UI.CG;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	boolean isReady=false;
	 public DotsMap dm=null;
	public StartMenu startMenu=new StartMenu(this);
	public OptionPanel optionPanel=new OptionPanel(this,startMenu);
	public Help help=new Help(this);
	public QuickStart quickStart=new QuickStart(this);
	public Team team=new Team(this);
	public DifficultySetting difficultySetting=new DifficultySetting(this);
	public FirstHandSetting firstHandSetting=new FirstHandSetting(this);
    public DreamModePanel dreamMode=new DreamModePanel(this);
    public static Point origin = new Point(); 
    public Story story=null;
	public static final int WIN_W = 1052;
	public static final int WIN_H = 650;
	
	public Frame (){
		super("Sophia's Dream");

		this.getContentPane().add(new CG(startMenu,this));
		
        CardLayout fl=new CardLayout();
		this.setLayout(fl);
        optionPanel.setVisible(false);
        team.setVisible(false);
        quickStart.setVisible(false);
        help.setVisible(false);
        dreamMode.setVisible(false);
        
	    this.setSize(WIN_W,WIN_H);
	    this.setLocationRelativeTo(null);//使窗口位于屏幕中央
	    this.setUndecorated(true);
	    this.setResizable(false);   // 去除窗口栏 (目前暂时保留以供调试方便)
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	
	}
	
	public void change(JPanel cur,JPanel next){
		cur.setVisible(false);
		this.remove(cur);
		System.gc();
		next.setVisible(true);
		add(next);
		next.setBounds(0,0,1052,650);
		repaint();
	}
	
	public void putOn(JPanel cur,MyPanel next){
		add(next);
		next.setVisible(true);
		add(cur);
		cur.setVisible(true);	
	}
	
	public void changeGradually(JPanel cur,MyPanel next){
		next.emerge();
		add(next);
		cur.setVisible(false);
		this.remove(cur);
		cur=null;
		System.gc();
		next.setVisible(true);
		
	}
}
