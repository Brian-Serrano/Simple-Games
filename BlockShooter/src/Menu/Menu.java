package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Toolbox.CenterButton;
import Toolbox.RightButton1;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Background;
import Util.Image;
import Util.Variables;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Background background;
	public CenterButton playButton;
	public RightButton1 settingsButton;
	public Image image;
	
	public Menu(Image image, Background background) {
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.image = image;
		this.background = background;
		this.playButton = new CenterButton();
		this.settingsButton = new RightButton1();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		g2.drawImage(image.playButton, playButton.x, playButton.y, playButton.width, playButton.height, null);
		g2.drawImage(image.settingsButton, settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height, null);
		
		g2.setFont(new Font("Impact", Font.BOLD, 50));
        String game = "BLOCK SHOOTER";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int gameX = (Variables.WINDOW_WIDTH - fontMetrics.stringWidth(game)) / 2;
    	int gameY = 80;
    	g2.setColor(new Color(0, 0, 102));
    	g2.drawString(game, gameX, gameY);
    	
    	g2.setFont(new Font("Impact", Font.PLAIN, 25));
    	g2.setColor(new Color(0, 0, 102));
    	g2.drawString("HIGHSCORE: " + Variables.highscore, 10, Variables.WINDOW_HEIGHT - 15);
    	
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
			Variables.selectedPanel = 4;
		}
	}
}