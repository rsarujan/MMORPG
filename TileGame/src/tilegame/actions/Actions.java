package tilegame.actions;

import tilegame.Handler;

public class Actions {
	
	public static Actions[] actions= new Actions[4];
	public static Actions Attack = new Actions ("Attack", 6 , 0);
	public static Actions Defend = new Actions ("Defend", 3 , 1);
	public static Actions Item = new Actions ("Item", 2 , 2);
	public static Actions Flee = new Actions ("Flee", 0 , 3);
	
	
	//Classe 
	protected Handler handler;
	protected String name;
	protected final int id;
	protected int cost;
	
	
	
	public Actions (String name, int cost, int id) {
		
		this.name=name;
		this.id=id;
		this.cost = cost;
		
		
		actions[id]=this;
	}



	public int getCost() {
		return cost;
	}



	public static Actions[] getActions() {
		return actions;
	}



	public static void setActions(Actions[] actions) {
		Actions.actions = actions;
	}



	public static Actions getAttack() {
		return Attack;
	}



	public static Actions getDefend() {
		return Defend;
	}




	public static Actions getItem() {
		return Item;
	}


	public static Actions getFlee() {
		return Flee;
	}




	public Handler getHandler() {
		return handler;
	}



	public String getName() {
		return name;
	}




	public int getId() {
		return id;
	}
	

	

}
