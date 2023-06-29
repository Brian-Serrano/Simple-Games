package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Util.Variables;

public class Cannon {
	public int x, y, width, height, ballSpeed, ballX, ballY, ballCreateTimeTimer, ballCreateTimer, ballsLaunched, hitboxX, hitboxY, hitboxWidth, hitboxHeight;
	public List<Ball> balls;
	public double angle;
	public boolean ballCreateTime, isMoved;
	public BufferedImage cannonImage;
	public Color[] colors;
	
	public Cannon(BufferedImage cannonImage, Color[] colors) {
		this.cannonImage = cannonImage;
		this.balls = new ArrayList<Ball>();
		this.width = 50;
		this.height = 100;
		this.x = (Variables.WINDOW_WIDTH - this.width) / 2;
		this.y = Variables.WINDOW_HEIGHT - this.height;
		this.colors = colors;
		this.ballX = this.x + (this.width / 2);
		this.ballY = this.y + this.height - 30;
		this.angle = -90;
		this.ballCreateTime = false;
		this.isMoved = false;
		this.ballCreateTimeTimer = 0;
		this.ballCreateTimer = 0;
		this.ballsLaunched = 0;
		this.hitboxWidth = this.width * 3;
		this.hitboxHeight = this.height;
		this.hitboxX = (Variables.WINDOW_WIDTH - this.hitboxWidth) / 2;
		this.hitboxY = this.y;
	}
	
	public void update() {
		for(Ball b : balls) {
			b.update();
		}
		if(ballCreateTime == true) {
			if(ballCreateTimer >= 1) {
				createBall();
				ballCreateTimer = 0;
	    	} else {
	    		ballCreateTimer++;
	    		ballCreateTimeTimer++;
	    	}
			if(ballCreateTimeTimer >= 50) {
				ballCreateTime = false;
				ballCreateTimeTimer = 0;
				isMoved = true;
			}
		}
		balls = balls.stream().filter(ball -> !ball.delete).collect(Collectors.toList());
	}
	
	public void draw(Graphics2D g2) {
		for(Ball b : balls) {
			b.draw(g2);
		}
		g2.rotate(Math.toRadians(angle + 90), ballX, ballY);
		g2.drawImage(cannonImage, x, y, width, height, null);
	}
	
	public void rotateCannon(int x, int y) {
		int dx = x - this.ballX;
		int dy = y - this.ballY;
		angle = Math.toDegrees(Math.atan2(dy, dx));
	}
	
	public void createBall() {
		String horizontal;
		String vertical;
		double angleRadians = Math.toRadians(angle);
		double velX = Math.cos(angleRadians);
		double velY = Math.sin(angleRadians);
		if(velX < 0) {
			velX = Math.abs(velX);
			horizontal = "left";
		} else {
			horizontal = "right";
		}
		if(velY < 0) {
			velY = Math.abs(velY);
			vertical = "top";
		} else {
			vertical = "bottom";
		}
		balls.add(new Ball(ballX, ballY, velX, velY, vertical, horizontal, colors));
	}
}
