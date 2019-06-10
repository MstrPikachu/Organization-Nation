import javax.swing.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;

/**
 * A class meant to be a template for a level.
 * Default controls are drag-and-drop.
 * Contains useful helper methods to make the job easier.
 *
 * @author Peter Lin
 * @version 1.1
 */
public class Level extends JPanel{
	//current points the user has
	private int points;
	//the layout manager
	protected SpringLayout layout;
	//the game timer
	protected Timer timer;
	private JLabel pointsLabel;

	/**
	 * Creates a drag-and-drop level.
	 */
	protected Level(){
		//set up panel
		super();
		layout = new SpringLayout();
		super.setLayout(layout);

		//declare components
		JButton pause = new JButton("Pause");
		pointsLabel = new JLabel("0 Points");

		//add components
		super.add(pause);
		super.add(pointsLabel);
		pause.addActionListener(EventHandler.create(ActionListener.class, Main.frame, "pause"));

		//set up layout
		layout.putConstraint(SpringLayout.NORTH, pause, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, pause, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, pointsLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, pointsLabel, 20, SpringLayout.WEST, this);
	}

	/**
	 * Adds the specified number of points to the user.
	 * Points will always be non-negative.
	 *
	 * @param points the number of points to add
	 */
	public void addPoints(int points){
		this.points = -points > this.points ? 0 : this.points + points;
		pointsLabel.setText(this.points == 1 ? "1 Point" : this.points + " Points");
	}

	/**
	 * Returns the amount of points the user has.
	 *
	 * @return the amount of points the user has.
	 */
	public int getPoints(){
		return points;
	}

	/**
	 * Hides the user's points.
	 */
	public void hidePoints(){
		pointsLabel.setText("");
	}

	/**
	 * Shuffles an array of objects to a random permutation.
	 *
	 * @param arr the array to be shuffled.
	 */
	public void shuffle(Object[] arr){
		for (int i = arr.length - 1; i > 1; i -= 1){
			int index = (int)(Math.random() * i);
			Object temp = arr[i];
			arr[i] = arr[index];
			arr[index] = temp;
		}
	}


	/**
	 * Makes a component mouse-draggable.
	 * Component should be set with SpringLayout and be bound using its north and west edges.
	 *
	 * @param component the component to make mouse-draggable.
	 */
	public void setDraggable(Component component){
		MouseListener listener = new MouseListener(component);
		component.addMouseListener(listener);
		component.addMouseMotionListener(listener);
	}

	private class MouseListener extends MouseAdapter{
		//coordinates of mouse relative to component
		private int x, y;
		//change in mouse coordinates as mouse moves
		private int dx, dy;
		//component that this class controls
		private int thisX, thisY;
		private Component component;
		private MouseListener(Component component){
			this.component = component;
		}

		@Override
		public void mousePressed(MouseEvent e){
			x = e.getXOnScreen();
			y = e.getYOnScreen();
			thisX = layout.getConstraint(SpringLayout.WEST, component).getValue();
			thisY = layout.getConstraint(SpringLayout.NORTH, component).getValue();
		}

		@Override
		public void mouseDragged(MouseEvent e){
			dx = e.getXOnScreen() - x;
			dy = e.getYOnScreen() - y;
			if (thisX + dx >= 0 && thisX + dx <= Frame.preferredSize.width - component.getWidth()){
				layout.putConstraint(SpringLayout.WEST, component, thisX = thisX + dx, SpringLayout.WEST, Level.this);
			}
			if (thisY + dy >= 0 && thisY + dy <= Frame.preferredSize.height - component.getHeight()){
				layout.putConstraint(SpringLayout.NORTH, component, thisY = thisY + dy, SpringLayout.NORTH, Level.this);
			}
			x += dx;
			y += dy;
			paintImmediately(thisX, thisY, component.getWidth(), component.getHeight());
			revalidate();
		}
	}

	@Override
	public java.awt.Dimension getPreferredSize(){
		return Frame.preferredSize;
	}
}