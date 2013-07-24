import org.lwjgl.input.Mouse;
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

public class Battle extends BasicGameState
{
	Image title;
	Image [] grain = new Image[4];
	private int mt = 0;
	public int fightTimer = 0;
	public boolean canChoose = false;
	private boolean defending = false;
	private Enemy [] enemies = null;
	private Rectangle [] enemyBorders = null;
	private int enemySelected = 0;
	private String context = "";
	
	Image [] buttons1 = new Image[3];
	Image bg;
	Rectangle [] buttonBorders1 = new Rectangle[3];
	Rectangle [] buttonBorders2 = new Rectangle[4];
	Rectangle mouseLocation = new Rectangle(0,0,0,0);
	Rectangle backButton = new Rectangle(0, 390, 50, 15);
	public boolean [] section = {true, false, false};
	
	int x = 200;
	int y = 200;
	
	
	public Battle(int state) {
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		System.out.println("battle init");
		createEnemies();
		for(int i=0; i<3; i++) {
			buttonBorders1[i] = new Rectangle(0, 600-(60*(i+1)), 250, 60);
		}
		for(int i=0; i<4; i++) {
			buttonBorders2[i] = new Rectangle(0, 600-(45*(i+1)), 250, 45);
		}
		buttons1[2] = new Image("res/Battle/attackButton.png");
		buttons1[1] = new Image("res/Battle/itemButton.png");
		buttons1[0] = new Image("res/Battle/fleeButton.png");
		bg = new Image("res/Battle/sand.png");
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		Display.sync(60);
		g.drawImage(bg, 0, 0);
		g.setColor(Color.darkGray);
		g.fillRect(0, 420, 1000, 180);
		g.setColor(Color.white);
		g.drawString("Defending: "+defending, 420, 506);
		g.drawRect(enemyBorders[enemySelected].getX()-1, enemyBorders[enemySelected].getY()-1, enemyBorders[enemySelected].getWidth()+2, enemyBorders[enemySelected].getHeight()+2);
		
		if(section[0]) {
			for(int i=0; i<3; i++) {
				g.setColor(Color.gray);
				if(mouseLocation.intersects(buttonBorders1[i]))
					g.fillRect(buttonBorders1[i].getX(), buttonBorders1[i].getY(), buttonBorders1[i].getWidth(), buttonBorders1[i].getHeight());
				else
					g.drawRect(buttonBorders1[i].getX(), buttonBorders1[i].getY(), buttonBorders1[i].getWidth(), buttonBorders1[i].getHeight());
				g.setColor(Color.white);
				if(i==0)
					g.drawString("FLEE", buttonBorders1[i].getX(), buttonBorders1[i].getY());
				else if(i==1)
					g.drawString("ITEM", buttonBorders1[i].getX(), buttonBorders1[i].getY());
				else if(i==2)
					g.drawString("FIGHT", buttonBorders1[i].getX(), buttonBorders1[i].getY());
			}
		}
		
		else if(section[1])
			for(int i=0; i<4; i++) {
				g.drawRect(0, 600-(45*(i+1)), 250, 45);
				g.setColor(Color.gray);
				if(mouseLocation.intersects(buttonBorders2[i]))
					g.fillRect(buttonBorders2[i].getX(), buttonBorders2[i].getY(), buttonBorders2[i].getWidth(), buttonBorders2[i].getHeight());
				else
					g.drawRect(buttonBorders2[i].getX(), buttonBorders2[i].getY(), buttonBorders2[i].getWidth(), buttonBorders2[i].getHeight());
				g.setColor(Color.white);
				
				if(i==2)
					g.drawString("DEFEND", buttonBorders2[i].getX(), buttonBorders2[i].getY());
				else if(i==3)
					g.drawString("ATTACK", buttonBorders2[i].getX(), buttonBorders2[i].getY());
			}
		
		
		g.fillRect(0, 410, fightTimer, 10);
		
		g.drawRect(0, 390, 50, 15);
		g.drawString("BACK", 0, 390);
		
		g.drawImage(new Image(frame.player1.icon), 276, 446);
		g.setColor(Color.white);
		g.drawString("HP: " + frame.player1.getHP()+"/"+frame.player1.getMaxHP(), 420, 446);
		g.drawString("Stamina: " + frame.player1.stamina + "/" + frame.player1.maxStamina, 420, 466);
		g.drawString("Equipped: " + frame.player1.equipped.name, 420, 486);
		for(int i=0; i<enemies.length; i++) {
			if(enemies[i]!=null) {
				g.drawImage(new Image(enemies[i].icon), 100+(180*i), 100);
				g.drawRect(100+(180*i), 260, enemies[i].getMaxHP()+1, 10);
				g.setColor(Color.red);
				g.fillRect(101+(180*i), 261, enemies[i].getHP(),9);
				
				g.drawRect(100+(180*i), 271, 101, 10);
				g.setColor(Color.green);
				g.fillRect(101+(180*i), 272, enemies[i].AP,9);
				g.setColor(Color.white);
			}
		}
		
		g.drawString(context, 400, 350);
		
		
	}

