package tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.actions.Actions;
import tilegame.entities.Entity;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;
import tilegame.inventory.Inventory;
import tilegame.items.Item;
import tilegame.states.FightState;
import tilegame.states.State;




// Merci elouai pour le sprite du personnage elouai.com

public class Player extends Creature {
	
	//Stats
	private final int baseStr = 5;
	private final int baseAgi=10,baseRes = 10;
	private static int Pexp = 0;
	private int expRequired =  100;
	private int pAp= 6;
	public boolean fighting = false;
	private static Creature ennemy;
	private boolean canAtk=true;
	private boolean eCanAtk=false;

	
	//Inventaire
	private Inventory inventory;
	
	//Animations
	private Animation animDown,animLeft,animUp,animRight, animStatic;
	//AtkTimer pour qu il n y ait pas de difference entre un pc puissant et un pc de l iut
	private long lastAtkTimer, atkCd = 250, atkTimer = atkCd;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.name = "Player";
		this.aggro=false;
		this.level=1;
		this.health = 10000 *this.level;
		this.resistance=baseRes *this.level;
		this.strengh=baseStr *this.level;
		this.agility=baseAgi *this.level;
		this.ap = pAp;
		
		System.out.println("Player hp : "+this.health);
		//Hitbox
		bounds.x=16;
		bounds.y=32;
		bounds.width=32;
		bounds.height=32;
		
		//Animations
		animDown = new Animation(125,Assets.player_down); //temps en ms entre les frames
		animLeft = new Animation(125,Assets.player_left); 
		animUp = new Animation(125,Assets.player_up); 
		animRight = new Animation(125,Assets.player_right); 
		animStatic = new Animation (1000,Assets.player_static);
		
		inventory = new Inventory(handler);
		

		
		
	}

	@Override
	public void tick() {
		//Animations
		
		animDown.tick();
		animLeft.tick();
		animUp.tick();
		animRight.tick();
		
		checkAtk();
		inventory.tick();
		if(fighting) fight();
		

		
		levelUp();
		
		
		//Mouvements
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
	}
	
	private void levelUp() {
		if (Pexp >= expRequired) {
			Pexp-=expRequired;
			this.level++;
			expRequired = this.level * 100;
			System.out.println("You gained a level ! Now level "+ this.level + "exp :" + Pexp);
			this.strengh = baseStr * this.level;
			this.health = 10000 *this.level;
			this.resistance=baseRes *this.level;
			this.strengh=baseStr *this.level;
			this.agility=baseAgi *this.level;
		}

	}
	
	
	private void checkAtk() {
		atkTimer+= System.currentTimeMillis() - lastAtkTimer;
		lastAtkTimer = System.currentTimeMillis();
		if(atkTimer < atkCd) return;
		if(inventory.isActive())return;
		
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle atkRec = new Rectangle();
		int atkRecSize = 20;
		atkRec.width = atkRecSize;
		atkRec.height= atkRecSize;
		
		
		// a quel emplacement creer la boite de collision de l attaque
		
		if(handler.getKeyManager().attackUp) {
			atkRec.x = cb.x + cb.width / 2 - atkRecSize/2;
			atkRec.y = cb.y - atkRecSize;
		}else if (handler.getKeyManager().attackDown){
			atkRec.x = cb.x + cb.width / 2 - atkRecSize/2;
			atkRec.y = cb.y + cb.height;
			
		}else if (handler.getKeyManager().attackLeft){
			atkRec.x = cb.x - atkRecSize;
			atkRec.y = cb.y + cb.height/2 - atkRecSize/2;
			
		}else if (handler.getKeyManager().attackRight){
			atkRec.x = cb.x + cb.width;
			atkRec.y = cb.y + cb.height/2 - atkRecSize/2;
		}else {
			return;
		}
		
		atkTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			//Player peut pas se taper lui meme
			if (e.equals(this)) continue;
			
			
			if (e.getCollisionBounds(0, 0).intersects(atkRec)) {
				if (e.getClass() == (new Goblin().getClass())) {
					System.out.println("this is a goblin");
					ennemy = (Creature) e;
					//FightState.getEnnemy(e);
					//State.setState(handler.getGame().fightState);
					fighting = true;
				}
				else e.hurt(this.strengh);//pour les tests
				return;
			}
		}
		
		
		
		
	}
	
	private void turn(Creature e) {
		//System.out.println( e.getName()+"'s turn ! ");
		while (e.getAp()>1) {
			
		
			if(e == this) {
				//canAtk=true;

				if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y )&& canAtk) {
					ennemy.hurt(this.getStrengh()-ennemy.getResistance());
					this.ap-=6;
					canAtk=false;
					eCanAtk=true;
				
				}
				else return;
			//	return;
				
			
			}
			else if (eCanAtk){
				this.hurt(ennemy.getStrengh()-this.getResistance());
				ennemy.setAp(0);
				System.out.println("Ennemy attacked you !");
				canAtk=true;
				eCanAtk=false;
			}
			else return;
		}
		return;
	}
	private void fight() {
		this.setAp(6);
		ennemy.setAp(6);
		//	System.out.println("1");
		turn(this);
	//	System.out.println("2");
		
		if(ennemy.getHealth()>0 && this.ap<6) {
			turn(ennemy);
			
			
		}
		
	
	if(this.getHealth()>0 && ennemy.getHealth()<=0)State.setState(handler.getGame().gameState);
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(inventory.isActive())return;
		
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	
	
	public void die() {
		System.out.println("You Lose");
		State.setState(handler.getGame().menuState);
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		if(fighting) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 26));

		g.drawImage(Assets.battleground, 0, 0, 650, 500, null);
		g.drawImage(Assets.red, 100, 135, 100,165,null);
		g.drawImage(Assets.goblinFight, 420, 150, 120,150,null);
		g.drawImage(Assets.fightBar, 0, 350, 650,150,null);
		Text.drawString(g, Integer.toString(handler.getWorld().getEntityManager().getPlayer().getHealth()), 390, 385, Color.BLACK);
		Text.drawString(g, Integer.toString(Goblin.getHealth(ennemy)), 580, 385, null);
		Text.drawString(g, Actions.Attack.getName() + ",  Y    " + Actions.Attack.getCost()+ " AP", 10, 380, null);
		Text.drawString(g, Actions.Defend.getName() + ",  U    " + Actions.Defend.getCost()+ " AP", 10, 412, null);
		Text.drawString(g, Actions.Item.getName() + ",  I    " + Actions.Item.getCost()+ " AP", 10, 446, null);
		Text.drawString(g, Actions.Flee.getName()+",  O" , 10, 483, null);
		Text.drawString(g, "Current AP : "+handler.getWorld().getEntityManager().getPlayer().getAp() , 10, 345, null);
		}
	
		
		
		
		//Voir la collision box (Temporaire)
	//	g.setColor(Color.red); 
		//g.fillRect((int)(x+ bounds.x - handler.getGameCamera().getxOffset()),
			//	(int) (y+ bounds.y - handler.getGameCamera().getyOffset()),
				//bounds.width,bounds.height);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove<0) return animLeft.getCurrentFrame();
		else if (xMove>0) return animRight.getCurrentFrame();
		else if (yMove <0) return animUp.getCurrentFrame();
		else if (yMove>0) return animDown.getCurrentFrame();
		else return animStatic.getCurrentFrame();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	

	public static void gainExp(int pexp) {
		Pexp += pexp;
	}

	@Override
	public void setAp(int ap) {
		pAp = ap;
	}

	@Override
	public int getAp() {
		return pAp;
	}
	

}


