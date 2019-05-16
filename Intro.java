import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Intro extends JPanel{
	private BufferedImage content;
	private int xRadius, yRadius, i = -50;
	private double angle;
	public Intro(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JLabel title = new JLabel("Organization Nation", SwingConstants.CENTER);
		JButton mainMenu = new JButton("Main Menu");

		//add components
		super.add(title);
		super.add(mainMenu);
		mainMenu.addActionListener(Listener.LISTENER);

		//set up layout
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenu, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainMenu, 0, SpringLayout.VERTICAL_CENTER, this);

		//finish panel
		//super.setOpaque(true);
		//super.setVisible(true);

		//set up content
		content = new BufferedImage(Frame.preferredSize.width, Frame.preferredSize.height, BufferedImage.TYPE_INT_ARGB);
	}
	public void splashScreen(){
		final Timer timer = new Timer(10, null);
		Graphics2D g = content.createGraphics();
		timer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				angle = i++ % 360 * Math.PI / 180; // Trigonometry and math functions source: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
				xRadius = (int) Math.round (50 * Math.cos (angle));
				yRadius = (int) Math.round (50 * Math.sin (angle));
				g.setColor (new Color ((int) (127 * Math.sin (angle) + 128), (int) (127 * Math.sin (angle + Math.PI * 2 / 3) + 128), (int) (127 * Math.sin (angle + Math.PI * 4 / 3) + 128)));
				g.fillOval (i - xRadius - 5, 300 - yRadius - 50, 10, 10);
				g.fillOval (i + xRadius - 5, 300 + yRadius - 50, 10, 10);
				g.fillOval (i - yRadius - 5, 300 + xRadius - 50, 10, 10);
				g.fillOval (i + yRadius - 5, 300 - xRadius - 50, 10, 10);
				repaint();
				if (i > 700)
					timer.stop();
			}
		});
		timer.start();
	}	


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(content, 0, 0, this);
	}
}