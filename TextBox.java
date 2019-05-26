import java.awt.*;
import javax.swing.*;

/**
 * This class represents text inside a box.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class TextBox extends JPanel{
	private JLabel text;
	private Dimension size;
	private Dimension textSize;

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

		textSize = text.getPreferredSize();
		size = new Dimension(textSize.width + 10, textSize.height + 10);
		setBackground(Color.WHITE);
	}
	@Override
	public Dimension getPreferredSize(){
		return size;
	}
}