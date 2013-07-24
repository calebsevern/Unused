import java.util.ArrayList;

import org.lwjgl.opengl.Display;
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
import org.newdawn.slick.state.transition.VerticalSplitTransition;

public class Scene01 extends BasicGameState
{
	player player1;
	private Image pic1, rain1, rain2, currentRain;
	private Rectangle baseDoorBorder, exitRight;
	private ArrayList<Rectangle> borders = new ArrayList<Rectangle>();
	private int rainCounter = 0;
	
	public Scene01(int state) {
		
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		player1 = frame.player1;
		baseDoorBorder = new Rectangle(128, 241, player1.width, player1.height);
		exitRight = new Rectangle(1000-10, 0, 10, 600);
		borders.add(new Rectangle(60, 40, 195, 293-player1.height));
		borders.add(exitRight);
		pic1 = new Image("res/Scene/scene01.png");
		rain1 = new Image("res/Scene/rain1.png");
		rain2 = new Image("res/Scene/rain2.png");
		currentRain = rain1;
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		Display.sync(60);
		
		g.drawImage(pic1, 0, 0);
		g.setColor(Color.black);
		g.fillRect(128, 241, player1.width, player1.height);
//		for(Rectangle r : borders)
//			g.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		
		player1.paint(g);
		g.drawImage(new Image(player1.current), player1.getX(), player1.getY());
		float a = (float) frame.time;
		float b = (float) ((a/300) + .5);
		float c = b - 1;
		g.drawString(""+player1.getX()+","+player1.getY(), 50, 50);
		g.setColor(new Color(0f,0f,0f, c));
		g.fillRect(0, 0, 1000, 600);
		g.drawImage(currentRain, 0, 0);
		
	}

	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gc.getInput();
		frame.dayCycleLogic();
		collisionDetection();
		rain();
		
		
		if(input.isKeyDown(Input.KEY_S)) {
			player1.moveUp(3);
			battleChance(arg1, gc);
		}
		
		else if(input.isKeyDown(Input.KEY_W)) {
			player1.moveDown(3);
			battleChance(arg1, gc);
		}

		else if(input.isKeyDown(Input.KEY_A)) {		
			player1.moveLeft(3);
			battleChance(arg1, gc);
		}		

		else if(input.isKeyDown(Input.KEY_D)) {
			player1.moveRight(3);
			battleChance(arg1, gc);
		}
		
		if(input.isKeyDown(Input.KEY_LSHIFT)) {
			frame.prevState = getID();
			arg1.enterState(frame.pause, null, new FadeInTransition());
		}
		if(input.isKeyDown(Input.KEY_F)) {
			enterBattle(arg1, gc);
		}
		
		if(input.isMousePressed(0)) {
		}
		
		if(player1.border.intersects(baseDoorBorder) && input.isKeyDown(Input.KEY_SPACE)) {
			player1.setX(80);
			player1.setY(535);
			player1.current = player1.right1;
			arg1.enterState(frame.base);
		}
		
		if(player1.border.intersects(exitRight)) {
			arg1.enterState(frame.scene02);
			player1.setX(15);
		}
	}
	
	public void enterBattle(StateBasedGame sbg, GameContainer gc) throws SlickException {
		frame.prevState = getID();
		sbg.getState(102).init(gc, sbg);
		sbg.enterState(frame.battle, null, new VerticalSplitTransition());
	}
	
	public void battleChance(StateBasedGame sbg, GameContainer gc) throws SlickException {
		int chance = (int) (Math.random()*10000)+1;
		if(chance==1)
			enterBattle(sbg, gc);
	}
	
	public void collisionDetection() {
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
	
	public void rain() {
		rainCounter++;
		if(rainCounter==5) {
			if(currentRain.equals(rain1))
				currentRain = rain2;
			else if(currentRain.equals(rain2))
				currentRain = rain1;
			rainCounter = 0;
		}
	}

	public int getID() {
		return 3;
	}
	
	
}