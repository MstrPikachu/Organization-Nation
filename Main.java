/**
 * This is the main class that runs the whole program.
 *
 * @author Peter Lin
 * @version 1.5 05/13/19
 */

import javax.swing.SwingUtilities;

public class Main {
	/** The <code>Frame</code> that is shown to the user */
	public static Frame frame;

	/**
	 * This method makes an instance of a Frame, which is the GUI which the user interacts with.
	 *
	 * @param args the command line arguments. Not used.
	 */
	public static void main(String[] args){
		User.read();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				frame = new Frame();
				frame.initializeContent();
				frame.intro();
			}
		});
	}
}