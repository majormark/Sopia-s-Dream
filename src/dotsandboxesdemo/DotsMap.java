package dotsandboxesdemo;

import menu.*;
import UI.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.ImageIcon;

//获取鼠标事件，调用board进行逻辑计算，绘图输出
@SuppressWarnings("serial")
public class DotsMap extends MyPanel implements Runnable,ActionListener {
	protected Frame frame;
	protected Board bd;
	private int insX;// 鼠标点击之处对应横竖线的左上角的坐标
	private int insY;
	protected Boolean horizon = false;// true代表水平 false代表竖线
	private int firstHand;
	private int player;// 1代表人类（玩家A）,2代表机器（玩家B），
	private Mouseclicked mouseclicked;
	private MouseMoved mouseMoved;
	private Boolean haveAI=true;
	private int difficulty;
	private Computer computer;
	private LineShape lastLine=null;
	protected int[][] whoseBox;
	protected int whiteX;
	protected int whiteY;
	protected boolean isWhite;
	protected int progress;
	private boolean isOn=true;
	protected int scoreA;
	protected int scoreB;
	
	protected Fire redFire;
	protected Fire blueFire;
	protected Image bg;

	private MyButton mainMenu;
	private MyButton again;
	private MyButton set;
	private GameAsk ga;
	protected Thread t=new Thread(this);
	
	protected boolean isFocused=false;
	
	public DotsMap(Frame frame,boolean haveAI, int difficulty,int firstHand,int progress) {
		super(frame);
		if (progress!=0) bg=new ImageIcon("graphics/background/" + progress + ".jpg").getImage();
		else{
			int index=(int)(Math.random()*8);
			bg=new ImageIcon("graphics/background/b" + index + ".jpg").getImage();
			index=(int)(Math.random()*8)+1;
			Media.changeBGMto(String.valueOf(index));
		}
		this.frame=frame;
		this.progress=progress;
	
		if (progress==0){
			this.haveAI=haveAI;
			this.difficulty=difficulty;
			this.firstHand=firstHand;
		}
		else{
			haveAI=true;
			switch(progress){
			case 1:
				difficulty=1;
				firstHand=1;
				break;
			case 2:
				difficulty=1;
				firstHand=2;
				break;
			case 3:
				difficulty=1;
				firstHand=1;
				break;
			case 4:
				difficulty=2;
				firstHand=2;
				break;
			case 5:
				difficulty=2;
				firstHand=1;
				break;
			case 6:
				difficulty=2;
				firstHand=2;
				break;
			case 7:
				difficulty=3;
				firstHand=1;
				break;
			case 8:
				difficulty=3;
				firstHand=2;
				break;
			}
		}

		player = firstHand;
		bd = new Board();
		bd.reset();

		setSize(1052, 650);
		setVisible(true);
		
		emerge();

		if (haveAI)
		computer = new Computer(difficulty);
		
		mouseMoved = new MouseMoved();
		this.addMouseMotionListener(mouseMoved);
		
		mouseclicked = new Mouseclicked();
		this.addMouseListener(mouseclicked);
		
		redFire=new Fire(Img.RED_FIRE,Img.NUM_RED,this);
		redFire.setBounds(55, 10, 200, 200);
		this.add(redFire);
		blueFire=new Fire(Img.BLUE_FIRE,Img.NUM_BLUE,this);
		blueFire.setBounds(271, 10, 200, 200);
		this.add(blueFire);
		
		mainMenu=new MyButton(Img.MAINMENU1,Img.MAINMENU2);
		this.add(mainMenu);
		mainMenu.setBounds(49, 503, 94, 40);
		mainMenu.addActionListener(this);
		mainMenu.setActionCommand("mainMenu");
		
		again=new MyButton(Img.Again1,Img.Again2);
		this.add(again);
		again.setBounds(300, 451, 114, 40);
		again.addActionListener(this);
		again.setActionCommand("again");
		
		set=new MyButton(Img.Set1,Img.Set2);
		this.add(set);
		set.setBounds(341, 503, 72, 40);
		set.addActionListener(this);
		set.setActionCommand("set");
		
		t.start();
	}
	
