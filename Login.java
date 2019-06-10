import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

/**
 * The login menu.
 * This class is a GUI that takes the username and password and logs the user in if they are correct.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class Login extends JPanel{
	// The text field for the username.
	private JTextField username;
	// The text field for the password.
	private JPasswordField password;

	/**
	 * The class constructor.
	 */
	public Login(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		username = new JTextField(15);
		password = new JPasswordField();
		JButton login = new JButton("Log In!");
		JButton back = new JButton("Back");

		//add components
		super.add(usernameLabel);
		super.add(passwordLabel);
		super.add(username);
		super.add(password);
		super.add(login);
		super.add(back);

		username.addActionListener(EventHandler.create(ActionListener.class, password, "requestFocus"));
		ActionListener loginListener = EventHandler.create(ActionListener.class, this, "login");
		password.addActionListener(loginListener);
		login.addActionListener(loginListener);
		back.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "back"));
		
		//set up layout
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usernameLabel, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, usernameLabel, 50, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, username, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, username, usernameLabel.getPreferredSize().width + 25, SpringLayout.WEST, usernameLabel);
		layout.putConstraint(SpringLayout.EAST, username, -50, SpringLayout.EAST, this);

		layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLabel, 25, SpringLayout.VERTICAL_CENTER, usernameLabel);
		layout.putConstraint(SpringLayout.WEST, passwordLabel, 50, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, password, 25, SpringLayout.VERTICAL_CENTER, username);
		layout.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, username);
		layout.putConstraint(SpringLayout.EAST, password, -50, SpringLayout.EAST, this);

		layout.putConstraint(SpringLayout.WEST, login, 0, SpringLayout.WEST, password);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, login, 25, SpringLayout.VERTICAL_CENTER, password);

		layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, back, -10, SpringLayout.SOUTH, this);
	}

	/**
	 * Attempts to set the cursor to the username text field.
	 */
	public void requestFocus(){
		username.requestFocusInWindow();
	}
	public void login(){
		if (User.login(username.getText(), password.getPassword()))
			Main.frame.mainMenu();
		else
			JOptionPane.showMessageDialog(Main.frame, "Your username or password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
	}


	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}
}