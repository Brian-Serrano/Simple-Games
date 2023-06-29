package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	public int x, y, size, rotation, lineX1, lineY1, lineX2, lineY2;
	public BufferedImage image;
	public boolean drawLine;
	
	public Tile(int size, int y, int x, BufferedImage image, int rotation, int height, int lineX1, int lineY1, int lineX2, int lineY2) {
		this.size = size;
		this.x = x * this.size;
		this.y = y * this.size + height;
		this.image = image;
		this.rotation = rotation;
		this.lineX1 = lineX1 + this.x;
		this.lineY1 = lineY1 + this.y;
		this.lineX2 = lineX2 + this.x;
		this.lineY2 = lineY2 + this.y;
		this.drawLine = true;
	}
	
	public Tile(int size, int y, int x, BufferedImage image, int rotation, int height) {
		this.size = size;
		this.x = x * this.size;
		this.y = y * this.size + height;
		this.image = image;
		this.rotation = rotation;
		this.drawLine = false;
	}
	
	public void draw(Graphics2D g2) {
		g2.rotate(Math.toRadians(rotation * 90), x + (size / 2), y + (size / 2));
		g2.drawImage(image, x, y, size, size, null);
		g2.rotate(-(Math.toRadians(rotation * 90)), x + (size / 2), y + (size / 2));
		if(drawLine) {
			g2.setColor(Color.white);
			g2.drawLine(lineX1, lineY1, lineX2, lineY2);
		}
	}
}
