import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	private Intro intro = new Intro();
	private MainMenu mainMenu = new MainMenu();
	private Settings settings = new Settings();
	private Login login = new Login();
	//private Register register = new Register();
	public static final Dimension preferredSize = new Dimension(640, 480);
	public Frame(){
		//set up JFrame
		super("Organization Nation");

		//finish JFrame
		super.setVisible(true);
		super.pack();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void intro(){
		super.setContentPane(intro);
		intro.splashScreen();
		repaint();
		revalidate();
	}

	public void login(){
		login.setBackground(Color.YELLOW);
		super.setContentPane(login);
		repaint();
		revalidate();
	}

	public void register(){
		//super.setContentPane(register);
		repaint();
		revalidate();
	}

	/*
         * Authenticates the user into the program.
	 * @param username the user's username
	 * @param password the user's password
	 * @return whether the log-in was successful
	 */
	public boolean authenticate(String username, char[] password){
		if (username.charAt(0) == ' ' || username.charAt(username.length() - 1) == ' '){
			JOptionPane.showMessageDialog(this, "Please do not enter leading or trailing spaces.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (username.length() == 0){
			JOptionPane.showMessageDialog(this, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (username.length() > 15){
			JOptionPane.showMessageDialog(this, "Your username is too long.", "Error", JOptionPane.ERROR_MESSAGE);
			return false; //username is too long
		}
		for (int i = 0; i < username.length(); i ++){
			if (username.charAt(i) < 32){
				JOptionPane.showMessageDialog(this, "Your username contains invalid characters.", "Error", JOptionPane.ERROR_MESSAGE);
				return false; // contains invalid characters
			}
		}
		for (int i = 0; i < Main.users.length; i ++){
			if (Main.users[i].substring(0, Main.users[i].indexOf('\u001b')).equals(username)){
				JOptionPane.showMessageDialog(this, "Sorry, this username is taken.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	public void mainMenu(){
		super.setContentPane(mainMenu);
		repaint();
		revalidate();
	}

	public void settings(){
		super.setContentPane(settings);
		repaint();
		revalidate();
	}

	@Override
	public Dimension getPreferredSize(){
		return preferredSize;
	}
}