import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * This stores the contents of the first level.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class LevelOne extends JPanel{
	//coordinates of mouse relative to text box
	private int x, y;
	//change in mouse coordinates
	private int dx, dy;
	//coordinates of text box relative to this JPanel
	private int thisX, thisY;
	//max coordinates of text box without going offscreen
	private int maxX, maxY;

	private SpringLayout layout = new SpringLayout();
	TextBox box, organization, other;
	MouseAdapter adapter = new MouseAdapter();
	public LevelOne(){
		//set up panel
		super();
		super.setLayout(layout);

		//declare components
		box = new TextBox("Test");
		organization = new TextBox("Organization", Frame.preferredSize.width / 2 - 40, box.getPreferredSize().height);
		other = new TextBox("Other", Frame.preferredSize.width / 2 - 40, box.getPreferredSize().height);

		maxX = Frame.preferredSize.width - box.getPreferredSize().width;
		maxY = Frame.preferredSize.height - box.getPreferredSize().height;
		thisX = maxX / 2;
		thisY = maxY / 2;

		//add components
		super.add(box);
		super.add(organization);
		super.add(other);
		organization.setPreferredSize(null);
		other.setPreferredSize(null);
		box.addMouseListener(adapter);
		box.addMouseMotionListener(adapter);
		layout.putConstraint(SpringLayout.WEST, box, thisX, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, box, thisY, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, organization, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, organization, -20, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.EAST, other, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, other, -20, SpringLayout.SOUTH, this);
	}

	@Override
	public Dimension getPreferredSize(){
		return Frame.preferredSize;
	}
	private class MouseAdapter extends java.awt.event.MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){
			x = e.getXOnScreen();
			y = e.getYOnScreen();
		}
		public void mouseReleased(MouseEvent e){
			//check if it has been sorted
		}

		@Override
		public void mouseDragged(MouseEvent e){
			dx = e.getXOnScreen() - x;
			dy = e.getYOnScreen() - y;
			if (thisX + dx >= 0 && thisX + dx <= maxX){
				layout.putConstraint(SpringLayout.WEST, box, thisX = thisX + dx, SpringLayout.WEST, LevelOne.this);
				x += dx;
			}
			if (thisY + dy >= 0 && thisY + dy <= maxY){
				layout.putConstraint(SpringLayout.NORTH, box, thisY = thisY + dy, SpringLayout.NORTH, LevelOne.this);
				y += dy;
			}
			paintImmediately(thisX, thisY, 0, 0);
			revalidate();
		}
	}
}