package tilegame.entities.creatures;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public abstract class Creature extends Entity {
	

	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;
	protected int strengh;
	protected int resistance;
	protected int agility;
	protected int level;
	protected String name;

	
	

	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		if (!checkEntityCollision(xMove, 0f)) moveX();
		if (!checkEntityCollision(0f, yMove)) moveY();
		
	}
	
	public void moveX() {
		if (xMove >0) {    ////Deplacement vers la droite
			int tx=(int) (x+ xMove + bounds.x + bounds.width)/ Tile.TILEWIDTH;
			//Verifie les coins haut droite et bas droite
			if(!collisionWithTile(tx,  (int) (y+ bounds.y)/Tile.TILEHEIGHT)&&
					!collisionWithTile(tx,  (int) (y+ bounds.y + bounds.height)/Tile.TILEHEIGHT)) x+=xMove;
			else x = tx * Tile.TILEWIDTH - bounds.x - bounds.width -1;
				
			
		}else if(xMove <0) { ///vers la gauche
			int tx=(int) (x+ xMove + bounds.x)/ Tile.TILEWIDTH;
			//Verifie les coins haut gauche et bas gauche
			if(!collisionWithTile(tx,  (int) (y+ bounds.y)/Tile.TILEHEIGHT)&&
					!collisionWithTile(tx,  (int) (y+ bounds.y + bounds.height)/Tile.TILEHEIGHT)) x+=xMove;
			else x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
		}
	}
	
	public void moveY() { //Descend
		if (yMove < 0) {
			
			int ty= (int) (y + yMove + bounds.y)/Tile.TILEHEIGHT;
			
			//////////Verifie les coins haut gauche et haut droite
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) y+=yMove;
			else y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			
			
		}else if(yMove > 0) { //Monte
			
			int ty= (int) (y + yMove + bounds.y + bounds.height)/Tile.TILEHEIGHT;
			
			//////////Verifie les coins bas gauche et bas droite
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))y += yMove;
			else y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
		}
			
		
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
	
	//GETTERS SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getStrengh() {
		return strengh;
	}

	public void setStrengh(int strengh) {
		this.strengh = strengh;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}
	

	
}
