import javax.swing.*;
import java.awt.*;

public class LevelSelect extends JPanel{
	public LevelSelect(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel title = new JLabel("Level Select");
		JButton one = new JButton("Level 1");
		JButton two = new JButton("Level 2");
		JButton three = new JButton("Level 3");
		JButton back = new JButton("Back");

		//add components
		super.add(title);
		super.add(one);
		super.add(two);
		super.add(three);
		super.add(back);

		one.addActionListener(Listener.LISTENER);
		back.addActionListener(Listener.LISTENER);

		//set up layout
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, one, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, one, -50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, two, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, two, 0, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, three, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, three, 50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, back, -10, SpringLayout.SOUTH, this);
	}
}