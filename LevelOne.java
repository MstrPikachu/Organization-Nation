import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This stores the contents of the first level.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class LevelOne extends JPanel{
	private int x, y;
	private int dx, dy;
	private int thisX, thisY;
	private SpringLayout layout = new SpringLayout();
	public LevelOne(){
		//set up panel
		super();
		super.setLayout(layout);

		//declare components
		TextBox box = new TextBox("Test");

		//add components
		super.add(box);
		box.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				x = e.getXOnScreen();
				y = e.getYOnScreen();
			}
		});
		box.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				dx = e.getXOnScreen() - x;
				x += dx;
				dy = e.getYOnScreen() - y;
				y += dy;
				System.out.printf("%d %d\n", dx, dy);
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, box, thisX = (thisX + dx), SpringLayout.HORIZONTAL_CENTER, LevelOne.this);
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, box, thisY = (thisY + dy), SpringLayout.VERTICAL_CENTER, LevelOne.this);
				repaint();
			}
		});
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, box, thisX = thisX + dx, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, box, thisY = thisY + dy, SpringLayout.VERTICAL_CENTER, this);
	}
}