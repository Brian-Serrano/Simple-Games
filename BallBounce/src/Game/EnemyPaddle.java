package Game;

import java.awt.Graphics2D;

import Main.Window;
import Util.Image;

public class EnemyPaddle {
	public int x, y, width, height, random;
	public Window window;
	
	public EnemyPaddle(Window window) {
		this.window = window;
		this.random = (int) (Math.random() * 10);
		this.width = 90;
		this.height = 30;
		this.x = (window.getWidth() - width) / 2;
		this.y = 20;
	}
	public void update(Ball ball) {
		this.x = ball.x - ((this.width - ball.width) / 2);
		if (this.x < 0) {
            this.x = 0;
        }
		if (this.x + this.width > window.getWidth()) {
            this.x = window.getWidth() - this.width;
        }
	}
	public void draw(Graphics2D g2, Image image) {
	    g2.drawImage(image.paddleImage.get(random), x, y, width, height, null);
	}
}