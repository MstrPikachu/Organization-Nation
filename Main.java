/**
 * This is the main class that runs the whole program.
 *
 * @author Peter Lin
 * @version 1.5 05/13/19
 */

import javax.swing.SwingUtilities;
import javax.sound.sampled.*;

public class Main {
	/** The <code>Frame</code> that is shown to the user */
	public static Frame frame;

	/**
	 * This method makes an instance of a Frame, which is the GUI which the user interacts with.
	 *
	 * @param args the command line arguments. Not used.
	 */
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, java.io.IOException{
		User.read();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				frame = new Frame();
				frame.initializeContent();
				frame.intro();
			}
		});

		//play music
		Clip clip = AudioSystem.getClip();
		AudioInputStream audio = AudioSystem.getAudioInputStream(Main.class.getResource("data/Tetris.wav"));
		clip.open(audio);
		clip.setLoopPoints(0, -1);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}