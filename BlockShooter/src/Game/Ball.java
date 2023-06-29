package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Util.Variables;

public class Ball {
	public int size, speed;
	public double x, y, velX, velY;
	public boolean delete;
	public String vertical, horizontal;
	public Color[] colors;

	public Ball(double x, double y, double velX, double velY, String vertical, String horizontal, Color[] colors) {
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.size = 12;
		this.x = x - (size / 2);
		this.y = y - (size / 2);
		this.velX = velX;
		this.velY = velY;
		this.speed = 10;
		this.colors = colors;
		this.delete = false;
	}

	public void update() {
		
		if(y > Variables.WINDOW_HEIGHT) {
			delete = true;
		}
		
		if(vertical == "bottom") {
			y += velY * speed;
		} else if(vertical == "top") {
			y -= velY * speed;
		}
		
		if(horizontal == "right") {
			x += velX * speed;
		} else if(horizontal == "left") {
			x -= velX * speed;
		}
		
		if(x + size > Variables.WINDOW_WIDTH) {
			horizontal = "left";
		}
		if(x < 0) {
			horizontal = "right";
		}
		if(y < 0) {
			vertical = "bottom";
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(colors[Variables.selectedBall]);
		g2.fillOval((int)x, (int)y, size, size);
	}
}
