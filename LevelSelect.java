import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class LevelSelect extends Background{
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

		one.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "levelOne"));
		two.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "levelTwo"));
		three.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "levelThree"));
		back.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "back"));

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

	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}
}