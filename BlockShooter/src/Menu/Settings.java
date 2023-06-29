package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Toolbox.LeftButton1;
import Toolbox.SemiCenterButton;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Background;
import Util.Image;
import Util.SaveResources;
import Util.Variables;

public class Settings extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public SaveResources saveResources;
	public LeftButton1 homeButton;
	public Image image;
	public Background background;
	public SemiCenterButton ballBack, ballNext, blockBack, blockNext;
	public BufferedImage previewColor;
	public Color[] colors;
	
	public Settings(Image image, Background background, Color[] colors, SaveResources saveResources) {
		this.saveResources = saveResources;
		this.colors = colors;
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.image = image;
		this.background = background;
		this.homeButton = new LeftButton1();
		this.ballBack = new SemiCenterButton(-40, -25);
		this.ballNext = new SemiCenterButton(40, -25);
		this.blockBack = new SemiCenterButton(-40, 75);
		this.blockNext = new SemiCenterButton(40, 75);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		
		g2.drawImage(image.settingsBoard, (Variables.WINDOW_WIDTH - 475) / 2, (Variables.WINDOW_HEIGHT - 273) / 2, 475, 273, null);
		
		g2.drawImage(image.backButton, homeButton.x, homeButton.y, homeButton.width, homeButton.height, null);
		
		g2.drawImage(image.backButton, ballBack.x, ballBack.y, ballBack.width, ballBack.height, null);
		g2.drawImage(image.nextButton, ballNext.x, ballNext.y, ballNext.width, ballNext.height, null);
		g2.drawImage(image.backButton, blockBack.x, blockBack.y, blockBack.width, blockBack.height, null);
		g2.drawImage(image.nextButton, blockNext.x, blockNext.y, blockNext.width, blockNext.height, null);
		
		g2.setColor(colors[Variables.selectedBlock]);
		g2.fill(new RoundRectangle2D.Double((Variables.WINDOW_WIDTH - 50) / 2, (Variables.WINDOW_HEIGHT - 50) / 2 + 25, 50, 50, 15, 15));
		
		g2.setColor(colors[Variables.selectedBall]);
		g2.fillOval((Variables.WINDOW_WIDTH - 50) / 2, (Variables.WINDOW_HEIGHT - 50) / 2 - 75, 50, 50);
		
		g2.setFont(new Font("Impact", Font.BOLD, 35));
        String game = "SETTINGS";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int gameX = (Variables.WINDOW_WIDTH - fontMetrics.stringWidth(game)) / 2;
    	int gameY = 80;
    	g2.setColor(new Color(0, 0, 102));
    	g2.drawString(game, gameX, gameY);
		
		g2.dispose();
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > homeButton.x && 
		   x < homeButton.x + homeButton.width && 
		   y > homeButton.y && 
		   y < homeButton.y + homeButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 2;
			saveResources.saveResources();
		}
		if(x > ballBack.x && 
		   x < ballBack.x + ballBack.width && 
		   y > ballBack.y && 
		   y < ballBack.y + ballBack.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedBall--;
            if (Variables.selectedBall < 0) {
            	Variables.selectedBall = colors.length - 1;
            }
		}
		if(x > ballNext.x && 
		   x < ballNext.x + ballNext.width && 
		   y > ballNext.y && 
		   y < ballNext.y + ballNext.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedBall++;
            if (Variables.selectedBall >= colors.length) {
            	Variables.selectedBall = 0;
            }
		}
		if(x > blockBack.x && 
		   x < blockBack.x + blockBack.width && 
		   y > blockBack.y && 
		   y < blockBack.y + blockBack.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedBlock--;
            if (Variables.selectedBlock < 0) {
            	Variables.selectedBlock = colors.length - 1;
            }
		}
		if(x > blockNext.x && 
		   x < blockNext.x + blockNext.width && 
		   y > blockNext.y && 
		   y < blockNext.y + blockNext.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedBlock++;
            if (Variables.selectedBlock >= colors.length) {
            	Variables.selectedBlock = 0;
            }
		}
	}
}