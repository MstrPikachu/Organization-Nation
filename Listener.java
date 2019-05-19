import java.awt.*;
import java.awt.event.*;

public class Listener implements ActionListener {
	public static final Listener LISTENER = new Listener();
	public void actionPerformed(ActionEvent ae){
		switch (ae.getActionCommand()){
			case "Back":
			case "Register!":
			case "Log In!":
			case "Main Menu": Main.frame.mainMenu(); break; // call main menu
			case "Settings": Main.frame.settings(); break; // call settings
			case "Log In": Main.frame.login(); break; // call login
			case "Log Out": User.logout(); Main.frame.intro(); break; // call logout and go back to start page
			case "Register": Main.frame.register(); break; // call register
		}
	}
}