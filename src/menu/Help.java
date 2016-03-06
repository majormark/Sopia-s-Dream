package menu;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.Img;
import UI.MyButton;

	@SuppressWarnings("serial")
	public class Help extends MyPanel implements ActionListener{
		
		private MyButton understand = new MyButton(Img.UNDERSTAND1,Img.UNDERSTAND2);
		private Frame frame;
		
		public Help(Frame frame){
			super(frame);
			this.frame=frame;
			this.setLayout(null);
			 this.add(understand);
			 understand.setBounds(484, 467, 82, 28);
			 understand.addActionListener(this);
		}
		public void paintComponent(Graphics g){
			g.drawImage(Img.HELP,0,0,this);
		}
		public void actionPerformed(ActionEvent e) {
			frame.change(this, frame.startMenu);
		}
	}
