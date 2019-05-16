import javax.swing.SwingUtilities;

public class Main implements Runnable{
	public static final Frame frame = new Frame();
	public void run(){
		frame.intro();
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Main());
	}
}