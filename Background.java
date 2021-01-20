import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.EventHandler;

/**
 * This JPanel is the base for every panel with the background animation.
 *
 * @author Peter Lin
 * @version 1.4
 */

public class Background extends JPanel implements Timed {

	//background constants
	public static final int CIRCLES = 9;
	public static final int COLOR_RANGE = 360;
	public static final int MAX_SPEED = 6;
	public static final int DIAMETER = 150;
	
	//x, y, and change in x, y values for each circle in the background
	public static double[] x = new double[CIRCLES];
	public static double[] y = new double[CIRCLES];
	public static double[] dx = new double[CIRCLES];
	public static double[] dy = new double[CIRCLES];

	//color related variables
	public static int COLOR_OFFSET = 0;
	public static Color[] colors = new Color[COLOR_RANGE];
    
	//background timer
	public final ActionListener backgroundListener = EventHandler.create(ActionListener.class, this, "updateCircles");
	protected Timer timer = new Timer(50, backgroundListener);

	//initialize background variables
	static{
	    //initialize motion variables
	    for (int i = 0; i < CIRCLES; i ++){
		x[i] = Math.random() * (Main.frame.preferredSize.width - DIAMETER);
		y[i] = Math.random() * (Main.frame.preferredSize.height - DIAMETER);
		double v = Math.sqrt(Math.random()) * MAX_SPEED;
		double angle = Math.random() * 2 * Math.PI;
		dx[i] = v * Math.cos(angle);
		dy[i] = v * Math.sin(angle);
	    }

	    //initialize color variables
	    for (int i = 0; i < COLOR_RANGE; i ++){
		double angle = 2 * Math.PI / COLOR_RANGE * i;
		colors[i] = new Color ((int) (127 * Math.sin (angle) + 128), (int) (127 * Math.sin (angle + Math.PI * 2 / 3) + 128), (int) (127 * Math.sin (angle + Math.PI * 4 / 3) + 128), 128);
	    }
	}
		
	//update background by drawing the circles
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < CIRCLES; i ++){
			int ind = COLOR_OFFSET + i * COLOR_RANGE / CIRCLES;
			if (ind >= COLOR_RANGE)
			    ind -= COLOR_RANGE;
			g.setColor(colors[ind]);
			g.fillOval((int)x[i], (int)y[i], DIAMETER, DIAMETER);
		}
	}

	/**
	 * Updates the background animation.
	 */
	public void updateCircles(){
		COLOR_OFFSET++;
		if (COLOR_OFFSET >= COLOR_RANGE)
		    COLOR_OFFSET = 0;
		for (int i = 0; i < CIRCLES; i ++){
			if (x[i] + dx[i] < 0 || x[i] + dx[i] > Frame.preferredSize.width - DIAMETER)
				dx[i] = -dx[i];
			if (y[i] + dy[i] < 0 || y[i] + dy[i] > Frame.preferredSize.height - DIAMETER)
				dy[i] = -dy[i];
			x[i] += dx[i];
			y[i] += dy[i];
		}
		repaint();
	}

	@Override
	public Timer getTimer(){
	    return timer;
	}

}
