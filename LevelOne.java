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
	//loop increment
	private int fadeIn = 1;

	private SpringLayout layout = new SpringLayout();
	private TextBox box, organization, other;
	private MouseAdapter adapter = new MouseAdapter();

	//If the text box is about organization or not
	private boolean isOrganization;
	//If the mouse is in the panel
	private boolean inPanel;

	private final String[] text = {"Giraffes have long necks", "ATM machines are automated", "<html>The sun rises in the east<br>and sets in the west", "<html>Atoms are invisible<br>and I am made of atoms,<br>therefore I am invisible", "<html>Poor organizational abilities<br>makes people less efficient", "<html>Poor organization causes<br>people to lose work, mix-up assignments,<br>and miss deadlines", "<html>People with worse organization are<br>more stressed and mentally exhausted", "<html>People that are more disorganized<br>are more likely to suffer<br>from stress induced headaches and<br>other stress induced pains", "It is important to be organized"};
	//the index of the text array
	private int textIndex;

	private Polygon checkmark;
	BufferedImage checkImage, xImage, content;

	/**
	 * Constructs a LevelOne object.
	 */
	public LevelOne(){
		//set up panel
		super();
		super.setLayout(layout);

		//declare components
		box = new TextBox("");
		organization = new TextBox("Organization");
		other = new TextBox("Other");

		shuffle(text);
		setText(text[0]);

		//initialize variables
		checkmark = new Polygon(new int[]{240, 320, 480, 480, 320, 240}, new int[]{150, 200, 100, 125, 225, 175}, 6);
		checkImage = new BufferedImage(Frame.preferredSize.width, Frame.preferredSize.height, BufferedImage.TYPE_4BYTE_ABGR);
		xImage = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);
		content = xImage;

		//add components
		super.add(box);
		super.add(organization);
		super.add(other);
		box.addMouseListener(adapter);
		box.addMouseMotionListener(adapter);

		//set up layout
		layout.putConstraint(SpringLayout.WEST, organization, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, organization, -20, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, organization, -20, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.EAST, other, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, other, 20, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, other, -20, SpringLayout.SOUTH, this);
	}

	/**
	 * Shuffles an array to a random permutation.
	 *
	 * @param arr the array to be shuffled.
	 */
	private void shuffle(String[] arr){
		for (int i = arr.length - 1; i > 1; i -= 1){
			int index = (int)(Math.random() * i);
			String temp = arr[i];
			arr[i] = arr[index];
			arr[index] = temp;
		}
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
				box.setVisible(false);
				if (inOrganization ^ isOrganization){ // Wrong!
					content = xImage;
					Graphics2D g = xImage.createGraphics();
					g.setColor(Color.RED);
					g.rotate(Math.PI / 4, 50, 50);
					Timer timer = new Timer(1, null);
					timer.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ae){
							i += fadeIn;
							g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, i / 100f));
							g.fillRect(0, 40, 100, 20);
							g.fillRect(40, 0, 20, 100);
							repaint();
							if (i == 100){
								fadeIn = -1;
							}
							else if (i == 0 && fadeIn == -1){
								timer.stop();
								fadeIn = 1;
								box.setVisible(true);
							}	
						}
					});
					timer.start();
				}
				else{ // Right!
					content = checkImage;
					Graphics2D g = checkImage.createGraphics();
					g.setColor(Color.GREEN);
					Timer timer = new Timer(1, null);
					timer.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ae){
							i += fadeIn;
							g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, i / 100f));
							g.fillPolygon(checkmark);
							repaint();
							if (i == 100){
								fadeIn = -1;
							}
							else if (i == 0 && fadeIn == -1){
								timer.stop();
								fadeIn = 1;
								box.setVisible(true);
							}	
						}
					});
					timer.start();
				}
				if (++textIndex < text.length){
					setText(text[textIndex]);
					repaint();
					revalidate();
				}
				else{ // level is done
					
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

	/**
	 * Sets the text of the text box for this level.
	 *
	 * @param str the String to set the text of the box to.
	 */
	private void setText(String str){
		box.setText(str);
		maxX = Frame.preferredSize.width - box.getPreferredSize().width;
		maxY = Frame.preferredSize.height - box.getPreferredSize().height;
		thisX = maxX / 2;
		thisY = maxY / 2;
		layout.putConstraint(SpringLayout.WEST, box, thisX, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, box, thisY, SpringLayout.NORTH, this);
		isOrganization = str.contains("organ");
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(content, (Frame.preferredSize.width - content.getWidth()) / 2, (Frame.preferredSize.height - content.getHeight()) / 2, this);
	}
}