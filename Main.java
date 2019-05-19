import javax.swing.SwingUtilities;
import java.util.*;;
import java.io.*;

public class Main implements Runnable{
	public static final Frame frame = new Frame();
	public void run(){
		frame.intro();
	}
	public static void main(String[] args){
		User.read();
		SwingUtilities.invokeLater(new Main());
	}
}