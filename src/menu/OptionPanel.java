package menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import dotsandboxesdemo.Media;
import dotsandboxesdemo.Property;
import UI.Img;
import UI.MyButton;
import UI.OnOffOption;

@SuppressWarnings("serial")
public class OptionPanel extends MyPanel implements ActionListener {
	private Frame frame;
	private JPanel lastPanel;
	private OnOffOption musicOn = new OnOffOption(Img.ON1, Img.ON2,
			Property.isMusicOn, 36);
	private OnOffOption musicOff = new OnOffOption(Img.OFF1, Img.OFF2,
			!Property.isMusicOn, 37);
	private OnOffOption soundEffectOn = new OnOffOption(Img.ON1, Img.ON2,
			Property.isSoundEffectOn, 36);
	private OnOffOption soundEffectOff = new OnOffOption(Img.OFF1, Img.OFF2,
			!Property.isSoundEffectOn, 37);
	private MyButton confirm = new MyButton(Img.QUEREN1, Img.QUEREN2);
	private MyButton cancel = new MyButton(Img.QUXIAO1, Img.QUXIAO2);

	Image backGround = Img.OPTION_BG;

	private boolean isMusicOn;
	private boolean isSoundEffectOn;

	OptionPanel(Frame frame, JPanel lastPanel) {
		super(frame);
		this.frame = frame;
		this.lastPanel = lastPanel;
		this.setLayout(null);
		this.setOpaque(false);
		this.add(musicOn);
		musicOn.setBounds(480, 286, 97, 25);
		musicOn.setActionCommand("musicon");
		musicOn.addActionListener(this);
		this.add(musicOff);
		musicOff.setBounds(570, 286, 97, 25);
		musicOff.setActionCommand("musicoff");
		musicOff.addActionListener(this);
		this.add(soundEffectOn);
		soundEffectOn.setBounds(480, 332, 97, 25);
		soundEffectOn.setActionCommand("soundon");
		soundEffectOn.addActionListener(this);
		this.add(soundEffectOff);
		soundEffectOff.setBounds(570, 332, 97, 25);
		soundEffectOff.setActionCommand("soundoff");
		soundEffectOff.addActionListener(this);
		this.add(confirm);
		confirm.setBounds(583, 376, 60, 29);
		confirm.setActionCommand("confirm");
		confirm.addActionListener(this);
		this.add(cancel);
		cancel.setBounds(406, 376, 59, 29);
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		
		isMusicOn=Property.isMusicOn;
		isSoundEffectOn=Property.isSoundEffectOn;
	}

	public void setMenuBackGround() {
		this.backGround = Img.OPTION_BG;
	}

	public void setGameBackGround() {
		this.backGround = Img.OPTION_BG_GAME;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(backGround, 0, 0, this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "cancel") {
			this.isMusicOn = Property.isMusicOn;
			Media.playCancel();
			if (isMusicOn) {
				musicOn.setChosen();
				musicOff.setBlank();
			} else {
				musicOn.setBlank();
				musicOff.setChosen();
			}
			this.isSoundEffectOn = Property.isSoundEffectOn;
			if (isSoundEffectOn) {
				soundEffectOn.setChosen();
				soundEffectOff.setBlank();
			} else {
				soundEffectOn.setBlank();
				soundEffectOff.setChosen();
			}
			if (this.backGround == Img.OPTION_BG)
				frame.change(this, lastPanel);
			else {
				frame.change(this, frame.dm);
				frame.dm.setFocused();
			}
		}
		if (e.getActionCommand() == "musicon") {
			musicOn.setChosen();
			musicOff.setBlank();
			Media.playChoose();
			isMusicOn = true;
		}
		if (e.getActionCommand() == "musicoff") {
			musicOff.setChosen();
			musicOn.setBlank();
			Media.playChoose();
			isMusicOn = false;
		}
		if (e.getActionCommand() == "soundon") {
			soundEffectOn.setChosen();
			soundEffectOff.setBlank();
			Media.playChoose();
			isSoundEffectOn = true;
		}
		if (e.getActionCommand() == "soundoff") {
			soundEffectOn.setBlank();
			soundEffectOff.setChosen();
			Media.playChoose();
			isSoundEffectOn = false;
		}
		if (e.getActionCommand() == "confirm") {
			Property.isMusicOn = isMusicOn;
			Media.setBGM(Property.isMusicOn);
			Property.isSoundEffectOn = isSoundEffectOn;
			Property.save();
			Media.playConfirm();
			if (this.backGround == Img.OPTION_BG)
				frame.change(this, lastPanel);
			else {
				frame.change(this, frame.dm);
				frame.dm.setFocused();
				System.out.println("ss");
			}
		}
	}
}
