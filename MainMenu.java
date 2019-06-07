import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The main menu panel.
 *
 * @author Peter Lin
 * @version 1.2
 */

public class MainMenu extends JPanel implements Timed {
	private Timer timer = new Timer(100, null);
	private int n;
	/**
	 * The class constructor.
	 * Must only be called after a valid user is logged in.
	 */
	public MainMenu() {
		//initialize JPanel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel title = new JLabel("Main Menu");
		JLabel user = new JLabel("Welcome, " + User.getUsername());
		JButton levelSelect = new JButton("Level Select");
		JButton settings = new JButton("Settings");

		//add components
		super.add(title);
		super.add(user);
		super.add(levelSelect);
		super.add(settings);

		//add action listeners
		levelSelect.addActionListener(Listener.LISTENER);
		settings.addActionListener(Listener.LISTENER);

		//put constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, user, -10, SpringLayout.EAST, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelSelect, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, levelSelect, -50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, settings, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, settings, 50, SpringLayout.VERTICAL_CENTER, this);

		timer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if (n == 39)
					n = 0;
				else
					n += 1;
				repaint();
			}
		});
		timer.start();
	}
	@Override
	public Timer getTimer(){
		return timer;
	}

	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		/*
		g.setColor(Color.RED);
		for (int i = 0; i < 20; i ++){
			g.drawLine(n + i - 80, 0, n + i - 40, 400);
			g.drawLine(n + i - 40, 0, n + i, 400);
			g.drawLine(n + i, 0, n + i + 40, 400);
			g.drawLine(n + i + 40, 0, n + i + 80, 400);
			g.drawLine(n + i + 80, 0, n + i + 120, 400);
			g.drawLine(n + i + 120, 0, n + i + 160, 400);
			g.drawLine(n + i + 160, 0, n + i + 200, 400);
			g.drawLine(n + i + 200, 0, n + i + 240, 400);
			g.drawLine(n + i + 240, 0, n + i + 280, 400);
			g.drawLine(n + i + 280, 0, n + i + 320, 400);
			g.drawLine(n + i + 320, 0, n + i + 360, 400);
			g.drawLine(n + i + 360, 0, n + i + 400, 400);
			g.drawLine(n + i + 400, 0, n + i + 440, 400);
			g.drawLine(n + i + 440, 0, n + i + 480, 400);
			g.drawLine(n + i + 480, 0, n + i + 520, 400);
			g.drawLine(n + i + 520, 0, n + i + 560, 400);
			g.drawLine(n + i + 560, 0, n + i + 600, 400);
			g.drawLine(n + i + 600, 0, n + i + 640, 400);
		}
		*/
	}
}