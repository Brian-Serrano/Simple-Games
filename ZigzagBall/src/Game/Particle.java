package Game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {
    private int x, y, size, particleTimer, particleInterval;
    private Color color;
    public boolean delete;
    
    public Particle(int x, int y) {
        this.size = 20;
    	this.x = x - this.size;
    	this.y = y - this.size;
        this.color = new Color(255, 255, 255, 100);
        this.delete = false;
		this.particleTimer = 0;
		this.particleInterval = (int) (Math.random() * 6 + 5);
    }
    
    public void update() {
    	if(particleTimer >= particleInterval) {
    		delete = true;
    	} else {
    		particleTimer++;
    	}
    }
    
    public void draw(Graphics2D g2) {
    	g2.setColor(color);
        g2.fillOval(x, y, size, size);
    }
}