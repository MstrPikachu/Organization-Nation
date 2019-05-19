import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JPanel{
	private JTextField username;
	private JPasswordField password, password2;
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

		Listener listener = new Listener();
		username.setActionCommand("Username");
		username.addActionListener(listener);
		password.setActionCommand("Password");
		password.addActionListener(listener);
		password2.setActionCommand("Password2");
		password2.addActionListener(listener);
		register.addActionListener(listener);
		back.addActionListener(listener);
		
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
	public void requestFocus(){
		username.requestFocusInWindow();
	}

	class Listener implements ActionListener{
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent ae){
			switch (ae.getActionCommand()){
				case "Back": Main.frame.back();
				case "Username": password.requestFocus(); break;
				case "Password": password2.requestFocus(); break;
				case "Password2":
				case "Register!": if (!password.getText().equals(password2.getText()))
							JOptionPane.showMessageDialog(Main.frame, "Your passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
						else if (User.register(username.getText(), password.getPassword()))
							Main.frame.mainMenu();
						break;
			}
		}
	}
}