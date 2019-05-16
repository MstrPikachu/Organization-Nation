import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel{
	private JTextField username;
	private JPasswordField password;
	public Login(){
		//set up panel
		super();
		GroupLayout layout = new GroupLayout(this);
		super.setLayout(layout);

		//declare components
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		username = new JTextField(15);
		password = new JPasswordField(15);
		JButton login = new JButton("Log In!");

		Listener listener = new Listener();
		username.setActionCommand("Username");
		username.addActionListener(listener);
		password.setActionCommand("Password");
		password.addActionListener(listener);
		login.addActionListener(listener);
		

		//set up layout
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup().addContainerGap(50, 50).
			addGroup(layout.createParallelGroup().addComponent(usernameLabel).addComponent(passwordLabel)).
			addGroup(layout.createParallelGroup().addComponent(username).addComponent(password).addComponent(login)).
			addContainerGap(50, 50));
		layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap(50, 50).
			addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(usernameLabel).addComponent(username)).
			addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(passwordLabel).addComponent(password)).
			addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(login)));
	}
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			switch (ae.getActionCommand()){
				case "Username": password.requestFocus(); break;
				case "Password":
				case "Log In!": if (Main.frame.authenticate(username.getText(), password.getPassword()))
							Main.frame.mainMenu();
						break;
			}
		}
	}
}