import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.EventHandler;

/**
 * This stores the contents of Level 2.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class LevelTwo extends Level implements Timed{
	//level instructions
	private static final String instructions = "<html><body width='400'>The second level will allow you to overcome adversity of organizing. Many teens with bad organizational skills will have poorly organized school binders. This level will give you 15 seconds to order seven sheets alphabetically. Then you have to click the finished button. There will be more points for finishing faster, however if you click the finished button and you have organized the boxes incorrectly, you will lose and get a score of 0. You will also get a 0 if the timer runs out. You can do this!</body></html>";

	//items to sort
	private TextBox[] items = new TextBox[7];
	//sorting number
	private JButton[] number = new JButton[7];
	//Timer to see how long the user takes
	Timer timer = new Timer(1, null);
	private int time = 15000; //15000 ms = 15 s
	JLabel timeLeft = new JLabel("15.000 seconds");

	private String[] text = new String[]{"<html><center>Book<br>Reflection</center></html>", "Chemistry", "<html><center>Civics<br>Assignment</center><html>", "Essay", "ICS Plan", "<html><center>Les Verbes<br>Français</center></html>", "Math Test"};
	private int[] order = new int[]{'1', '2', '3', '4', '5', '6', '7'};

	public LevelTwo(){
		//set up panel
		super();
		hidePoints();

		//declare components
		JButton finished = new JButton("Finished!");
		finished.addActionListener(EventHandler.create(ActionListener.class, this, "check"));
		super.add(finished);
		super.add(timeLeft);


		layout.putConstraint(SpringLayout.SOUTH, finished, -20, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, finished, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, timeLeft, -20, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, timeLeft, -20, SpringLayout.EAST, this);


		ActionListener listener = EventHandler.create(ActionListener.class, this, "handle", "");
		//lay out components
		shuffle();
		for (int i = 0; i < 7; i ++){
			items[i] = new TextBox(text[i]);
			number[i] = new JButton();
			super.add(items[i]);
			super.add(number[i]);
			number[i].addActionListener(listener);
			if (i == 0){
				layout.putConstraint(SpringLayout.WEST, items[i], 20, SpringLayout.WEST, this);
			}
			else{
				layout.putConstraint(SpringLayout.WEST, items[i], 20, SpringLayout.EAST, items[i - 1]);
			}
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, items[i], 80, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, number[i], 0, SpringLayout.HORIZONTAL_CENTER, items[i]);
			layout.putConstraint(SpringLayout.NORTH, number[i], 120, SpringLayout.NORTH, this);
		}

		timer.addActionListener(EventHandler.create(ActionListener.class, this, "update"));
		//Explain instructions to the user
		JOptionPane.showMessageDialog(Main.frame, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
		timer.start();
	}

	public void handle(ActionEvent ae){
		timer.stop();
		String str = JOptionPane.showInputDialog(Main.frame, "What order does this item go in?", "Item sorting", JOptionPane.QUESTION_MESSAGE);
		if (str == null)
			str = "";
		timer.start();
		if (str.length() != 1 || str.charAt(0) < '1' || str.charAt(0) > '7'){
			JOptionPane.showMessageDialog(Main.frame, "Please enter a number from 1-7.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			((JButton)(ae.getSource())).setText(str);
		}
	}

	public void shuffle(){
		for (int i = text.length - 1; i > 1; i -= 1){
			int index = (int)(Math.random() * i);
			String temp = text[i];
			text[i] = text[index];
			text[index] = temp;
			int temp2 = order[i];
			order[i] = order[index];
			order[index] = temp2;
		}
	}

	public void update(){
		timeLeft.setText(String.format("%.3f seconds", time / 1000f)); 
		if (time == 0){
			timer.stop();
			JOptionPane.showMessageDialog(Main.frame, "Sorry, you ran out of time.", "You Lose", JOptionPane.PLAIN_MESSAGE);
			Main.frame.mainMenu();
			return;
		}
		time -= 1;
		repaint(timeLeft.getBounds());
	}

	@Override
	public Timer getTimer(){
		return timer;
	}

	public void check(){
		timer.stop();
		for (int i = 0; i < 7; i ++){
			if (number[i].getText().charAt(0) != order[i]){
				JOptionPane.showMessageDialog(Main.frame, "Sorry, you sorted the items incorrectly.", "You Lose", JOptionPane.PLAIN_MESSAGE);
				Main.frame.mainMenu();
				break;
			}
		}
		JOptionPane.showMessageDialog(Main.frame, "Congratulations, you sorted the items correctly!", "You Win", JOptionPane.PLAIN_MESSAGE);
		Main.frame.mainMenu();
	}
}