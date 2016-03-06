package dotsandboxesdemo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import UI.MyButton;
import menu.Frame;
import menu.MyPanel;
import menu.StageStartPanel;

@SuppressWarnings("serial")
public class Story extends MyPanel implements ActionListener{
	private Frame frame;
	private int progress;
	private ArrayList<Image> images=new ArrayList<Image>();
	private int count=0;
	private MyButton skip=new MyButton(new ImageIcon("graphics/dream/skip1.png").getImage(),new ImageIcon("graphics/dream/skip2.png").getImage());
	


	public Story(Frame frame,int progress){
		super(frame);
		this.frame=frame;
		this.progress=progress;
		this.addMouseListener(new MouseListener());		
		
		this.add(skip);
		skip.setBounds(938, 608, 72, 29);
		skip.addActionListener(this);
		
		Media.changeBGMto(String.valueOf(progress));
		
		switch(progress){
		case 1:
			images.clear();
			for(int i=0;i<28;i++) {
				images.add(new ImageIcon("graphics/dream/c1/"+i+".jpg").getImage());			
			}
			break;
		case 2:
			images.clear();
			for(int i=0;i<19;i++) {
				images.add(new ImageIcon("graphics/dream/c2/"+i+".jpg").getImage());			
			}
			break;
		case 3:
			images.clear();
			for(int i=0;i<23;i++) {
				images.add(new ImageIcon("graphics/dream/c3/"+i+".jpg").getImage());			
			}
			break;
		case 4:
			images.clear();
			for(int i=0;i<19;i++) {
				images.add(new ImageIcon("graphics/dream/c4/"+i+".jpg").getImage());			
			}
			break;
		case 5:
			images.clear();
			for(int i=0;i<30;i++) {
				images.add(new ImageIcon("graphics/dream/c5/"+i+".jpg").getImage());			
			}
			break;
		case 6:
			images.clear();
			for(int i=0;i<36;i++) {
				images.add(new ImageIcon("graphics/dream/c6/"+i+".jpg").getImage());			
			}
			break;
		case 7:
			images.clear();
			for(int i=0;i<44;i++) {
				images.add(new ImageIcon("graphics/dream/c7/"+i+".jpg").getImage());			
			}
			break;
		case 8:
			images.clear();
			for(int i=0;i<15;i++) {
				images.add(new ImageIcon("graphics/dream/c8/"+i+".jpg").getImage());			
			}
			break;
		case 9:
			images.clear();
			for(int i=0;i<30;i++) {
				images.add(new ImageIcon("graphics/dream/c9/"+i+".jpg").getImage());			
			}
			break;
		default:
			break;			
		}
	}
	
	public void actionPerformed(ActionEvent e){
		skip();
	}
	
	public void paintComponent(Graphics g){
		if (count<images.size()){			
			g.drawImage(images.get(count), 0, 0, this);
		}			
	}
	
	public void skip(){
		this.count=images.size()-1;
		repaint();
		StageStartPanel ssp=new StageStartPanel(frame,Story.this,progress);
		frame.putOn( Story.this,ssp);
	}
	
	class MouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			count++;
			repaint();
			if (count==images.size()){
				if (progress==9){
					frame.changeGradually(Story.this, frame.startMenu);
				}
				else{
					count=images.size()-1;
					StageStartPanel ssp=new StageStartPanel(frame,Story.this,progress);
					frame.putOn( Story.this,ssp);
				}
			}
		}
	}
}
