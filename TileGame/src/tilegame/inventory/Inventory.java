package tilegame.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;
import tilegame.items.Item;

public class Inventory {
	
	private Handler handler;
	private boolean active=false;
	private ArrayList<Item> inventoryItems;
	
	
	
	//Hardcode positions de l inventaire
	private int invX=64,invY=48,invWidth=512,invHeight=384,
			invListCenterX=invX+40,invListCenterY=invY+invHeight/2 +12,
			invListSpace=30;
	//Positions de la miniature de l item selectionne
	private int invImageX = 450, invImageY = 80,
			invImageWidth = 64, invImageHeight = 64;
	//Positions du compteur d items selectionnes
	private int invCountX = 475, invCountY=180;
	
	private int selectedItem = 0;
	
	
	
	
	public Inventory (Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void tick() {
		//Touche pour activer et desactiver l'inventaire
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) active = !active;
		//Si l'inventaire n'est pas active ne rien faire
		if(!active)return;
		// Changer d objet selectionne dans l inventaire
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) selectedItem++;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) selectedItem--;
		
		//Check erreurs potentielles pendant la selection d objet
		if(selectedItem <0) selectedItem=inventoryItems.size()-1;
		else if (selectedItem >= inventoryItems.size())selectedItem = 0;
		
		
		
		
		
		//Code pour afficher le contenu de l inventaire en console // Test
		//for(Item i : inventoryItems) System.out.println(i.getName() + "   " + i.getCount());
		
		
	}
	
	
	
	public void render(Graphics g) {
		if(!active) return;
		
		g.drawImage(Assets.inventoryScreen,invX,invY,invWidth,invHeight,null);
		
		
		int len = inventoryItems.size();
		if(len==0)return;
		g.setFont(new Font("TimesRoman", Font.BOLD, 28));
		for(int i = -5; i<6; i++) {
			if(selectedItem +i <0 || selectedItem +i>= len) continue;
			if(i==0)Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpace, Color.RED);
			else	Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpace, Color.WHITE);
		}
		
		Item item= inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, Color.WHITE);
	}
	
	// methodes de l inventaire
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if (i.getId()== item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	
	
	
	
	//Getters et Setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

}
