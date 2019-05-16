import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Intro extends JPanel{
	JButton mainMenu;
	private int xRadius, yRadius, i = -50;
	private double angle;
	public Intro(){
		super();
		super.setOpaque(true);
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);
		JLabel title = new JLabel("Organization Nation", SwingConstants.CENTER);
		mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(Listener.LISTENER);

		super.add(title);
		super.add(mainMenu);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenu, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainMenu, 0, SpringLayout.VERTICAL_CENTER, this);

		super.setVisible(true);
		System.out.println("Done!");
	}
	public void splashScreen(){
		Timer timer = new Timer(10, new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				angle = i++ % 360 * Math.PI / 180; // Trigonometry and math functions source: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
				xRadius = (int) Math.round (50 * Math.cos (angle));
				yRadius = (int) Math.round (50 * Math.sin (angle));
				repaint (i - xRadius - 5, 300 - yRadius - 55, i - xRadius + 5, 300 - yRadius - 45);
				repaint (i + xRadius - 5, 300 + yRadius - 55, i + xRadius + 5, 300 + yRadius - 45);
				repaint (i - yRadius - 5, 300 + xRadius - 55, i - xRadius + 5, 300 + yRadius - 45);
				repaint(i + yRadius - 5, 300 - xRadius - 55, i + xRadius + 5, 300 - yRadius - 45);
			}
		});
		timer.start();
	}	


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor (new Color ((int) (127 * Math.sin (angle) + 128), (int) (127 * Math.sin (angle + Math.PI * 2 / 3) + 128), (int) (127 * Math.sin (angle + Math.PI * 4 / 3) + 128)));
		g.fillOval (i - xRadius - 5, 300 - yRadius - 50, 10, 10);
		g.fillOval (i + xRadius - 5, 300 + yRadius - 50, 10, 10);
		g.fillOval (i - yRadius - 5, 300 + xRadius - 50, 10, 10);
		g.fillOval (i + yRadius - 5, 300 - xRadius - 50, 10, 10);
		System.out.println(i);
	}
}