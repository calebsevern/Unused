import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState
{
	Image title;
	Image [] grain = new Image[4];
	Image [] buttons = new Image[4];
	Rectangle [] buttonBorders = new Rectangle[4];
	Rectangle mouseLocation = new Rectangle(0,0,0,0);
	
	private int grainLoop=0;
	private int spacer=0;
	int x = 200;
	int y = 200;
	
	
	public MainMenu(int state) {
		
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		frame.initImages();
		title = new Image("res/MainMenu/title.png");
		for(int i=0; i<grain.length; i++)
			grain[i] = new Image("res/MainMenu/grain/"+i+".png");
		for(int i=0; i<buttons.length; i++)
			buttons[i] = new Image("res/MainMenu/menu/"+i+".png");
		for(int i=0; i<buttonBorders.length; i++)
			buttonBorders[i] = new Rectangle(300, 200+(70*i), 400, 60);
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		
		grainRender(g);
		g.setColor(Color.black);
		g.drawImage(title, 500-(title.getWidth()/2), 50);
		for(int i=0; i<buttons.length; i++)
			g.drawImage(buttons[i], 300, 200+(70*i));
		for(int i=0; i<buttonBorders.length; i++)
			g.drawRect(buttonBorders[i].getX(), buttonBorders[i].getY(), buttonBorders[i].getWidth(), buttonBorders[i].getHeight());
	}

	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gc.getInput();
		mouseLocation = new Rectangle(Mouse.getX(), 600-Mouse.getY(), 1, 1);
		if(input.isKeyDown(Input.KEY_DOWN)) {
			System.out.println("doowop");
		}
		if(input.isMousePressed(0))
		{
			if(mouseLocation.intersects(buttonBorders[0])) {
				arg1.enterState(4);
			}
			if(mouseLocation.intersects(buttonBorders[1])) {
				arg1.enterState(1, new FadeOutTransition(), new FadeInTransition());
			}
			if(mouseLocation.intersects(buttonBorders[2])) {
				
			}
			if(mouseLocation.intersects(buttonBorders[3])) {
				System.exit(0);
			}
				
		}
		
	}

	public int getID() {
		return 0;
	}
	
	public void grainRender(Graphics g) {
		g.drawImage(grain[grainLoop], 0, 0);
		spacer++;
		if(spacer%52 == 0) {
			grainLoop++;
			if(grainLoop==4)
				grainLoop=0;
		}
		if(spacer>5200)
			spacer = 0;
	}
}