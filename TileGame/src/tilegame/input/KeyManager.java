package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys,justPressed, cantPress;
	public boolean up, down, left, right;
	public boolean attackUp, attackDown , attackRight,attackLeft;
	public boolean attack, defend, item, flee;
	
	public KeyManager(){
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick(){
		for(int i = 0; i< keys.length;i++ ) {
			if(cantPress[i] && !keys[i]) cantPress[i]=false;
			
			else if (justPressed[i]) {
				cantPress[i]= true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && keys[i]) justPressed[i] = true;
		//	System.out.println("test");
			
		}
		
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
		attackUp = keys[KeyEvent.VK_Z];
		attackDown = keys[KeyEvent.VK_S];
		attackRight = keys[ KeyEvent.VK_D];
		attackLeft = keys[ KeyEvent.VK_Q];
		
		//Actions en combat
	/*	attack= keys[KeyEvent.VK_Y];
		defend = keys[KeyEvent.VK_U];
		item = keys[KeyEvent.VK_I];
		flee = keys[KeyEvent.VK_O];
		*/
		
	}
	
	
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)return false;
		
		return justPressed[keyCode];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()<0 || e.getKeyCode()> keys.length) return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()<0 || e.getKeyCode()> keys.length) return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
