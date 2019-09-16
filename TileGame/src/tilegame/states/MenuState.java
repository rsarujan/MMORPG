package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.ui.ClickListener;
import tilegame.ui.UIImageButton;
import tilegame.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;

	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.play_button, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
			}}));
	}

	@Override
	public void tick() {

		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(handler.getMouseManager().getMouseX(),handler.getMouseManager().getMouseY(),15,15);
		uiManager.render(g);
	}
	
}
