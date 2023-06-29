package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Util.Variables;

public class Ball {
	public int x, y, size, velX, velY, obstacleCreation;
	public List<Particle> particles;
	public Color color;
	
	public Ball() {
		this.particles = new ArrayList<Particle>();
		this.color = Variables.ballColor;
		this.size = 25;
		this.x = Variables.WINDOW_WIDTH / 2 - 100;
		this.y = Variables.WINDOW_HEIGHT - 100;
		this.velX = 8;
		this.velY = 8;
		this.obstacleCreation = -700;
	}
	
	public void update() {
		obstacleCreation -= velY;
		y -= velY;
		x += velX;
		
		particles.add(new Particle(x + (int) (Math.random() * 20 - 10) + this.size / 2, y + (int) (Math.random() * 20 - 10) + this.size / 2));
		particles.add(new Particle(x + (int) (Math.random() * 20 - 10) + this.size / 2, y + (int) (Math.random() * 20 - 10) + this.size / 2));
		for(Particle p : particles) {
			p.update();
		}
		particles = particles.stream().filter(particle -> !particle.delete).collect(Collectors.toList());
	}
	
	public void draw(Graphics2D g2) {
		for(Particle p : particles) {
			p.draw(g2);
		}
        g2.setColor(color);
		g2.fillOval(x - size, y - size, size * 2, size * 2);
	}
	
	public void switchDirection() {
		velX = -velX;
		Variables.score++;
	}
}
