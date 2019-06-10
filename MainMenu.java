import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.EventHandler;

/**
 * The main menu panel.
 *
 * @author Peter Lin
 * @version 1.2
 */

public class MainMenu extends JPanel implements Timed {
	private Timer timer = new Timer(50, null);
	private static final int CIRCLES = 9;
	private static final int DIAMETER = 150;
	//x, y, and change in x, y values for each circle in the background
	private double[] x = new double[CIRCLES];
	private double[] y = new double[CIRCLES];
	private double[] dx = new double[CIRCLES];
	private double[] dy = new double[CIRCLES];
	private Color[] colors = new Color[CIRCLES];
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
		levelSelect.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "levelSelect"));
		settings.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "settings"));

		//put constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, user, -10, SpringLayout.EAST, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, levelSelect, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, levelSelect, -50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, settings, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, settings, 50, SpringLayout.VERTICAL_CENTER, this);

		timer.addActionListener(EventHandler.create(ActionListener.class, this, "updateCircles"));
		timer.start();

		//make colors and initialize coordinate values
		for (int i = 0; i < CIRCLES; i ++){
			double angle = Math.PI / CIRCLES * i;
			colors[i] = new Color ((int) (127 * Math.sin (angle) + 128), (int) (127 * Math.sin (angle + Math.PI * 2 / 3) + 128), (int) (127 * Math.sin (angle + Math.PI * 4 / 3) + 128), 128);
			x[i] = Math.random() * (Frame.preferredSize.width - DIAMETER);
			y[i] = Math.random() * (Frame.preferredSize.height - DIAMETER);
			dx[i] = Math.random() * -2 + 4;
			dy[i] = Math.random() * -2 + 4;
		}
	}
	@Override
	public Timer getTimer(){
		return timer;
	}

	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}

	public void updateCircles(){
		for (int i = 0; i < CIRCLES; i ++){
			if (x[i] + dx[i] < 0 || x[i] + dx[i] > Frame.preferredSize.width - DIAMETER)
				dx[i] = -dx[i];
			if (y[i] + dy[i] < 0 || y[i] + dy[i] > Frame.preferredSize.height - DIAMETER)
				dy[i] = -dy[i];
			x[i] += dx[i];
			y[i] += dy[i];
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < CIRCLES; i ++){
			g.setColor(colors[i]);
			g.fillOval((int)x[i], (int)y[i], DIAMETER, DIAMETER);
		}
	}
}