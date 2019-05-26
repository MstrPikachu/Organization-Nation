import java.awt.*;
import javax.swing.*;

/**
 * This class represents text inside a box.
 *
 * @author Peter Lin
 * @version 1.1
 */

public class TextBox extends JComponent{
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
		text = new JLabel(str);
		textSize = text.getPreferredSize();
		size = new Dimension(textSize.width + 10, textSize.height + 10);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(-5, -5, textSize.width + 10, textSize.height + 10);
	}
	@Override
	public Dimension getPreferredSize(){
		return size;
	}
}