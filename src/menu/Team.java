package menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import dotsandboxesdemo.Media;

	@SuppressWarnings("serial")
	public class Team extends MyPanel{
		private Image bg;
		
		public Team(Frame frame){
			super(frame);
			this.addMouseListener(new MouseListener());
			bg=new ImageIcon("graphics/us.jpg").getImage();
		}
		
		public void paintComponent(Graphics g){
			g.drawImage(bg,0,0,this);
		}
		
		
		
		class MouseListener extends MouseAdapter{
			public void mouseClicked(MouseEvent e){
				Media.playChoose();
				frame.change(Team.this, frame.startMenu);
			}
		}
	}
	
	