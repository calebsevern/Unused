import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Enemy
{
	private int x = 200;
	private int y = 200;
	private int HP = 80;
	private int maxHP = 100;
	public int AP = 0;
	public int maxAP = 100;
	public int width = 32;
	public int height =64;
	public String icon;
	public Inventory inventory;
	public boolean moveLeft, moveRight, moveDown, moveUp;
	public Weapon equipped = new Weapon("Stick", 1, 2);
	public int strength = 1;
	public String type = "";
	
	public Enemy(String type)
	{
		if(type.equalsIgnoreCase("rat")) {
			this.type = "Wild rat";
			icon = "res/enemy/rat.png";
			HP = 40;
			maxHP = 40;
			strength = 1;
		}
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
		
	}
	
	
	
	
	
	public void modHP(int mod) {
		HP += mod;
	}
	
	public int getHP() {
		return HP;
	}
	
	public int getMaxHP() {
		return maxHP;
	}

}