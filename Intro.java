import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * The introduction panel for the that has a custom animation.
 *
 * @author Peter Lin
 * @version 1.4
 */

public class Intro extends JPanel{
	/** The background to be drawn to the JPanel. */
	private BufferedImage content = new BufferedImage(Frame.preferredSize.width, Frame.preferredSize.height, BufferedImage.TYPE_INT_ARGB);
	/** The x- and y-components of the radius used by the animation */
	private int xRadius, yRadius;
	/** The starting position of the animation */
	private int i = -50;
	/** The angle of part of the animation */
	private double angle;

	/**
	 * The class constructor.
	 */
	public Intro(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel title = new JLabel("Organization Nation");
		JButton login = new JButton("Log In");
		JButton register = new JButton("Register");

		//add components
		super.add(title);
		super.add(login);
		super.add(register);
		login.addActionListener(Listener.LISTENER);
		register.addActionListener(Listener.LISTENER);

		//set up layout
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, login, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, login, -50, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, register, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, register, 50, SpringLayout.VERTICAL_CENTER, this);
	}

	/**
	 * The introductory animation to be displayed.
	 */
	public void splashScreen(){
		Graphics2D g = content.createGraphics();
		final Timer timer = new Timer(10, null);
		timer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				angle = i++ % 360 * Math.PI / 180; // Trigonometry and math functions source: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
				xRadius = (int) Math.round (50 * Math.cos (angle));
				yRadius = (int) Math.round (50 * Math.sin (angle));
				g.setColor (new Color ((int) (127 * Math.sin (angle) + 128), (int) (127 * Math.sin (angle + Math.PI * 2 / 3) + 128), (int) (127 * Math.sin (angle + Math.PI * 4 / 3) + 128)));
				g.fillOval (i - xRadius - 5, 250 - yRadius, 10, 10);
				g.fillOval (i + xRadius - 5, 250 + yRadius, 10, 10);
				g.fillOval (i - yRadius - 5, 250 + xRadius, 10, 10);
				g.fillOval (i + yRadius - 5, 250 - xRadius, 10, 10);
				repaint(0, i - 55, 200, 110, 110);
				if (i > 700){
					timer.stop();
					g.dispose();
				}
			}
		});
		timer.start();
	}	

	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(content, 0, 0, null);
	}
}