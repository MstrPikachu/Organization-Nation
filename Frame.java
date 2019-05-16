import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	private Intro intro = new Intro();
	private MainMenu mainMenu = new MainMenu();
	private Settings settings = new Settings();
	public  static final Dimension preferredSize = new Dimension(640, 480);
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