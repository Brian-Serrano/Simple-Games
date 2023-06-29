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
import Util.Variables;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Background background;
	public Button1 settingsButton, playButton;
	public Image image;
	
	public Menu(Image image, Background background) {
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.image = image;
		this.background = background;
		this.playButton = new Button1(250);
		this.settingsButton = new Button1(350);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		g2.drawImage(image.playButton, playButton.x, playButton.y, playButton.width, playButton.height, null);
		g2.drawImage(image.settingsButton, settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height, null);
		
		g2.setFont(new Font("Impact", Font.BOLD, 50));
        String game = "ZIGZAG BALL";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int gameX = (Variables.WINDOW_WIDTH - fontMetrics.stringWidth(game)) / 2;
    	int gameY = 80;
        g2.setColor(new Color(102, 0, 0));
    	g2.drawString(game, gameX, gameY);
    	g2.setColor(new Color(204, 0, 0));
    	g2.drawString(game, gameX - 3, gameY - 1);
    	
    	g2.setFont(new Font("Impact", Font.PLAIN, 25));
    	g2.setColor(new Color(102, 0, 0));
    	g2.drawString("HIGHSCORE: " + Variables.highscore, 10, Variables.WINDOW_HEIGHT - 15);
    	g2.setColor(new Color(204, 0, 0));
    	g2.drawString("HIGHSCORE: " + Variables.highscore, 10 - 3, Variables.WINDOW_HEIGHT - 15 - 1);
    	
		g2.dispose();
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > playButton.x && 
		   x < playButton.x + playButton.width && 
		   y > playButton.y && 
		   y < playButton.y + playButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 1;
		}
		if(x > settingsButton.x && 
		   x < settingsButton.x + settingsButton.width && 
		   y > settingsButton.y && 
		   y < settingsButton.y + settingsButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
	        Variables.originalObstacleColor = Variables.newObstacleColor;
			Variables.selectedPanel = 4;
		}
	}
}
