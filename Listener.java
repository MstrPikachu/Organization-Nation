import java.awt.*;
import java.awt.event.*;

public class Listener implements ActionListener {
	public static final Listener LISTENER = new Listener();
	public void actionPerformed(ActionEvent ae){
		switch (ae.getActionCommand()){
			case "Back": Main.frame.back(); break;
			case "Settings": Main.frame.settings(); break; // call settings
			case "Level Select": Main.frame.levelSelect(); break; // call levelSelect
			case "Log In": Main.frame.login(); break; // call login
			case "Log Out": User.logout(); Main.frame.intro(); break; // call logout and go back to start page
			case "Register": Main.frame.register(); break; // call register
		}
	}
}