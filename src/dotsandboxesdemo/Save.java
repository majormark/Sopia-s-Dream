package dotsandboxesdemo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Save  implements Serializable{
	public boolean isMusicOn;
	public boolean isSoundEffectOn;
	public int progress;
	
	public Save(boolean music,boolean sound,int pro){
		this.isMusicOn=music;
		this.isSoundEffectOn=sound;
		this.progress=pro;
	}

}
