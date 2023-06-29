package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import Util.Variables;

public class Tile {
	public int x, y, number, size, distance;
	public Font font;
	public boolean delete;
	public Color[] colors;
	
	public Tile(int y, int x, int number, Color[] colors) {
		this.colors = colors;
		this.size = Variables.WINDOW_WIDTH / 14;
		this.x = x * this.size;
		this.y = (y * this.size) - (this.size * 7);
		this.number = number;
		this.distance = size / 2;
		this.delete = false;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(colors[Variables.selectedBlock]);
		g2.fill(new RoundRectangle2D.Double(x, y, size, size, 15, 15));
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Impact", Font.PLAIN, 20));
        String tileNumber = Integer.toString(number);
        FontMetrics fontMetrics = g2.getFontMetrics();
        int textX = x + ((size - fontMetrics.stringWidth(tileNumber)) / 2);
    	int textY = y + ((size - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
    	g2.drawString(tileNumber, textX, textY);
	}
	
	public boolean isCollidedWithCannon(Cannon cannon) {
		return (
				cannon.hitboxX + cannon.hitboxWidth > x &&
				cannon.hitboxX < x + size &&
				cannon.hitboxY + cannon.hitboxHeight > y &&
				cannon.hitboxY < y + size
            );
	}
	
	public boolean isCollidedWithBottomOfWindow() {
		return (y + size > Variables.WINDOW_HEIGHT);
	}
}
