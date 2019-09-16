package tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import tilegame.Handler;

public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected int health;
	public static final int DEFAULT_HEALTH = 50;
	protected boolean active = true;
	public boolean aggro;
	protected int ap;
	
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		
		//Collision box 
		bounds = new Rectangle(0,0,width,height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt (int dmg) {
		if (dmg <=0)dmg = 1;
		System.out.println(dmg +" damage dealt");
		health -=dmg;
		if (health <=0) {
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollision(float xOffset, float yOffset) {
		//Verifie si il y a collision entre toutes les entitees
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			// ne check pas collision avec lui meme
			if(e.equals(this)) continue; 
			if(e.getCollisionBounds(0f , 0f ).intersects(getCollisionBounds(xOffset, yOffset))) return true;
		}
		return false;
	}
	
	
	
	public Rectangle getCollisionBounds(float xOffset,float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset),
				bounds.width , bounds.height);
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract int getAp();

	public abstract void setAp(int ap);
	
}
