import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	private JPanel intro = new JPanel();
	private static final Dimension preferredSize = new Dimension(640, 480);
	public Frame(){
		//set up JFrame
		super("Organization Nation");

		//set up intro screen
		JLabel title = new JLabel("Organization Nation", SwingConstants.CENTER);
		intro.setLayout(new BorderLayout());
		intro.add(title, BorderLayout.CENTER);
		intro.setBackground(new Color(255, 0, 0));
		

		super.setVisible(true);
		super.pack();

	}
	public void intro(){
		super.setContentPane(intro);
	}

	@Override
	public Dimension getPreferredSize(){
		return preferredSize;
	}
}