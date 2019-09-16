package tilegame.entities.statics;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.gfx.Assets;
import tilegame.items.Item;
import tilegame.tiles.Tile;

public class Tree extends StaticEntity{



	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH *2, Tile.TILEHEIGHT *2);
		// Collision box de l arbre
		bounds.x=45;
		bounds.y=(int) (height / 1.2f);
		bounds.width=width -90;
		bounds.height = (int) (height - height / 1.2f);
		this.aggro = false;
	}

	public void tick() {
		
	}
	
	public void die() {
		System.out.print("You Smashed a tree !");
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x+width/2), (int)(y+ height/2)));
		handler.getWorld().getEntityManager().getPlayer();
		Player.gainExp(100);
	}

	public void render(Graphics g) {
		g.drawImage(Assets.tree,(int) (x-handler.getGameCamera().getxOffset()) ,
				(int) (y-handler.getGameCamera().getyOffset()) ,width,height,null);
	}

	@Override
	public int getAp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAp(int ap) {
		// TODO Auto-generated method stub
		
	}

}
