package tilegame.entities.creatures;

import java.awt.Graphics;
import java.util.Random;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.gfx.Assets;
import tilegame.states.State;

public class Goblin extends Creature{
	

	private static Random randomGenerator = new Random();
	private static int GexpLoot;
	private static int gAp = 6;
	private static final int baseStr = 5;

	public Goblin(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		this.level = randomGenerator.nextInt(5)+1;
		//valeurs v=differentes des stats "base" pour les tests
		GexpLoot = this.level * 150;
		this.health=this.level *10000;
		this.agility =this.level * 3;
		this.resistance =this.level * 2;
		this.strengh=this.level *5;
		this.ap = gAp;
		this.aggro=true;
		this.name = "Goblin";
		bounds.x=10;
		bounds.y=(int) (height / 1.5f);
		bounds.width=width -10;
		bounds.height = (int) (height - height / 1.5f);

		
	}

	public Goblin() {
		super(null,10,10,DEFAULT_CREATURE_WIDTH,DEFAULT_CREATURE_HEIGHT);
		setActive(false);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.goblin,(int) (x-handler.getGameCamera().getxOffset()) ,
				(int) (y-handler.getGameCamera().getyOffset()) ,width,height,null);
	}

	@Override
	public void die() {
		System.out.println("You killed a Goblin ! Earned : "+ GexpLoot);
		handler.getWorld().getEntityManager().getPlayer();
		Player.gainExp(GexpLoot);
		System.out.print("Goblin level :"+this.level);
		State.setState(handler.getGame().gameState);
	}
	
	public static int getHealth(Entity ennemy) {
		return ennemy.getHealth();
	}

	@Override
	public void setAp(int ap) {
		gAp = ap;
	}

	@Override
	public int getAp() {
		return gAp;
	}

}
