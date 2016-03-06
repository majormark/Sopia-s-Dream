package UI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

import dotsandboxesdemo.DotsMap;

@SuppressWarnings("serial")
public class Fire extends JLabel implements Runnable{
	private Image[]fires;
	private Image[]nums;
	private int i=0;
	private boolean onFire=true;
	private int num;
	private DotsMap dm;
	
	public Fire(Image[] fires,Image[] nums,DotsMap dm){
		this.fires=fires;
		this.dm=dm;
		this.nums=nums;
		this.setOpaque(false);
		Thread t=new Thread(this);
		t.start();
	}
	
	public void run(){
		while (onFire){
			repaint();
			dm.repaint();
			try {
				Thread.sleep(67);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			i++;
			if (i==16) i=0;
		}
	}
	
	public void stop(){
		onFire=false;
	}
	
	public void setNum(int num){
		this.num=num;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(fires[i], 0,0,this);
		g.drawImage(nums[num],15,56,this);
	}
}
