import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;


/**
 * A menu with a translucent background designed to pause the game.
 * Blocks mouse events so the game is truly frozen while paused.
 */
public class PauseMenu extends JPanel{

	/**
	 * The class constructor.
	 */
	public PauseMenu(){
		//set up panel
		super();
		SpringLayout layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JButton resume = new JButton("Resume");
		JButton mainMenu = new JButton("Main Menu");
		resume.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "resume"));
		mainMenu.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "mainMenu"));

		super.add(resume);
		super.add(mainMenu);

		//set up layout
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, resume, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, resume, -25, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenu, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainMenu, 25, SpringLayout.VERTICAL_CENTER, this);

		//disable mouse while pause is up
		addMouseListener(EventHandler.create(MouseListener.class, this, "consume", ""));
	}

	/**
	 * Consumes a mouse event.
	 *
	 * @param e the event to be consumed.
	 */
	public void consume(MouseEvent e){
		e.consume();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.83f));
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, Frame.preferredSize.width, Frame.preferredSize.height);
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
}