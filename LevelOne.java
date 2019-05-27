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
	TextBox box;
	MouseAdapter adapter = new MouseAdapter();
	public LevelOne(){
		//set up panel
		super();
		super.setLayout(layout);

		//declare components
		box = new TextBox("Test");

		maxX = Frame.preferredSize.width - box.getPreferredSize().width;
		maxY = Frame.preferredSize.height - box.getPreferredSize().height;
		thisX = maxX / 2;
		thisY = maxY / 2;

		//add components
		super.add(box);
		box.addMouseListener(adapter);
		box.addMouseMotionListener(adapter);
		layout.putConstraint(SpringLayout.WEST, box, thisX, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, box, thisY, SpringLayout.NORTH, this);
	}

	public void changeBox(TextBox box){
		remove(this.box);
		add(this.box = box);
		maxX = 320 - box.getPreferredSize().width / 2;
		maxY = 320 - box.getPreferredSize().height / 2;
		thisX = maxX / 2;
		thisY = maxY / 2;
		box.addMouseListener(adapter);
		box.addMouseMotionListener(adapter);
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

		@Override
		public void mouseDragged(MouseEvent e){
			dx = e.getXOnScreen() - x;
			x += dx;
			dy = e.getYOnScreen() - y;
			y += dy;
			layout.putConstraint(SpringLayout.WEST, box, thisX = Math.min(Math.max(0, thisX + dx), maxX), SpringLayout.WEST, LevelOne.this);
			layout.putConstraint(SpringLayout.NORTH, box, thisY = Math.min(Math.max(0, thisY + dy), maxY), SpringLayout.NORTH, LevelOne.this);
			repaint();
			revalidate();
		}
	}
}