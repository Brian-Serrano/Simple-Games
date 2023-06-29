package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Toolbox.Button1;
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
	public Button1 continueButton, restartButton, homeButton;
	public Image image;
	public Background background;
	
	public Pause(Image image, Background background, SaveResources saveResources) {
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.saveResources = saveResources;
		this.image = image;
		this.background = background;
		this.continueButton = new Button1(150);
		this.restartButton = new Button1(250);
		this.homeButton = new Button1(350);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		g2.drawImage(image.continueButton, continueButton.x, continueButton.y, continueButton.width, continueButton.height, null);
		g2.drawImage(image.restartButton, restartButton.x, restartButton.y, restartButton.width, restartButton.height, null);
		g2.drawImage(image.homeButton, homeButton.x, homeButton.y, homeButton.width, homeButton.height, null);
		
		g2.setFont(new Font("Impact", Font.BOLD, 35));
        String game = "PAUSED";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int gameX = (Variables.WINDOW_WIDTH - fontMetrics.stringWidth(game)) / 2;
    	int gameY = 80;
        g2.setColor(new Color(102, 0, 0));
    	g2.drawString(game, gameX, gameY);
    	g2.setColor(new Color(204, 0, 0));
    	g2.drawString(game, gameX - 3, gameY - 1);
		
		g2.dispose();
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > continueButton.x && 
		   x < continueButton.x + continueButton.width && 
		   y > continueButton.y && 
		   y < continueButton.y + continueButton.height) {
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
		if(x > homeButton.x && 
		   x < homeButton.x + homeButton.width && 
		   y > homeButton.y && 
		   y < homeButton.y + homeButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			if(Variables.highscore < Variables.score) {
    			Variables.highscore = Variables.score;
    		}
			saveResources.saveResources();
			Variables.selectedPanel = 2;
		}
	}
}
