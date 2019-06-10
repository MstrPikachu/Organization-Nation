import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.EventHandler;

/**
 * This stores the contents of the first level.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class LevelOne extends Level{
	//Level instructions
	private static final String instructions = "<html><body width='400'>The first level will be where you practice organizing, and you learn about organization! You will be required to organize a set of text boxes by clicking on all the ones with information about the problem that lack of organizational skills pose. The other boxes will contain information on other random topics, such as giraffes. You will collect points from how many right and wrong answers you get by dragging and dropping. If you get an incorrect text box you will lose two points, and if you get a correct text box you will get 1 point. Once you select an answer, the information you need to be organizing disappears and you cannot get it back. Take your time and choose carefully, because this is not a timed level. Good luck!</body></html>";

	//loop counter
	private int i;
	//loop increment
	private int fadeIn = 1;

	private TextBox box, organization, other;

	//If the text box is about organization or not
	private boolean isOrganization;
	//If the mouse is in the panel
	private boolean inPanel;

	private final String[] text = {"Giraffes have long necks", "ATM machines are automated", "<html>The sun rises in the east<br>and sets in the west", "<html>Atoms are invisible<br>and I am made of atoms,<br>therefore I am invisible", "<html>Poor organizational abilities<br>makes people less efficient", "<html>Poor organization causes<br>people to lose work, mix-up assignments,<br>and miss deadlines", "<html>People with worse organization are<br>more stressed and mentally exhausted", "<html>People that are more disorganized<br>are more likely to suffer<br>from stress induced headaches and<br>other stress induced pains", "It is important to be organized"};
	//the index of the text array
	private int textIndex;

	//the number of correct sorts
	private int correct;

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
		box.addMouseListener(new MouseAdapter());
		setDraggable(box);

		//set up layout
		layout.putConstraint(SpringLayout.WEST, organization, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, organization, -20, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, organization, -20, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.EAST, other, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, other, 20, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, other, -20, SpringLayout.SOUTH, this);
		JOptionPane.showMessageDialog(Main.frame, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}

	private class MouseAdapter extends java.awt.event.MouseAdapter{
		@Override
		public void mouseReleased(MouseEvent e){
			//check to see where the text box is
			boolean inOrganization = box.getBounds().intersects(organization.getBounds());
			boolean inOther = box.getBounds().intersects(other.getBounds());
			if (inOrganization ^ inOther){ // in only one of the boxes
				box.setVisible(false);
				if (inOrganization ^ isOrganization){ // Wrong!
					addPoints(-2);
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
								if (textIndex < text.length)
									box.setVisible(true);
							}	
						}
					});
					timer.start();
				}
				else{ // Right!
					addPoints(1);
					correct += 1;
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
								if (textIndex < text.length)
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
					JOptionPane.showMessageDialog(Main.frame, "You received " + getPoints() + " points.", "Level Complete", JOptionPane.PLAIN_MESSAGE);
					Main.frame.mainMenu();
				}
			}
		}
	}

	/**
	 * Sets the text of the text box for this level.
	 *
	 * @param str the String to set the text of the box to.
	 */
	private void setText(String str){
		box.setText(str);
		layout.putConstraint(SpringLayout.WEST, box, (Frame.preferredSize.width - box.getPreferredSize().width) / 2, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, box, (Frame.preferredSize.height - box.getPreferredSize().height) / 2, SpringLayout.NORTH, this);
		isOrganization = str.contains("organ");
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(content, (Frame.preferredSize.width - content.getWidth()) / 2, (Frame.preferredSize.height - content.getHeight()) / 2, this);
	}
}