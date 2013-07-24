import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Intro extends BasicGameState
{
	private int msgCount = 0;
	private Rectangle skipBox = new Rectangle(875, 525, 100, 50);
	private Rectangle mouseLocation = new Rectangle(0,0,0,0);
	private String [] messages = {"Wake up.",
								  "Deacon Pollip, wake up.",
								  "You have to move now. You won't last much longer here.",
								  ""};
	
	
	
	public Intro(int state) {
		
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	}

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {

		Display.sync(60);
		g.setColor(Color.white);
		drawMessage(messages[msgCount], g);
		g.drawRect(875, 525, 100, 50);
		g.drawString("skip", 905, 540);
		
			
	}

	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gc.getInput();
		mouseLocation = new Rectangle(Mouse.getX(), 600-Mouse.getY(), 1, 1);
		if(input.isMousePressed(0) && msgCount<messages.length-1)
		{
			if(mouseLocation.intersects(skipBox))
				msgCount = messages.length-1;
			else
				msgCount++;
		}
		if(msgCount==messages.length-1)
			arg1.enterState(3, new FadeOutTransition(), null);
			
	}

	public int getID() {
		return 1;
	}
	
	public void drawMessage(String msg, Graphics g)
	{
		g.drawString(msg, 500-(msg.length()*5), 280);
	}
	
}