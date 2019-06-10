import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SpringLayout;
import java.beans.EventHandler;

/**
 * The settings panel.
 *
 * @author Peter Lin
 * @version 1.5
 */
public class Settings extends JPanel{

	/**
	 * The class constructor.
	 * Should only be called after a user is logged in.
	 */
	public Settings(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel title = new JLabel("Settings");
		JButton controls = new JButton("Controls");
		JButton logout = new JButton("Log Out");
		JButton back = new JButton("Back");

		//add components
		super.add(title);
		super.add(controls);
		super.add(logout);
		super.add(back);
		logout.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "logout"));
		back.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "back"));

		//set up layout
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, controls, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, controls, -50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logout, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, logout, 50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, back, -10, SpringLayout.SOUTH, this);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//add drawings later
	}
	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}
}