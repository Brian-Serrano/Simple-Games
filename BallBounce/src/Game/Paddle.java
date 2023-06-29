package Game;

import java.awt.Graphics2D;

import Main.Window;
import Util.Image;
import Util.Variables;

public class Paddle {
	public int x, y, width, height;
	public Window window;
	
	public Paddle(Window window) {
		this.window = window;
		this.width = 90;
		this.height = 30;
		this.x = (window.getWidth() - width) / 2;
		this.y = window.getHeight() - 50;
	}

	public void update(int x) {
		this.x = x - (this.width / 2);
		if (this.x < 0) {
            this.x = 0;
        }
		if (this.x + this.width > window.getWidth()) {
            this.x = window.getWidth() - this.width;
        }
	}
	public void draw(Graphics2D g2, Image image) {
	    g2.drawImage(image.paddleImage.get(Variables.selectedPaddle), x, y, width, height, null);
	}
}
