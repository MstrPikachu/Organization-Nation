import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Frame extends JFrame {
	private ArrayDeque<Container> back = new ArrayDeque<>();
	private Intro intro = new Intro();
	private MainMenu mainMenu;
	private Settings settings = new Settings();
	private Login login = new Login();
	private Register register = new Register();
	public static final Dimension preferredSize = new Dimension(640, 480);
	public Frame(){
		//set up JFrame
		super("Organization Nation");

		//finish JFrame
		super.setVisible(true);
		super.pack();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void back(){
		setContentPane(back.poll());
	}
	public void intro(){
		back.push(getContentPane());
		setContentPane(intro);
		intro.splashScreen();
		repaint();
		revalidate();
	}

	public void login(){
		back.push(getContentPane());
		setContentPane(login);
		repaint();
		revalidate();
		login.requestFocus();
	}

	public void register(){
		back.push(getContentPane());
		setContentPane(register);
		repaint();
		revalidate();
	}
	public boolean registerUser(String username, char[] password){
		return User.register(username, password);
	}

	/*
         * Authenticates the user into the program.
	 * @param username the user's username
	 * @param password the user's password
	 * @return whether the log-in was successful
	 */
	public boolean authenticate(String username, char[] password){
		return User.login(username, password);
	}

	public void logout(){
		User.logout();
	}

	public void mainMenu(){
		back.push(getContentPane());
		if (mainMenu == null) // must wait to login before initialization
			mainMenu = new MainMenu();
		setContentPane(mainMenu);
		repaint();
		revalidate();
	}

	public void settings(){
		back.push(getContentPane());
		setContentPane(settings);
		repaint();
		revalidate();
	}

	@Override
	public Dimension getPreferredSize(){
		return preferredSize;
	}
	@Override
	public void setContentPane(Container contentPane){
		super.setContentPane(contentPane);
		repaint();
		revalidate();
	}
}