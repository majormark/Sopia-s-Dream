package menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import dotsandboxesdemo.DotsMapRiddle;
import UI.Img;
import UI.MyButton;

@SuppressWarnings("serial")
public class WordsPanel extends MyPanel implements ActionListener{
	private Image bg=new ImageIcon("graphics/dream/yincangwenzi/bg.png").getImage();
	private Image miss=new ImageIcon("graphics/dream/yincangwenzi/miss.png").getImage();
	private MyButton yes=new MyButton(Img.QUEREN1,Img.QUEREN2);
	private MyButton no=new MyButton(Img.QUXIAO1,Img.QUXIAO2);
	private boolean isMiss=false;
	private JTextArea text=new JTextArea();
	private DotsMapRiddle dm;
	private int scoreA;
	private int scoreB;
	
	public WordsPanel(Frame frame,DotsMapRiddle dm,int scoreA,int scoreB){
		super(frame);
		this.dm=dm;
		this.scoreA=scoreA;
		this.scoreB=scoreB;
		text.setOpaque(false);
		
		this.add(yes);
		yes.setBounds(618, 366, 62, 30);
		yes.addActionListener(this);
		yes.setActionCommand("yes");
		this.add(no);
		no.setBounds(382, 366, 59, 30);
		no.addActionListener(this);
		no.setActionCommand("no");
		this.add(text);
		text.setVisible(true);
		text.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20));

		//EXPERIMENT
		text.setBounds(417, 307, 159, 30);
		text.requestFocus();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg,0,0,this);
		if (isMiss){
			g.drawImage(miss,396,341,this);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("yes")){
			if (text.getText().equals("±ð½ÐÎÒÊ¥Å®")){
				isMiss=false;
				this.setVisible(false);
				frame.remove(this);
				frame.putOn(dm, new DreamOverPanel(frame,dm,5,scoreA,scoreB,1));
			}
			else{
				isMiss=true;
				repaint();
			}
		}
		else{
			isMiss=false;
			this.setVisible(false);
			frame.remove(this);
			frame.putOn(dm, new DreamOverPanel(frame,dm,4,scoreA,scoreB,2));
		}
	}

}
