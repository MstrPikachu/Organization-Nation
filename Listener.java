import java.awt.*;
import java.awt.event.*;

public class Listener implements ActionListener {
	public static final Listener LISTENER = new Listener();
	public void actionPerformed(ActionEvent ae){
		switch (ae.getActionCommand()){
			case "Main Menu": Main.frame.mainMenu(); break; // call main menu
			case "Settings": Main.frame.settings(); break; // call settings
			case "Back": Main.frame.mainMenu(); break; // go back to main menu
		}
	}
}