package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Main.Window;
import Toolbox.RightButton1;
import Toolbox.RightButton2;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Image;
import Util.SaveResources;
import Util.Variables;

public class Pause extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Window window;
	public Image image;
	public RightButton1 playButton;
	public RightButton2 homeButton;
	public SaveResources saveResources;
	
	public Pause(Window window, Image image, SaveResources saveResources) {
		this.window = window;
		this.playButton = new RightButton1(window);
		this.homeButton = new RightButton2(window);
		this.image = image;
		this.saveResources = saveResources;
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.backgroundImage, 0, 0, window.getWidth(), window.getHeight(), null);
        g2.drawImage(image.playButton, playButton.x, playButton.y, playButton.width, playButton.height, null);
        g2.drawImage(image.homeButton, homeButton.x, homeButton.y, homeButton.width, homeButton.height, null);
        
        g2.setFont(new Font("Impact", Font.BOLD, 50));
        String pause = "PAUSED";
        FontMetrics fontMetrics = g2.getFontMetrics();
		int x = (window.getWidth() - fontMetrics.stringWidth(pause)) / 2;
        int y = (window.getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        g2.setColor(new Color(179, 89, 0));
    	g2.drawString(pause, x, y);
    	g2.setColor(new Color(255, 128, 0));
    	g2.drawString(pause, x - 3, y - 1);
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
