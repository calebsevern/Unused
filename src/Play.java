import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState
{
	public Play(int state) {
		
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Here we go!", 100, 100);
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}

	public int getID() {
		return 2;
	}
	
}