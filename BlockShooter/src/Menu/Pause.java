package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Toolbox.RightButton1;
import Toolbox.RightButton2;
import Toolbox.RightButton3;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Background;
import Util.Image;
import Util.SaveResources;
import Util.Variables;

public class Pause extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public SaveResources saveResources;
	public RightButton1 resumeButton;
	public RightButton2 restartButton;
	public RightButton3 exitButton;
	public Image image;
	public Background background;
	
	public Pause(Image image, Background background, SaveResources saveResources) {
		this.saveResources = saveResources;
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.image = image;
		this.background = background;
		this.resumeButton = new RightButton1();
		this.restartButton = new RightButton2();
		this.exitButton = new RightButton3();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		g2.drawImage(image.resumeButton, resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height, null);
		g2.drawImage(image.restartButton, restartButton.x, restartButton.y, restartButton.width, restartButton.height, null);
		g2.drawImage(image.exitButton, exitButton.x, exitButton.y, exitButton.width, exitButton.height, null);
		
		g2.setFont(new Font("Impact", Font.BOLD, 35));
        String game = "PAUSED";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int gameX = (Variables.WINDOW_WIDTH - fontMetrics.stringWidth(game)) / 2;
    	int gameY = 80;
    	g2.setColor(new Color(0, 0, 102));
    	g2.drawString(game, gameX, gameY);
		
		g2.dispose();
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > resumeButton.x && 
		   x < resumeButton.x + resumeButton.width && 
		   y > resumeButton.y && 
		   y < resumeButton.y + resumeButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.continued = false;
			Variables.selectedPanel = 1;
		}
		if(x > restartButton.x && 
		   x < restartButton.x + restartButton.width && 
		   y > restartButton.y && 
		   y < restartButton.y + restartButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 1;
		}
		if(x > exitButton.x && 
		   x < exitButton.x + exitButton.width && 
		   y > exitButton.y && 
		   y < exitButton.y + exitButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			if(Variables.highscore < Variables.score) {
    			Variables.highscore = Variables.score;
    		}
			Variables.selectedPanel = 2;
			saveResources.saveResources();
		}
	}
}
