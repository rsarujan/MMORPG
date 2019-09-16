package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import tilegame.Handler;
import tilegame.actions.Actions;
import tilegame.entities.Entity;
import tilegame.entities.creatures.Creature;
import tilegame.entities.creatures.Goblin;
import tilegame.entities.creatures.Player;
import tilegame.gfx.Assets;
import tilegame.gfx.Text;
import tilegame.input.KeyManager;

public class FightState extends State{
	private static Creature ennemy;
	private static Player player;
	private boolean ready = false;
	KeyManager keyManager;
	//private JPanel panel = new JPanel();
	//int fps = 10;
	//double timePerTick = 1000000000 / fps;
	//double delta = 0;
//	long now;
//	long lastTime = System.nanoTime();
	//long timer = 0;
//	int ticks = 0;
	

	public FightState(Handler handler) {
		super(handler);
		
		
	}

	@Override
	public void tick() {
		player =handler.getWorld().getEntityManager().getPlayer();
		if (ready) {
			
			
		
			//	System.out.println("1");
				turn(player);
			//	System.out.println("2");
				player.setAp(6);
				if(ennemy.getHealth()>0) {
					turn(ennemy);
					ennemy.setAp(6);
				}
			
			if(player.getHealth()>0 && ennemy.getHealth()<=0)State.setState(handler.getGame().gameState);
		}
		
		
		
		
	}

	@Override
	public void render(Graphics g) {
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
		if (!ready) {
			ready = !ready;

		}
		Text.drawString(g, "Current AP : "+handler.getWorld().getEntityManager().getPlayer().getAp() , 10, 345, null);

	}
	
	private void turn(Creature e) {
		System.out.println( e.getName()+"'s turn ! ");
		while (e.getAp()>1) {
			
		
			if(e == player) {

			//	if(handler.getKeyManager().attack) {
					ennemy.hurt(player.getStrengh()-ennemy.getResistance());
					player.setAp(player.getAp()-Actions.Attack.getCost());
			//	}
			//	return;
				
			
			}
			else {
				player.hurt(ennemy.getStrengh()-player.getResistance());
				ennemy.setAp(0);
				System.out.println("Ennemy attacked you !");
			}
		}
		return;
	}
	
	
	
	
	public static void getEnnemy(Entity e) {
		ennemy= (Creature)e;
	}
	

}



	

