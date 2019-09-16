package tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font_size28;
	
	public static BufferedImage  dirt, grass, stone, tree,wood;
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_right;
	public static BufferedImage[] player_static;
	public static BufferedImage[] play_button;
	public static BufferedImage inventoryScreen;
	public static BufferedImage sword;
	public static BufferedImage goblin;
	public static BufferedImage battleground;
	public static BufferedImage red;
	public static BufferedImage goblinFight;
	public static BufferedImage fightBar;


	public static void init(){
		
		//font_size28=FontLoader.loadFont("/fonts/slkscr.ttf", 28);
		
		
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
		SpriteSheet trees = new SpriteSheet(ImageLoader.loadImage("/textures/trees.png"));
		SpriteSheet play = new SpriteSheet(ImageLoader.loadImage("/textures/play.png"));
		SpriteSheet woodLoot = new SpriteSheet(ImageLoader.loadImage("/textures/wood.png"));
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		sword=ImageLoader.loadImage("/textures/Excalibur.png");
		goblin = ImageLoader.loadImage("/textures/goblin.png");
		battleground= ImageLoader.loadImage("/textures/battleback.png");
		red = ImageLoader.loadImage("/textures/red.png");
		goblinFight = ImageLoader.loadImage("/textures/goblinfight.png");
		fightBar = ImageLoader.loadImage("/textures/fightbar.png");

		
		//Animations du joueur
		
		player_static= new BufferedImage[1];
		player_static[0] = player.crop(width * 0, height * 2, width, height);
		
		player_down = new BufferedImage[6];
		player_down[0]= player.crop(width * 1, height *2, width, height);
		player_down[1]= player.crop(width * 2, height *2, width, height);
		player_down[2]= player.crop(width * 3, height *2, width, height);
		player_down[3]= player.crop(width * 4, height *2, width, height);
		player_down[4]= player.crop(width * 3, height *2, width, height);
		player_down[5]= player.crop(width * 2, height *2, width, height);
		
		player_left= new BufferedImage[6];
		player_left[0]= player.crop(width * 1, height *3, width, height);
		player_left[1]= player.crop(width * 2, height *3, width, height);
		player_left[2]= player.crop(width * 3, height *3, width, height);
		player_left[3]= player.crop(width * 4, height *3, width, height);
		player_left[4]= player.crop(width * 3, height *3, width, height);
		player_left[5]= player.crop(width * 2, height *3, width, height);
		
		player_up= new BufferedImage[6];
		player_up[0]= player.crop(width * 1, height *4, width, height);
		player_up[1]= player.crop(width * 2, height *4, width, height);
		player_up[2]= player.crop(width * 3, height *4, width, height);
		player_up[3]= player.crop(width * 4, height *4, width, height);
		player_up[4]= player.crop(width * 3, height *4, width, height);
		player_up[5]= player.crop(width * 2, height *4, width, height);
		
		player_right= new BufferedImage[6];
		player_right[0]= player.crop(width * 1, height *5, width, height);
		player_right[1]= player.crop(width * 2, height *5, width, height);
		player_right[2]= player.crop(width * 3, height *5, width, height);
		player_right[3]= player.crop(width * 4, height *5, width, height);
		player_right[4]= player.crop(width * 3, height *5, width, height);
		player_right[5]= player.crop(width * 2, height *5, width, height);
		
		
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = trees.crop(width * 0, 0, width *2 , height *2 );
		wood = woodLoot.crop(width * 0, 0, width, height);
		
		play_button = new BufferedImage[2];
		play_button[0] = play.crop(width * 0, 0, width *2 , height *1 );
		play_button[1] = play.crop(width * 0, height, width*2 , height);
	}
	
}
