package Util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class Background {
	public int startX, startY, endX, endY;
	public Color startColor, endColor;
	public GradientPaint gradient;
	
	public Background() {
		this.startX = 0;
        this.startY = 0;
        this.endX = Variables.WINDOW_WIDTH;
        this.endY = Variables.WINDOW_HEIGHT;
        this.startColor = Variables.BG1Color;
        this.endColor = Variables.BG2Color;
        this.gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
	}
	
	public void draw(Graphics2D g2) {
		g2.setPaint(gradient);
        g2.fillRect(startX, startY, endX, endY);
	}
}
