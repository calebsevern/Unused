import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Functions {
	
	public static void collisionDetection(ArrayList<Rectangle> borders) {
		frame.player1.canGoLeft = true;
		frame.player1.canGoDown = true;
		frame.player1.canGoUp = true;
		frame.player1.canGoRight = true;
		
		for(int i=0; i<borders.size(); i++) {
			if(frame.player1.left.intersects(borders.get(i)))
				frame.player1.canGoLeft = false;
			if(frame.player1.right.intersects(borders.get(i)))
				frame.player1.canGoRight = false;
			if(frame.player1.up.intersects(borders.get(i)))
				frame.player1.canGoDown = false;
			if(frame.player1.bottom.intersects(borders.get(i)))
				frame.player1.canGoUp = false;
		}
	}
	
	/***********************************************************************************************************/
	
	public static void passTime() {
		float a = (float) frame.time;
		float b = (float) ((a/300) + .5);
		float c = b - 1;
		frame.darkness = c;
	}
	
	

	/***********************************************************************************************************/
	
	public static void drawObjects(ArrayList<Dialogue> objects, Graphics g) {
		for(Dialogue d : objects)
			g.drawRect(d.location.getX(), d.location.getY(), d.location.getWidth(), d.location.getHeight());
	}
	
}