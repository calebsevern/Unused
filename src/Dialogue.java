import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Dialogue {
	
	public String text;
	public Rectangle location;
	public static boolean inDialogue = false;
	public static String currentDialogue = "";
	
	public Dialogue(String text, Rectangle location) {
		this.text = text;
		this.location = location;
	}
	
	public static void showDialogue(String s, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,450,1000,150);
		g.setColor(Color.white);
		g.drawString(s, 100, 480);
		g.drawString("Press SPACE to continue...", 765, 570);
	}
}