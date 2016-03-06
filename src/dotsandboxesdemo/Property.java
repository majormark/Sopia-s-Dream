package dotsandboxesdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Property{
	public static boolean isMusicOn;
	public static boolean isSoundEffectOn;
	public static int progress=1;
	
	public static void load(){
		try {
			ObjectInputStream in = new ObjectInputStream
					(new FileInputStream("property.obj"));
			Save save=(Save)in.readObject();
			Property.isMusicOn=save.isMusicOn;
			Property.isSoundEffectOn=save.isSoundEffectOn;
			Property.progress=save.progress;
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void save(){
		try {
			ObjectOutputStream out = new ObjectOutputStream
					(new FileOutputStream("property.obj"));
			out.writeObject(new Save(Property.isMusicOn, Property.isSoundEffectOn, Property.progress));
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
