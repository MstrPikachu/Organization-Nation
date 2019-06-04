import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;

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
	//loop counter
	private int i;

	private SpringLayout layout = new SpringLayout();
	private TextBox box, organization, other;
	private MouseAdapter adapter = new MouseAdapter();

	//If the text box is about organization or not
	private boolean isOrganization;
	//If the mouse is in the panel
	private boolean inPanel;

	private Polygon checkmark, xmark;
	BufferedImage checkImage, xImage, content;
	public LevelOne(){
		//set up panel
		super();
		super.setLayout(layout);

		//declare components
		box = new TextBox("Test");
		organization = new TextBox("Organization");
		other = new TextBox("Other");

		//initialize variables
		maxX = Frame.preferredSize.width - box.getPreferredSize().width;
		maxY = Frame.preferredSize.height - box.getPreferredSize().height;
		thisX = maxX / 2;
		thisY = maxY / 2;
		checkmark = new Polygon(new int[]{240, 320, 480, 480, 320, 240}, new int[]{250, 300, 200, 225, 325, 275}, 6);
		checkImage = new BufferedImage(Frame.preferredSize.width, Frame.preferredSize.height, BufferedImage.TYPE_4BYTE_ABGR);
		xImage = new BufferedImage(Frame.preferredSize.width, Frame.preferredSize.height, BufferedImage.TYPE_4BYTE_ABGR);

		//add components
		super.add(box);
		super.add(organization);
		super.add(other);
		box.addMouseListener(adapter);
		box.addMouseMotionListener(adapter);

		//set up layout
		layout.putConstraint(SpringLayout.WEST, box, thisX, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, box, thisY, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, organization, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, organization, -20, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, organization, -20, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.EAST, other, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, other, 20, SpringLayout.HORIZONTAL_CENTER, this);
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
			//check to see where the text box is
			boolean inOrganization = thisX < Frame.preferredSize.width / 2 - 20 && thisX + box.getPreferredSize().width > 20 && thisY + box.getPreferredSize().height > Frame.preferredSize.height - 20 - organization.getPreferredSize().height;
			boolean inOther = thisX + box.getPreferredSize().width > Frame.preferredSize.width / 2 + 20 && thisX < Frame.preferredSize.width - 20 && thisY + box.getPreferredSize().height > Frame.preferredSize.height - 20 - other.getPreferredSize().height;
			if (inOrganization ^ inOther){ // in only one of the boxes
				if (inOrganization ^ isOrganization){ // Wrong!

				}
				else{ // Right!
					content = checkImage;
					Graphics2D g = checkImage.createGraphics();
					g.setColor(Color.GREEN);
					Timer timer = new Timer(1, null);
					timer.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ae){
							g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i++ / 100f));
							g.fillPolygon(checkmark);
							repaint();
							System.out.println(i);
							if (i == 100){
								timer.stop();
							}			
						}
					});
					timer.start();

				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e){
			dx = e.getXOnScreen() - x;
			dy = e.getYOnScreen() - y;
			if (thisX + dx >= 0 && thisX + dx <= maxX){
				layout.putConstraint(SpringLayout.WEST, box, thisX = thisX + dx, SpringLayout.WEST, LevelOne.this);
			}
			if (thisY + dy >= 0 && thisY + dy <= maxY){
				layout.putConstraint(SpringLayout.NORTH, box, thisY = thisY + dy, SpringLayout.NORTH, LevelOne.this);
			}
			x += dx;
			y += dy;
			paintImmediately(thisX, thisY, 0, 0);
			revalidate();
		}
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(content, 0, 0, this);
	}
}