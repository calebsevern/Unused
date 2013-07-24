import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class tile
{
	Image image;
	int x, y;
	
	public tile(String filename, int x, int y) throws SlickException {
		image = new Image(filename);
		this.x = x;
		this.y = y;
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, x, y);
	}
	
	public void moveX(int mod) {
		x += mod;
	}
	
	public void moveY(int mod) {
		y += mod;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY() {
		return y;
	}
}