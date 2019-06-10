import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.beans.EventHandler;

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
	// An empty JPanel.
	private JPanel empty;

	//background constants
	public static final int CIRCLES = 9;
	public static final int DIAMETER = 150;
	//x, y, and change in x, y values for each circle in the background
	public double[] x = new double[CIRCLES];
	public double[] y = new double[CIRCLES];
	public double[] dx = new double[CIRCLES];
	public double[] dy = new double[CIRCLES];
	public Color[] colors = new Color[CIRCLES];
	public final ActionListener backgroundListener = EventHandler.create(ActionListener.class, this, "updateCircles");


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

		//make colors and initialize coordinate values
		for (int i = 0; i < CIRCLES; i ++){
			double angle = Math.PI / CIRCLES * i;
			colors[i] = new Color ((int) (127 * Math.sin (angle) + 128), (int) (127 * Math.sin (angle + Math.PI * 2 / 3) + 128), (int) (127 * Math.sin (angle + Math.PI * 4 / 3) + 128), 128);
			x[i] = Math.random() * (Frame.preferredSize.width - DIAMETER);
			y[i] = Math.random() * (Frame.preferredSize.height - DIAMETER);
			dx[i] = Math.random() * -2 + 4;
			dy[i] = Math.random() * -2 + 4;
		}
	}


	/**
	 * Initializes the internal frames used in this JFrame.
	 *
	 */
	public void initializeContent(){
		empty = new JPanel();
		empty.setPreferredSize(preferredSize);
		intro = new Intro();
		settings = new Settings();
		login = new Login();
		register = new Register();
		pause = new PauseMenu();
		setGlassPane(pause);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);
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
		setContentPane(empty);
		setContentPane(levelOne = new LevelOne());
		pack();
		repaint();
		revalidate();
	}

	/**
	 * Displays the second level.
	 */
	public void levelTwo(){
		setContentPane(empty);
		setContentPane(levelTwo = new LevelTwo());
		pack();
		repaint();
		revalidate();
	}

	/**
	 * Displays the third level.
	 */
	public void levelThree(){
		setContentPane(empty);
		setContentPane(levelThree = new LevelThree());
		levelThree.requestFocus();
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

	/**
	 * Updates the background animation.
	 */
	public void updateCircles(){
		for (int i = 0; i < CIRCLES; i ++){
			if (x[i] + dx[i] < 0 || x[i] + dx[i] > Frame.preferredSize.width - DIAMETER)
				dx[i] = -dx[i];
			if (y[i] + dy[i] < 0 || y[i] + dy[i] > Frame.preferredSize.height - DIAMETER)
				dy[i] = -dy[i];
			x[i] += dx[i];
			y[i] += dy[i];
		}
		getContentPane().repaint();
	}
}