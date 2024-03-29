import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

/**
 * The register menu for registering new users.
 *
 * @author Peter Lin
 * @version 1.8
 */

public class Register extends Background {
	// The username text field.
	private JTextField username;
	// The password text fields.
	private JPasswordField password, password2;

	/**
	 * The class constructor.
	 */
	public Register(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		JLabel passwordLabel2 = new JLabel("<html>Confirm<br>Password</html>");
		username = new JTextField(15);
		password = new JPasswordField();
		password2 = new JPasswordField();
		JButton register = new JButton("Register!");
		JButton back = new JButton("Back");

		//add components
		super.add(usernameLabel);
		super.add(passwordLabel);
		super.add(passwordLabel2);
		super.add(username);
		super.add(password);
		super.add(password2);
		super.add(register);
		super.add(back);

		//add actionlisteners
		username.addActionListener((ActionListener)EventHandler.create(ActionListener.class, password, "requestFocus"));
		password.addActionListener((ActionListener)EventHandler.create(ActionListener.class, password2, "requestFocus"));
		ActionListener registerListener = (ActionListener)EventHandler.create(ActionListener.class, this, "register");
		password2.addActionListener(registerListener);
		register.addActionListener(registerListener);
		back.addActionListener((ActionListener)EventHandler.create(ActionListener.class, Main.frame, "back"));
		
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

		layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLabel2, 30, SpringLayout.VERTICAL_CENTER, passwordLabel);
		layout.putConstraint(SpringLayout.WEST, passwordLabel2, 50, SpringLayout.WEST, this);

		layout.putConstraint(SpringLayout.VERTICAL_CENTER, password2, 0, SpringLayout.VERTICAL_CENTER, passwordLabel2);
		layout.putConstraint(SpringLayout.WEST, password2, 0, SpringLayout.WEST, password);
		layout.putConstraint(SpringLayout.EAST, password2, -50, SpringLayout.EAST, this);

		layout.putConstraint(SpringLayout.WEST, register, 0, SpringLayout.WEST, password);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, register, 25, SpringLayout.VERTICAL_CENTER, password);

		layout.putConstraint(SpringLayout.WEST, register, 0, SpringLayout.WEST, password2);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, register, 25, SpringLayout.VERTICAL_CENTER, password2);

		layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, back, -10, SpringLayout.SOUTH, this);
	}

	/**
	 * Attempts to set the focus in the username text field.
	 */
	public void requestFocus(){
		username.requestFocusInWindow();
	}

	@SuppressWarnings("deprecation")
	public void register(){
		if (!password.getText().equals(password2.getText()))
			JOptionPane.showMessageDialog(Main.frame, "Your passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
		else if (User.register(username.getText(), password.getPassword()))
			Main.frame.mainMenu();
		password.setText("");
		password2.setText("");
	}

	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}
}