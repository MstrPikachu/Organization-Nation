import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel {
	public MainMenu() {
		//initialize JPanel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel title = new JLabel("Main Menu");
		JButton levelSelect = new JButton("Level Select");
		JButton settings = new JButton("Settings");

		//add components
		super.add(title);
		super.add(levelSelect);
		super.add(settings);

		//add action listeners
		levelSelect.addActionListener(Listener.LISTENER);
		settings.addActionListener(Listener.LISTENER);

		//put constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelSelect, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, levelSelect, -50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, settings, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, settings, 50, SpringLayout.VERTICAL_CENTER, this);
	}
}