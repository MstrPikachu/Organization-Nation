import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;

/**
 * The introduction panel for the that has a custom animation.
 *
 * @author Peter Lin
 * @version 1.4
 */

public class Intro extends Background implements Timed{
	private Timer timer = new Timer(50, Main.frame.backgroundListener);

	/**
	 * The class constructor.
	 */
	public Intro(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel title = new JLabel("Organization Nation");
		JButton login = new JButton("Log In");
		JButton register = new JButton("Register");

		//add components
		super.add(title);
		super.add(login);
		super.add(register);
		login.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "login"));
		register.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "register"));

		//set up layout
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, login, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, login, -50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, register, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, register, 50, SpringLayout.VERTICAL_CENTER, this);
	}

	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}

	@Override
	public Timer getTimer(){
		return timer;
	}
}