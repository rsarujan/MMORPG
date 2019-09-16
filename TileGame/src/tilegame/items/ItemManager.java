package tilegame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import tilegame.Handler;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler){
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	public void tick(){
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			
			Item i = it.next();
			i.tick();
			if(i.isPickedUp()) {
				if(i==Item.sword) {
					handler.getWorld().getEntityManager().getPlayer().setStrengh
					(handler.getWorld().getEntityManager().getPlayer().getStrengh()+15);
					System.out.println("You got a sword ! It's very sharp so your attack power got a bonus of 15");
				}
			
				it.remove();
			}
		}
	}
	
	public void render(Graphics g){
		for(Item i : items)
			i.render(g);
	}
	
	public void addItem(Item i){
		i.setHandler(handler);
		items.add(i);
	}
	
	// Getters and Setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}