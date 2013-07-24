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

public class Scene02 extends BasicGameState
{
	player player1;
	private Image bg1, water1;
	private Rectangle exitLeft;
	private ArrayList<Rectangle> borders = new ArrayList<Rectangle>();
	private ArrayList<Dialogue> objects = new ArrayList<Dialogue>();
	
	public Scene02(int state) {
		
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		player1 = frame.player1;
		exitLeft = new Rectangle(0, 0, 10, 600);
		borders.add(new Rectangle(472, 215, 525, 47));
		borders.add(new Rectangle(472-47, 215+47, 525+(47), 47));
		borders.add(new Rectangle(472-(47*2), 215+(47*2), 47*4, 47));
		borders.add(new Rectangle(477+(47*6), 215+(47*2), 47*5, 47*6));
		borders.add(new Rectangle(472-(47*2), 215+(47*3), 47*3, 47));
		
		borders.add(new Rectangle(477-(47*1), 220+(47*4), 47*2, 47));

		borders.add(new Rectangle(472-(47*2), 220+(47*5), 47*4, 47));
		borders.add(new Rectangle(477+(47*5), 220+(47*5), 47, 47));
		borders.add(new Rectangle(472-(47*2), 220+(47*6), 47*8, 47*2));
		
		objects.add(new Dialogue("You cannot cross.", new Rectangle(440, 405, 20, 48)));
		
		
		
		bg1 = new Image("res/Scene/dirt.png");
		water1 = new Image("res/Scene/water02_1.png");
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		Display.sync(60);
		
		g.drawImage(bg1, 0, 0);
		g.drawImage(water1, 0, 0);
		g.setColor(Color.black);
		
		player1.paint(g);
		g.drawImage(new Image(player1.current), player1.getX(), player1.getY());
		g.drawString(""+player1.getX()+","+player1.getY(), 50, 50);
		
		
		g.setColor(new Color(0f,0f,0f, frame.darkness));
		g.fillRect(0, 0, 1000, 600);
		frame.rain();
		g.drawImage(frame.currentRain, 0, 0);
		g.setColor(Color.white);
		//Functions.drawObjects(objects, g);
		
		if(Dialogue.inDialogue) {
			Dialogue.showDialogue(Dialogue.currentDialogue, g);
		}
		
	}

	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gc.getInput();
		Functions.passTime();
		frame.dayCycleLogic();
		Functions.collisionDetection(borders);
		
		
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
		
		
		if(input.isKeyPressed(Input.KEY_SPACE))
			dialogueLogic();
		
		if(player1.border.intersects(exitLeft)) {
			arg1.enterState(frame.scene01);
			player1.setX(1000-player1.width-15);
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
		
	
	public void dialogueLogic() {
		if(Dialogue.inDialogue) {
			Dialogue.inDialogue = false;
		}
		
		else {
			for(Dialogue d : objects) {
				if(player1.interactionBox.intersects(d.location)) {
					Dialogue.inDialogue = true;
					Dialogue.currentDialogue = d.text;
					break;
				}
			}
		}
	}

	public int getID() {
		return 4;
	}
	
	
}