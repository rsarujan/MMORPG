package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	
	private int speed, index;
	private long lastTime,timer;
	private BufferedImage[]frames;
	
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames=frames;
		index = 0;
		timer = 0;
		lastTime=System.currentTimeMillis();
	}
	
	//Connaitre le temps ecoule entre 2 appels de la fonction tick
	public void tick() {
		timer+=System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if (timer>speed) {
			index++;
			timer=0;
			//Reset la frame a appeler si la precedente etait la derniere de l image
			if (index>=frames.length) index=0; 
		}
		
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

}
