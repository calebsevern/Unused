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

public class baseScene extends BasicGameState
{
	player player1;
	private Image pic1;
	private Rectangle stairBorder = new Rectangle(54, 540, 10, 50);
	private ArrayList<Rectangle> borders = new ArrayList<Rectangle>();
	
	public baseScene(int state) {
		
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		player1 = frame.player1;
		pic1 = new Image("res/Scene/baseScene.png");
		borders.add(new Rectangle(15, 530, 60, 10));
		borders.add(new Rectangle(0, 0, 15, 600));
		borders.add(new Rectangle(0, 0, 1000, 15));
		borders.add(new Rectangle(0, 590, 1000, 10));
		borders.add(new Rectangle(990, 0, 10, 600));
		borders.add(new Rectangle(800, 15, 120, 115));
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		Display.sync(60);
		player1.canGoLeft = true;
		player1.canGoRight = true;
		player1.canGoUp = true;
		player1.canGoDown = true;
		
		g.drawImage(pic1, 0, 0);
		g.setColor(Color.green);
		//g.drawString("This is a room/cave/whatever.", 100, 100);
//		for(Rectangle r : borders)
//			g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		
		player1.paint(g);
		g.drawImage(new Image(player1.current), player1.getX(), player1.getY());
	}

	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gc.getInput();
		collisionDetection();
		//frame.passTime();
		
		
		if(input.isKeyDown(Input.KEY_S)) {
			player1.moveUp(player1.speed);
		}
		

		if(input.isKeyDown(Input.KEY_W)) {
			player1.moveDown(player1.speed);
		}

		if(input.isKeyDown(Input.KEY_A)) {		
			player1.moveLeft(player1.speed);
		}		

		if(input.isKeyDown(Input.KEY_D)) {
			player1.moveRight(player1.speed);
		}
		
		if(input.isKeyDown(Input.KEY_LSHIFT)) {
			frame.prevState = getID();
			arg1.enterState(frame.pause, null, new FadeInTransition());
		}
		
		if(input.isMousePressed(0)) {
		}
		
		if(input.isKeyDown(Input.KEY_SPACE)) {
			if(player1.border.intersects(stairBorder)) {
				player1.setX(130);
				player1.setY(280);
				player1.current = player1.down1;
				arg1.enterState(frame.scene01);
			}
		}
		
	}
	
	public int getID() { 
		return 101;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}