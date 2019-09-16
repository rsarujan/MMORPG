package tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import tilegame.Handler;
import tilegame.entities.creatures.Player;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderOrder = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			
			if(a.getY()+ a.getHeight() < b.getY()+ b.getHeight())return -1;
			return 1;
		}
		
	};
	
	
	public EntityManager(Handler handler, Player player) {
		
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		
	}
	
	public void tick() {
		
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.tick();
			if (!e.isActive()) it.remove();;
		}
		entities.sort(renderOrder);
	}
	
	
	public void render(Graphics g) {
		if (!handler.getWorld().getEntityManager().getPlayer().fighting) {
		for(Entity e : entities) {
			e.render(g);
		}
		player.postRender(g);
		}
		else handler.getWorld().getEntityManager().getPlayer().render(g);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	//getters et setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
	
	

}
