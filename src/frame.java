import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class frame extends StateBasedGame
{
	public static final String gamename = "UNUSED";
	public static player player1 = new player();
	public static int time = 0;
	public static int inc = 0;
	public static int prevState=0;
	public static final int menu = 0;
	public static final int intro = 1;
	public static final int play = 2;
	public static final int scene01 = 3;
	public static final int scene02 = 4;
	public static final int scene03 = 5;
	public static final int scene04 = 6;
	public static final int scene05 = 7;
	public static final int scene06 = 8;
	public static final int scene07 = 9;
	public static final int pause = 100;
	public static final int base = 101;
	public static final int battle = 102;
	private static boolean up = true;
	private static int rainCounter = 0;
	public static Image rain1, rain2, currentRain;
	public static float darkness = 0;
	
	public frame(String gamename) {
		super(gamename);
		this.addState(new MainMenu(menu));
		this.addState(new Intro(intro));
		this.addState(new Play(play));
		this.addState(new Scene01(scene01));
		this.addState(new Scene02(scene02));
//		this.addState(new Scene03(scene03));
//		this.addState(new Scene04(scene04));
//		this.addState(new Scene05(scene05));
//		this.addState(new Scene06(scene06));
//		this.addState(new Scene07(scene07));

		this.addState(new Pause(pause));
		this.addState(new baseScene(base));
		this.addState(new Battle(battle));
	}
	
	
	public void initStatesList(GameContainer gc) throws SlickException {
		getState(menu).init(gc, this);
		getState(intro).init(gc, this);
		getState(play).init(gc, this);
		getState(scene01).init(gc, this);
		getState(scene02).init(gc, this);
//		getState(scene03).init(gc, this);
//		getState(scene04).init(gc, this);
//		getState(scene05).init(gc, this);
//		getState(scene06).init(gc, this);
//		getState(scene07).init(gc, this);

		getState(pause).init(gc, this);
		getState(base).init(gc, this);
		getState(battle).init(gc, this);
		enterState(menu);
	}
	
	public static void main(String[] args)
	{
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new frame(gamename));
			appgc.setDisplayMode(1000, 600, false);
			appgc.setShowFPS(false);
			appgc.start();
		} catch(SlickException e){}
	}
	
	
	public static void dayCycleLogic() {
		inc++;
		if(inc==20) {
			if(up)
				time++;
			else if(!up)
				time--;
			inc = 0;
			if(time==300)
				up = false;
			else if(time==0)
				up = true;
		}
	}
	
		public static void rain() {
		rainCounter++;
		if(rainCounter==5) {
			if(currentRain.equals(rain1))
				currentRain = rain2;
			else if(currentRain.equals(rain2))
				currentRain = rain1;
			rainCounter = 0;
		}
	}
		
	public static void initImages() throws SlickException {
		rain1 = new Image("res/Scene/rain1.png");
		rain2 = new Image("res/Scene/rain2.png");
		currentRain = rain1;
	}
	
}