	public void update(GameContainer gc, StateBasedGame arg1, int arg2) throws SlickException {
		
		if(isFinished()) {
			//createEnemies();
			arg1.enterState(frame.prevState);
		}
		enemyLogic();
		fightTimer();
		Input input = gc.getInput();
		mouseLocation = new Rectangle(Mouse.getX(), 600-Mouse.getY(), 1, 1);
		if(input.isKeyDown(Input.KEY_DOWN)) {
			System.out.println("doowop");
		}
		if(input.isKeyDown(Input.KEY_LSHIFT)) {
			arg1.enterState(frame.prevState, null, new FadeInTransition());
		}
		
		if(input.isMousePressed(0))
		{
			if(mouseLocation.intersects(backButton))
			{
				if(section[2]) {
					section[2] = false;
					section[1] = true;
				}
				else if(section[1]) {
					section[1] = false;
					section[0] = true;
				}
			}
			
			if(section[0])
			{
				if(mouseLocation.intersects(buttonBorders1[2])) {
					section[0]=false;
					section[1]=true;
				}
				if(mouseLocation.intersects(buttonBorders1[0])) {
					arg1.enterState(frame.prevState);
				}
			}
				
			else if(section[1] && canChoose)
			{
				if(mouseLocation.intersects(buttonBorders2[3])) {
					System.out.println("ATTACK");
					attack();
				}
				else if(mouseLocation.intersects(buttonBorders2[2])) {
					System.out.println("DEFEND");
					defending = true;
				}
				
				canChoose = false;
				fightTimer=0;
			}
			
			
			for(int i=0; i<enemyBorders.length; i++) {
				if(mouseLocation.intersects(enemyBorders[i])) {
					enemySelected = i;
				}
			}
				
			
		}
		
	}

	public void fightTimer() {
		if((fightTimer<=250))
			fightTimer++;
		if(fightTimer==250)
			canChoose = true;
		
	}
	
	
	public void createEnemies() {
		int howMany = (int) (Math.random()*4)+1;
		enemies = new Enemy[howMany];
		enemyBorders = new Rectangle[howMany];
		for(int i=0; i<howMany; i++) {
			enemies[i] = new Enemy("rat");
			enemyBorders[i] = new Rectangle(100+(180*i), 100, 160, 160);
		}
	}
	
	public void attack() {
		enemies[enemySelected].modHP(-frame.player1.equipped.damage);
		if(enemies[enemySelected].getHP()<=0)
			enemies[enemySelected]=null;
	}
	
	public boolean isFinished() {
		boolean finished = true;
		for(int i=0; i<enemies.length; i++) {
			if(enemies[i]!=null)
				finished = false;
		}
		return finished;
	}
	
	
	public void enemyLogic() {
		mt++;
		for(int i=0; i<enemies.length; i++) {
			if(enemies[i]!=null) {
				if(mt%3==0) {
					if(enemies[i].AP < enemies[i].maxAP)
						enemies[i].AP++;
				}
			}
		}
		
		for(int i=0; i<enemies.length; i++) {
			if(enemies[i]!=null) {
				if(mt%3==0) {
					if(enemies[i].AP==enemies[i].maxAP) {
						int tempChance = (int) (Math.random()*50) + 1;
						if(tempChance==1) {
							if(defending)
								defending = false;
							else {
								frame.player1.modHP(-(enemies[i].strength));
								context = enemies[i].type + " " + (i+1) + " dealt " + enemies[i].strength + " damage!";
							}
							enemies[i].AP = 0;
						}
					}
				}
			}
		}
		
		
	}
	
	public int getID() {
		return 102;
	}
	
}