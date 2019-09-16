package tilegame.worlds;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.creatures.Goblin;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Tree;
import tilegame.items.Item;
import tilegame.items.ItemManager;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	// Entites
	private EntityManager entityManager;
	
	//Items
	private ItemManager itemManager;
	
	
	public World(Handler handler, String path){
		this.handler = handler;
		// Mettre les entites dans le monde
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		//Gen arbres
		entityManager.addEntity(new Tree(handler, 100, 200));
		entityManager.addEntity(new Tree(handler, 400, 370));
		entityManager.addEntity(new Tree(handler, 620, 230));
		entityManager.addEntity(new Tree(handler, 500, 450));
		entityManager.addEntity(new Tree(handler, 250, 600));
		
		
		//Gen goblins
		entityManager.addEntity(new Goblin(handler, 400, 500));
		entityManager.addEntity(new Goblin(handler, 700, 250));
		entityManager.addEntity(new Goblin(handler, 1000, 1000));
		entityManager.addEntity(new Goblin(handler, 400, 900));
		entityManager.addEntity(new Goblin(handler, 100, 850));
		entityManager.addEntity(new Goblin(handler, 770, 720));
		entityManager.addEntity(new Goblin(handler, 950, 430));
		entityManager.addEntity(new Goblin(handler, 800, 800));
		
		//Hardcode sword
		getItemManager().addItem(Item.sword.createNew(300,200));
		
		
		// Ne plus mettre d entites
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick(){
		entityManager.tick();
		itemManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Items
		itemManager.render(g);
		
		//Enbtites
		entityManager.render(g);
	}
	
	
	
	public Tile getTile(int x, int y){
		
		//Si le joueur sort de la map, dis au jeu qu'il est sur un bout d'herbe pour ne pas crash le jeu
		if(x<0 || y<0 || x>=width || y >=height) return Tile.grassTile;
		
		
		
		
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	//Definir les limites du monde pour ne pas avoir une zone en dehors de la map a l ecran
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	
	
}