	public void cheat(){
		if ((!haveAI)||progress==0) return;
		isOn=false;
		scoreA=25;
		scoreB=0;
		int i,j;
		for (i=0;i<5;i++){
			for (j=0;j<5;j++){
				whoseBox[i][j]=1;
			}
		}
		repaint();
	}
	
	public void emerge(){
		LogoMask mask=new LogoMask(this);
		mask.setBounds(0,0,1052,650);
		this.add(mask);
		Thread t=new Thread(mask);
		t.start();
	}
	
	public void cease(){
		this.isOn=false;
		this.redFire.stop();
		this.blueFire.stop();
		redFire.setVisible(false);
		blueFire.setVisible(false);
		this.setLocked();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("again")&&isFocused){
			ga=new GameAsk(frame, this,"again",haveAI,  difficulty, firstHand,  progress);
			frame.putOn(this, ga);
			this.setLocked();
		}
		else if(e.getActionCommand().equals("mainMenu")&&isFocused){
			ga=new GameAsk(frame,this, "mainMenu",haveAI,  difficulty, firstHand,  progress);
			frame.putOn(this, ga);
			this.setLocked();
		}
		else if(e.getActionCommand().equals("set")&&isFocused){
			frame.optionPanel.setGameBackGround();
			frame.putOn(this, frame.optionPanel);
			this.setLocked();
		}
	}
	
	public void run() {
		int getBox;
		while (scoreA+scoreB!=25&&isOn) {
			if (player == 2 && haveAI&&isFocused) {
				Line AIpt = computer.AImove(bd,2);
				getBox = bd.addLines(AIpt, 2);
				scoreA=bd.getScore()[1];
				scoreB=bd.getScore()[2];
				putNewLine(AIpt, 2);
				repaint();
				if (getBox == 0)	player = 3 - player;	
				} 
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			}
		checkWinner();
	}
	
	protected void checkWinner(){
		if (scoreA+scoreB==25){
			if (haveAI&&progress == 0) {
				SingleGameOverPanel sgop=new SingleGameOverPanel(frame, this,difficulty, firstHand, scoreA>scoreB?1:2, scoreA, scoreB);
				frame.putOn(this, sgop);
			}
			if(!haveAI&&progress == 0) {
				DoubleGameOverPanel dgop=new DoubleGameOverPanel(frame,this, difficulty, firstHand,  scoreA, scoreB);
				frame.putOn(this, dgop);
			}
			if(progress != 0) {
				DreamOverPanel dop=new DreamOverPanel(frame,this,progress,scoreA,scoreB,scoreA>scoreB?1:2);
				frame.putOn(this,dop);
			}
		}
	}
	
	private void putNewLine(Line line,int player){
		LineShape sl=null;
		int x=line.getX();
		int y=line.getY();
			if (line.getDirection()){
				if (player==1){
					sl=new LineShape(Img.LINE_BLUE_H,Img.LINE_GR_H,lastLine);
					Media.playMoveSoundForMe();
				}
				else{
					sl=new LineShape(Img.LINE_RED_H,Img.LINE_GR_H,lastLine);
					Media.playMoveSoundForOpponent();
				}
				sl.setBounds(466+106*y,43+106*x,95,30);
				}
			else{
				if (player==1) {
					sl=new LineShape(Img.LINE_BLUE_V,Img.LINE_GR_V,lastLine);
					Media.playMoveSoundForMe();
				}
				else {
					sl=new LineShape(Img.LINE_RED_V,Img.LINE_GR_V,lastLine);
					Media.playMoveSoundForOpponent();
				}
				sl.setBounds(446+106*y,61+107*x,31,99);
			}
		this.lastLine=sl;
		this.add(sl);
		Thread t=new Thread(sl);
		t.start();
		scoreA=bd.getScore()[1];
		scoreB=bd.getScore()[2];
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, this);
		g.drawImage(Img.SOPHIA, 0,0,this);
		paintBorder(g);
		 g.drawImage(Img.BG_RECT, 402, 0, this);
		paintBoxes(g);
		blueFire.setNum(scoreA);
		redFire.setNum(scoreB);
		
		 if (isWhite) {
		 if (horizon) {
		 g.drawImage(Img.LINE_WH_H, 465 + whiteY * 106, 42
					 + 106 * whiteX, this);
		 }
		 else {
		 g.drawImage(Img.LINE_WH_V, 446 + 106 * whiteY, 61
					 + 107 * whiteX, this);
		 }
		 }
		 
		g.drawImage(Img.DOTS, 402, 0, this);
		g.drawImage(Img.FRAME, 402, 0, this);
	}
	
	protected void paintBoxes(Graphics g) {
		whoseBox=bd.getWhoseBox();
		int i, j;
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				if (whoseBox[i][j] == 1) {
					g.drawImage(Img.RECT_BLUE[i][j], 461 + j * 107,
							57 + i * 107, this);
				}
				if (whoseBox[i][j] == 2) {
					g.drawImage(Img.RECT_RED[i][j], 461 + j * 107,
							57 + i * 107, this);
				}
			}
		}
	}
	
	public void drawWhite(int x, int y, boolean horizon) {
		this.whiteX = x;
		this.whiteY = y;
		this.horizon = horizon;
		this.isWhite = true;
		repaint();
	}

	class MouseMoved extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e){
			float mouseX = e.getX();
			float mouseY = e.getY();
			if (!isFocused) return;
			if (!clickOnMap(mouseX, mouseY)||!bd.isValid(new Line(insY,insX,horizon))){
				isWhite=false;
				repaint();
			}
			else
				drawWhite(insY,insX,horizon);
		}
	}

	class Mouseclicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			float mouseX = e.getX();
			float mouseY = e.getY();
			if (clickOnMap(mouseX, mouseY)&&isFocused) {
				if (player == 1 || !haveAI) {
					Line pt = new Line(insY, insX,horizon); // pt等于鼠标点中的该点坐标
					if (bd.isValid(pt)){
						int getBox = bd.addLines(pt, player); 
						scoreA=bd.getScore()[1];
						scoreB=bd.getScore()[2];
						putNewLine(pt, player);
						if (getBox == 0)
							player = 3 - player;// 划线，若成功划线且没有占领格子，换手
					}
				}
			}
			else if(230<=mouseX&&239>=mouseX&&20<=mouseY&&30>=mouseY){
				cheat();
			}
		}
	}
	
	private void setLocked(){
		this.isFocused=false;
		mainMenu.setLocked();
		set.setLocked();
		again.setLocked();
	}
	
	public void setFocused(){
		this.isFocused=true;
		mainMenu.setFocused();
		set.setFocused();
		again.setFocused();
	}

	private Boolean clickOnMap(float x, float y) { // 判断是否鼠标点在棋盘上，若是，返回true，计算左上角坐标，并判断是横线还是竖线
		 if ((402 + 50 < x) && (x < 1052 - 50) && (50 < y) && (y < 600)) {
				x = x - 402 - 50;
				y = y - 50;
		     if(!((x % 106)<=20&&(y % 106)<=20)){
		    
		     if ((20 < (x % 106)  && (y % 106) < 20)||((y % 106) < (x % 106)&&(x % 106)<(106-(y % 106)+20) && (y % 106) < 40)) {
					insX = (int) (x / 106);
					insY = (int) (y / 106);
					horizon = true;
					return true;
				}
	         if((106-(y % 106)+20) < (x % 106)&&(x % 106)<(y % 106)  && (y % 106) > 86){
					insX = (int) (x / 106);
					insY = (int) (y / 106)+1;
					horizon = true;
					return true;
				}
		     if ((x % 106) < 20 && 20 < (y % 106)||((x % 106)<40)&&(x % 106)<(y % 106)&&(y % 106)<(106-(x % 106)+20)) {
					insX = (int)(x/106);
					insY = (int)(y/106);
					horizon = false;
					return true;
				}
		     if ((x % 106) > 86 && (106-(x % 106)+20) < (y % 106)&&(y % 106)<(x % 106)) {
					insX = (int)(x/106)+1;
					insY = (int)(y/106);
					horizon = false;
					return true;
				}
			}
		 }
			return false;
		}

}
