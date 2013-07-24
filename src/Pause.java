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

public class Pause extends BasicGameState
{
	Image title;
	Image [] grain = new Image[4];
	Image [] buttons = new Image[4];
	Rectangle [] buttonBorders = new Rectangle[4];
	Rectangle mouseLocation = new Rectangle(0,0,0,0);
	Image moon, sun, timeLine;
	
	int x = 200;
	int y = 200;
	
	
	public Pause(int state) {
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		title = new Image("res/MainMenu/title.png");
		
		for(int i=0; i<grain.length; i++)
			grain[i] = new Image("res/MainMenu/grain/"+i+".png");
		for(int i=0; i<buttons.length; i++)
			buttons[i] = new Image("res/MainMenu/menu/"+i+".png");
		for(int i=0; i<buttonBorders.length; i++)
			buttonBorders[i] = new Rectangle(300, 200+(70*i), 400, 60);
		
		moon = new Image("res/Pause/moon.png");
		sun  = new Image("res/Pause/sun.png");
		timeLine = new Image("res/Pause/timeLine.png");
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Status: "+frame.player1.status, 50, 140);
		g.drawString("HP:     "+frame.player1.getHP()+"/"+frame.player1.getMaxHP(), 50, 165);
		g.drawString("AP:     "+frame.player1.stamina+"/"+frame.player1.maxStamina, 50, 180);
		g.drawString("Thirst: "+frame.player1.thirst+"/"+frame.player1.maxThirst, 50, 195);
		g.drawString("Weapons", 50, 270);
		for(int i=0; i<frame.player1.inventory.weapons.size(); i++)
			g.drawString(frame.player1.inventory.weapons.get(i).name, 50, 285+(10*i));
		
		g.drawImage(sun, 250, 50);
		g.drawImage(moon, 650, 50);
		g.drawImage(timeLine, 370, 85);
		g.setColor(Color.green);
		g.fillRect(370+frame.time, 86, 2, 15);
		//g.drawString(""+frame.time, 400, 100);
	}

	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gc.getInput();
		mouseLocation = new Rectangle(Mouse.getX(), 600-Mouse.getY(), 1, 1);
		if(input.isKeyDown(Input.KEY_DOWN)) {
			System.out.println("doowop");
		}
		if(input.isKeyDown(Input.KEY_LSHIFT)) {
			arg1.enterState(frame.prevState, null, new FadeInTransition());
		}
		
		{
//			if(mouseLocation.intersects(buttonBorders[0])) {
//				arg1.enterState(3, new BlobbyTransition(), null);
//			}
//			if(mouseLocation.intersects(buttonBorders[1])) {
//				arg1.enterState(1, new FadeOutTransition(), new FadeInTransition());
//			}
//			if(mouseLocation.intersects(buttonBorders[2])) {
//				
//			}
//			if(mouseLocation.intersects(buttonBorders[3])) {
//				System.exit(0);
//			}
				
		}
		
	}

	public int getID() {
		return 100;
	}
	
}