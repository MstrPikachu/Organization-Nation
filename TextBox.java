import java.awt.*;
import javax.swing.*;

/**
 * This class represents text inside a box.
 *
 * @author Peter Lin
 * @version 1.2
 */

public class TextBox extends JPanel{
	private JLabel text;
	private Dimension size;

	/**
	 * The <code>TextBox</code> constructor.
	 * Supports HTML.
	 *
	 * @param str the contents of the text box.
	 */
	public TextBox(String str){
		super();
		SpringLayout layout = new SpringLayout();
		text = new JLabel(str);

		super.add(text);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, text, 0, SpringLayout.VERTICAL_CENTER, this);

		setBackground(Color.WHITE);
	}

	public TextBox(String str, int width, int height){
		this(str);
		size = new Dimension(width, height);
	}

	@Override
	public Dimension getPreferredSize(){
		return size == null ? super.getPreferredSize() : size;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getSize().width, getSize().height);
	}
}