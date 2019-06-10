import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.EventHandler;

/**
 * This stores the contents of Level 3.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class LevelThree extends Level implements Timed{
	//Level instructions
	private static final String instructions = "<html><body width='400'>You have arrived at the last level. A text box with content for a specific school subject will appear. There will be a lot of bins representing different subjects, at the bottom of the screen. You need to quickly organize the boxes into their respective bins, by using the arrow keys and the space bar. Right answers get you 1 point, wrong answers give you no points. This last test will get you to implement the skills you have learned so you can go back into real life able to sort their own items effectively. You got this!</body></html>";

	//bin coordinates
	private int x, y;

	//Timer to move the text
	private Timer moveDown = new Timer (250, null);
	private JTextField focuser = new JTextField();

	private String text[] = new String[]{"<html><center>The Periodic<br>Table", "Shakespeare", "<html><center>How To Use<br>Sine Law To<br>Solve Triangles", "<html><center>Canada's<br>Regions", "Atoms Worksheet", "Book Report", "<html><center>Intro to<br>Long Division", "<html><center>Provincial<br>Capitals", "<html><center>The Basics of<br>the Respiratory<br>System", "<html><center>Essay<br>Writing Tips", "<html><center>Geometry<br>Quiz", "<html><center>World<br>Countries"};
	private int[] answers = new int[]{0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3};
	private TextBox box = new TextBox("");
	private TextBox bins[] = new TextBox[]{new TextBox("Science", 135, 70), new TextBox("English", 135, 70), new TextBox("Math", 135, 70), new TextBox("Geography", 135, 70)};
	private int textNumber;
	public LevelThree(){
		//set up panel
		super();

		shuffle();
		box.setText(text[0]);

		//add components
		super.add(box);
		super.add(bins[0]);
		super.add(bins[1]);
		super.add(bins[2]);
		super.add(bins[3]);
		super.add(focuser);
		focuser.addKeyListener(EventHandler.create(KeyListener.class, this, "keyPressed", "", "keyPressed"));

		//set up layout
		layout.putConstraint(SpringLayout.SOUTH, focuser, 0, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.SOUTH, box, y = 60, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, box, x = (Frame.preferredSize.width - box.getPreferredSize().width) / 2, SpringLayout.WEST, this);

		layout.putConstraint(SpringLayout.WEST, bins[0], 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, bins[0], -30, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.WEST, bins[1], 175, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, bins[1], -30, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.WEST, bins[2], 330, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, bins[2], -30, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.WEST, bins[3], 485, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, bins[3], -30, SpringLayout.SOUTH, this);

		//set up timers
		moveDown.addActionListener(EventHandler.create(ActionListener.class, this, "moveDown"));
		JOptionPane.showMessageDialog(Main.frame, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
		moveDown.start();
	}

	public void requestFocus(){
		focuser.requestFocus();
	}

	public void shuffle(){
		for (int i = text.length - 1; i > 1; i -= 1){
			int index = (int)(Math.random() * i);
			String temp = text[i];
			text[i] = text[index];
			text[index] = temp;
			int temp2 = answers[i];
			answers[i] = answers[index];
			answers[index] = temp2;
		}
	}

	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
				if (x < Frame.preferredSize.width - box.getPreferredSize().width - 9)
					layout.putConstraint(SpringLayout.WEST, box, x = x + 20, SpringLayout.WEST, this);
				repaint();
				revalidate();
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
				if (x > 9)
					layout.putConstraint(SpringLayout.WEST, box, x = x - 20, SpringLayout.WEST, this);
				repaint();
				revalidate();
				break;
			case KeyEvent.VK_SPACE:
				layout.putConstraint(SpringLayout.SOUTH, box, 300, SpringLayout.NORTH, this);
				repaint();
				revalidate();
				checkItem();
		}
	}

	public void moveDown(){
		if (y == 300){
			checkItem();
			return;
		}
		layout.putConstraint(SpringLayout.SOUTH, box, y = y + 10, SpringLayout.NORTH, this);
		repaint();
		revalidate();
	}

	public void checkItem(){
		boolean correct = x > answers[textNumber] * 155 && x + box.getPreferredSize().width < answers[textNumber] * 155 + 175;
		System.out.printf("%s %s\n", x > answers[textNumber] * 155, x + box.getPreferredSize().width < answers[textNumber] * 155 + 175);
		if (correct){
			addPoints(1);
		}
		if (textNumber < 11){
			box.setText(text[textNumber = textNumber + 1]);
			layout.putConstraint(SpringLayout.SOUTH, box, y = 60, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.WEST, box, x = (Frame.preferredSize.width - box.getPreferredSize().width) / 2, SpringLayout.WEST, this);
			repaint();
			revalidate();
		}
		else{
			moveDown.stop();
			JOptionPane.showMessageDialog(Main.frame, "You completed the level with " + getPoints() + " points.", "Level Complete", JOptionPane.PLAIN_MESSAGE);
			Main.frame.mainMenu();
		}
	}

	@Override
	public Timer getTimer(){
		return moveDown;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(128, 128, 128, 128));
		g.fillRect(x, 300 - box.getPreferredSize().height, box.getPreferredSize().width, box.getPreferredSize().height);
	}
}