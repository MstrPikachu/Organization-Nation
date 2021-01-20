import java.awt.*;
import javax.swing.*;

/**
 * This JPanel is the base for every panel with the background animation.
 *
 * @author Peter Lin
 * @version 1.4
 */

public class Background extends JPanel{
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < Main.frame.CIRCLES; i ++){
			int ind = Main.frame.COLOR_OFFSET + i * Main.frame.COLOR_RANGE / Main.frame.CIRCLES;
			if (ind >= Main.frame.COLOR_RANGE)
			    ind -= Main.frame.COLOR_RANGE;
			g.setColor(Main.frame.colors[ind]);
			g.fillOval((int)Main.frame.x[i], (int)Main.frame.y[i], Main.frame.DIAMETER, Main.frame.DIAMETER);
		}
	}
}
