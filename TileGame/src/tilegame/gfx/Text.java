package tilegame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {
	
	public static void drawString(Graphics g, String text, int xPos, int yPos,Color c) {
		g.setColor(c);
		
		int x= xPos;
		int y = yPos;
		g.drawString(text, x, y);
	}

}
