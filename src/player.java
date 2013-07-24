import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class player
{
	private int x = 400;
	private int y = 200;
	private int HP = 10;
	private int maxHP = 10;
	public int thirst = 100;
	public int maxThirst = 100;
	public int stamina = 10;
	public int maxStamina = 10;
	public int width = 32;
	public int height =48;
	public String icon, current, down1, down2, left1, left2, right1, right2, up1, up2, status;
	public Inventory inventory;
	public boolean canGoUp = true;
	public boolean canGoDown = true;
	public boolean canGoLeft = true;
	public boolean canGoRight = true;
	public boolean facingLeft = false;
	public boolean facingRight = false;
	public boolean facingDown = false;
	public boolean facingUp = false;
	public Weapon equipped = new Weapon("Stick", 1, 17);
	public Rectangle border = new Rectangle(0,0,0,0);
	public Rectangle interactionBox = new Rectangle(0,0,0,0);
	public Rectangle left = new Rectangle(0,0,0,0);
	public Rectangle bottom = new Rectangle(0,0,0,0); 
	public Rectangle right = new Rectangle(0,0,0,0); 
	public Rectangle up =  new Rectangle(0,0,0,0);
	private int pace = 0;
	public int speed = 3;

	
	
	public player()
	{
		System.out.println("Player created.");
		inventory = new Inventory(this);
		inventory.weapons.add(new Weapon("Stick", 1, 30));
		icon = "res/player/temp.png";
		down1 = "res/player/down1.png";
		down2 = "res/player/down2.png";
		left1 = "res/player/left1.png";
		left2 = "res/player/left2.png";
		right1= "res/player/right1.png";
		right2= "res/player/right2.png";
		up1 = "res/player/up1.png";
		up2 = "res/player/up2.png";
		status = "Healthy";
		
		current = down1;

	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.yellow);
		//g.fillRect(x, y, width, height);
		border = new Rectangle(x, y+(height/2), width, height/2);
		left = new Rectangle(border.getX(),border.getY()+5,5,border.getHeight()-10);
		bottom =  new Rectangle(border.getX()+5,border.getY()+border.getHeight()-5,border.getWidth()-10,5);
		right = new Rectangle(border.getX()+border.getWidth()-5,border.getY()+5, 5,border.getHeight()-10);
		up =  new Rectangle(border.getX()+5,border.getY(),border.getWidth()-10,5);
		if(facingUp)
			interactionBox = new Rectangle(x, y+height, width, height);
		if(facingDown)
			interactionBox = new Rectangle(x, y-height, width, height);
		if(facingLeft)
			interactionBox = new Rectangle(x-width, y, width, height);
		if(facingRight)
			interactionBox = new Rectangle(x+width, y, width, height);
			
		
//		g.drawRect(interactionBox.getX(), interactionBox.getY(), interactionBox.getWidth(), interactionBox.getHeight());
//		g.setColor(Color.orange);
//		g.fillRect(border.getX(), border.getY(), border.getWidth(), border.getHeight());
//		g.setColor(Color.white);
//		g.drawRect(left.getX(), left.getY(), left.getWidth(), left.getHeight());
//		g.drawRect(up.getX(), up.getY(), up.getWidth(), up.getHeight());
//		g.drawRect(bottom.getX(), bottom.getY(), bottom.getWidth(), bottom.getHeight());
//		g.drawRect(right.getX(), right.getY(), right.getWidth(), right.getHeight());
		
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int loc) {
		x = loc;
	}
	
	public void setY(int loc) {
		y = loc;
	}

	
	public void moveLeft(int mod) {
		if(canGoLeft) {
			paceLeft();
			x -= mod;
			if(x<=0)
				x = 0;
		}
		facingLeft = true;
		facingRight=false;
		facingDown =false;
		facingUp  = false;
	}
	
	public void moveRight(int mod) {
		if(canGoRight) {
			paceRight();
			x += mod;
			if(x>=968)
				x=968;
		}
		facingLeft =false;
		facingRight=true;
		facingDown =false;
		facingUp  = false;
	}
	
	public void moveUp(int mod) {
		if(canGoUp) {
			paceDown();
			y += mod;
			if(y>=(600-frame.player1.height))
				y=600-(frame.player1.height);
		}
		facingLeft =false;
		facingRight=false;
		facingDown =false;
		facingUp  = true;
	}
	
	public void moveDown(int mod) {
		if(canGoDown) {
			paceUp();
			y-= mod;
			if(y<=0)
				y=0;
		}
		facingLeft =false;
		facingRight=false;
		facingDown =true;
		facingUp  = false;
	}
	
	
	public void equip(Weapon w) {
		for(int i=0; i<inventory.weapons.size(); i++) {
			if(w.equals(inventory.weapons.get(i)))
				equipped = w;
		}
	}
	
	public void paceDown() {
		pace++;
		if(pace==8) {
			if(current.equals(down1))
				current = down2;
			else if(current.equals(down2))
				current = down1;
			else
				current = down1;
			pace=0;
		}
	}
	
	public void paceLeft() {
		pace++;
		if(pace==8) {
			if(current.equals(left1))
				current = left2;
			else if(current.equals(left2))
				current = left1;
			else
				current = left1;
			pace=0;
		}
	}
	
	public void paceRight() {
		pace++;
		if(pace==8) {
			if(current.equals(right1))
				current = right2;
			else if(current.equals(right2))
				current = right1;
			else
				current = right1;
			pace=0;
		}
	}
	
	public void paceUp() {
		pace++;
		if(pace==8) {
			if(current.equals(up1))
				current = up2;
			else if(current.equals(up2))
				current = up1;
			else
				current = up1;
			pace=0;
		}
	}
}