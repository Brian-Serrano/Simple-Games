package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Window;
import Util.Variables;

public class Ball {
	public int x, y, width, height, directionX, velocityY;
	public int[] velocityX = {-8, 8};
	public Window window;
	public Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK, Color.MAGENTA, Color.ORANGE, Color.CYAN};

	public Ball(Window window) {
		this.window = window;
		this.width = 25;
		this.height = 25;
		this.x = (window.getWidth() - this.width) / 2;
		this.y = 50;
		this.directionX = (int) Math.round(Math.random());
		this.velocityY = 8;
	}

	public void update() {
		x += velocityX[directionX];
		y += velocityY;
		
		if (x + width > window.getWidth() || x < 0) {
			velocityX[directionX] = -velocityX[directionX];
		}
		
		if (y + height > window.getHeight() || y < 0) {
			velocityY = -velocityY;
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(colors[Variables.selectedColor]);
	    g2.fillOval(x, y, width, height);
	}
}
