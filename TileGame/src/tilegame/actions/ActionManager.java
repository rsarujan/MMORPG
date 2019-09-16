package tilegame.actions;

import java.util.ArrayList;

import tilegame.Handler;


public class ActionManager {
	private Handler handler;
	private ArrayList<Actions> actions;
	
	public ActionManager(Handler handler){
		this.handler = handler;
		actions = new ArrayList<Actions>();
	}

}
