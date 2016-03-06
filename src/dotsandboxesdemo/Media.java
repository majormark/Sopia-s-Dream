package dotsandboxesdemo;
import java.io.File;
import saint.media.SimplePlayer;

public class Media extends Thread{
	private static SimplePlayer bgmPlayer = null;
	private static SimplePlayer soundPlayer = null;
	private static String newFile="mainMenu";
	
	public static void playBGM() {
		if (bgmPlayer!=null){
			bgmPlayer.stop();
			bgmPlayer=new SimplePlayer();
		}
		else{
			bgmPlayer=new SimplePlayer();
			if (Property.isMusicOn) bgmPlayer.setVolume(1);
			else bgmPlayer.setVolume(0);
		}
		try{
			bgmPlayer.open(new File("sound/"+newFile+".mp3"));
			bgmPlayer.setLoop(true);
			bgmPlayer.setLoopCount(1000);
		}catch (Exception e) {
			System.err.println("文件无法加载");
			return;
		}

		try{
			bgmPlayer.play();	
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void playSound(String name){
		if (!Property.isSoundEffectOn) return;
		soundPlayer=new SimplePlayer();
		
		try{
			soundPlayer.open(new File("sound/"+name+".mp3"));
			soundPlayer.setLoop(false);
		}catch (Exception e) {
			System.err.println("文件无法加载");
			return;
		}

		try{
			soundPlayer.play();	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playMoveSoundForOpponent(){
		playSound("opponentNewMove");
	}
	
	public static void playMoveSoundForMe(){
		playSound("myNewMove");
	}
	
	public static void playIllegalSoundWhenLocked(){
		playSound("choosenIllegalStage");
	}
	
	public static void playLegalStageChosen(){
		playSound("legalStageChosen");
	}
	
	public static void playConfirm(){
		playSound("confirm");
	}
	
	public static void playCancel(){
		playSound("cancel");
	}
	
	public static void playChoose(){
		playSound("chose");
	}
	
	public static void playLose(){
		playSound("lose");
	}
	
	public static void playWin(){
		playSound("win");
	}
	
	public static void playChange(){
		playSound("change");
	}
	
	public static void setBGM(boolean isOn){
		
		if (bgmPlayer!=null){
			if (isOn) {
				bgmPlayer.setVolume(1);
			}
			else bgmPlayer.setVolume(0);
		}
		else{
			bgmPlayer=new SimplePlayer();
			playBGM();
			if (isOn) {
				bgmPlayer.setVolume(1);
			}
			else bgmPlayer.setVolume(0);
		}
	}
	
	public static void changeBGMto(String File){
		if (!Property.isMusicOn) return;
		if (bgmPlayer==null){
			bgmPlayer=new SimplePlayer();
		}
		
        newFile=File;
		new Media().start();

		}
	
	public void run(){
	
		for(int i=0;i<40;i++){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bgmPlayer.setVolume((float) (1-(i+1)*0.025));

		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playBGM();
		bgmPlayer.setVolume(0);
		for(int i=0;i<40;i++){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bgmPlayer.setVolume((float) ((i+1)*0.025));
		
		}
	}
}
