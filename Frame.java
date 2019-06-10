import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

/**
 * This is the frame that contains all the content that is showed to the user.
 * It contains multiple methods that change the content, which usually have
 * a corresponding button on the GUI.
 *
 * @author Peter Lin
 * @version 1.7
 */

public class Frame extends JFrame {
	// A list of previous frames, and their order, allowing the user to "go back". 
	private ArrayDeque<Container> back = new ArrayDeque<>();
	// The intro menu. 
	private Intro intro;
	// The main menu. 
	private MainMenu mainMenu;
	// The settings menu. 
	private Settings settings;
	// The login menu. 
	private Login login;
	// The register user menu. 
	private Register register;
	// The level selection menu. 
	private LevelSelect levelSelect;
	// The level 1 screen.
	private LevelOne levelOne;
	// The level 2 screen.
	private LevelTwo levelTwo;
	// The level 3 screen.
	private LevelThree levelThree;
	// The pause menu.
	private PauseMenu pause;

	/** The <code>Dimension</code> of this <code>Frame</code>'s contentPane. */
	public static final Dimension preferredSize = new Dimension(640, 400);

	/**
	 * The class constructor, making an empty <code>JFrame</code>.
	 * It has the preferred size and is visible, and will exit on close.
	 */
	public Frame(){
		//set up JFrame
		super("Organization Nation");

		//finish JFrame
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
	}


	/**
	 * Initializes the internal frames used in this JFrame.
	 *
	 */
	public void initializeContent(){
		intro = new Intro();
		settings = new Settings();
		login = new Login();
		register = new Register();
		pause = new PauseMenu();
		setGlassPane(pause);
		super.setVisible(true);
		super.pack();
	}

	/**
	 * Pauses the game.
	 */
	public void pause(){
		stopTimer();
		pause.setVisible(true);
		repaint();
		revalidate();
	}

	/**
	 * Resumes the game.
	 */
	public void resume(){
		pause.setVisible(false);
		startTimer();
		repaint();
		revalidate();
	}

	/**
	 * Goes to the previous <code>java.awt.Container</code> that this <code>Frame</code> was displaying.
	 * Must only be called if the current content pane has a "back" button.
	 */
	public void back(){
		setContentPane(back.poll());
	}

	/**
	 * Displays the intro content and runs an introductory animation.
	 */
	public void intro(){
		back.push(getContentPane());
		setContentPane(intro);
		intro.splashScreen();
		repaint();
		revalidate();
	}

	/**
	 * Displays the login menu.
	 * Sets the mouse cursor in the username text field.
	 */
	public void login(){
		back.push(getContentPane());
		setContentPane(login);
		repaint();
		revalidate();
		login.requestFocus();
	}

	/**
	 * Logs the user out.
	 */
	public void logout(){
		User.logout();
		back.clear();
		intro();
	}

	/**
	 * Displays the register user menu.
	 * Sets the mouse cursor in the username text field.
	 */
	public void register(){
		back.push(getContentPane());
		setContentPane(register);
		repaint();
		revalidate();
	}

	/**
	 * Displays the main menu.
	 * Must be called after the user is logged in.
	 * Creates a new instance of <code>MainMenu</code> and <code>LevelSelect</code>
	 * so user information can be updated.
	 */
	public void mainMenu(){
		pause.setVisible(false);
		back.clear();
		back.push(getContentPane());
		mainMenu = new MainMenu(); // make a new JPanel for the new user
		levelSelect = new LevelSelect();
		setContentPane(mainMenu);
		repaint();
		revalidate();
	}

	/**
	 * Displays the settings menu.
	 */
	public void settings(){
		back.push(getContentPane());
		setContentPane(settings);
		repaint();
		revalidate();
	}

	/**
	 * Displays the level selection menu.
	 */
	public void levelSelect(){
		back.push(getContentPane());
		setContentPane(levelSelect);
		repaint();
		revalidate();
	}

	/**
	 * Displays the first level.
	 */
	public void levelOne(){
		setContentPane(levelOne = new LevelOne());
		pack();
		repaint();
		revalidate();
	}

	/**
	 * Displays the second level.
	 */
	public void levelTwo(){
		setContentPane(levelTwo = new LevelTwo());
		pack();
		repaint();
		revalidate();

	}

	/**
	 * Displays the third level.
	 */
	public void levelThree(){
		setContentPane(levelThree = new LevelThree());
		pack();
		repaint();
		revalidate();
	}

	/**
	 * Stops the timer of the current content pane.
	 * Only stops the timer if it implements the Timed interface.
	 *
	 * @see Timed
	 */
	public void stopTimer(){
		if (getContentPane() instanceof Timed)
			((Timed)getContentPane()).getTimer().stop();
	}

	/**
	 * Starts the timer of the current content pane.
	 * Only stops the timer if it implements the Timed interface.
	 *
	 * @see Timed
	 */
	public void startTimer(){
		if (getContentPane() instanceof Timed)
			((Timed)getContentPane()).getTimer().start();
	}

	/**
	 * Stops the timer if the previous content pane was timed.
	 *{@inheritDoc}
	 */
	@Override
	public void setContentPane(Container container){
		stopTimer();
		super.setContentPane(container);
		startTimer();
		pack();
	}

}