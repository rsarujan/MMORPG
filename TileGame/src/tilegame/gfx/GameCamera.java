package tilegame.gfx;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;
	
	public GameCamera(Handler handler, float xOffset, float yOffset){
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	//Si l'espace affiche est plus petit que celui qui est defini
	//on affiche uniquement ce qui est defini

	public void checkBlankSpace() {
		if (xOffset<0) xOffset=0;// cote gauche
		                         //cote droit
		else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
			
		if (yOffset<0) yOffset=0;// cote haut
		                         //cote bas
		else if(yOffset > (handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight())){
			yOffset = (handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight());
		}
	}
	
	
	public void centerOnEntity(Entity e){
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
