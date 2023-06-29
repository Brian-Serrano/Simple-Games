package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

import Game.Game;
import Toolbox.Button1;
import Toolbox.Button2;
import Toolbox.LeftButton1;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Background;
import Util.Image;
import Util.ImageColorChanger;
import Util.SaveResources;
import Util.Variables;

public class Settings extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public SaveResources saveResources;
	public LeftButton1 homeButton;
	public Button1 confirmButton;
	public Image image;
	public Background background;
	public Button2 chooseBall, chooseBG1, chooseBG2, chooseObstacle;
	public Game game;
	public BufferedImage previewColor;
	
	public Settings(Image image, Background background, Game game, SaveResources saveResources) {
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.saveResources = saveResources;
		this.image = image;
		this.previewColor = ImageColorChanger.colorImage(image.blockOne, Variables.newObstacleColor, Variables.originalObstacleColor);
		this.background = background;
		this.game = game;
		this.homeButton = new LeftButton1();
		this.confirmButton = new Button1(350);
		this.chooseBall = new Button2(-200, 0);
		this.chooseBG1 = new Button2(-50, 0);
		this.chooseBG2 = new Button2(50, 0);
		this.chooseObstacle = new Button2(200, 0);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		background.draw(g2);
		g2.drawImage(image.homeButton, homeButton.x, homeButton.y, homeButton.width, homeButton.height, null);
		g2.drawImage(image.confirmButton, confirmButton.x, confirmButton.y, confirmButton.width, confirmButton.height, null);
		
		g2.drawImage(image.chooseButton, chooseBall.x, chooseBall.y, chooseBall.width, chooseBall.height, null);
		g2.drawImage(image.chooseButton, chooseBG1.x, chooseBG1.y, chooseBG1.width, chooseBG1.height, null);
		g2.drawImage(image.chooseButton, chooseBG2.x, chooseBG2.y, chooseBG2.width, chooseBG2.height, null);
		g2.drawImage(image.chooseButton, chooseObstacle.x, chooseObstacle.y, chooseObstacle.width, chooseObstacle.height, null);
		
		
		g2.setColor(Variables.ballColor);
		g2.fillOval(chooseBall.x, chooseBall.y - 100, chooseBall.width, chooseBall.width);
		g2.drawImage(previewColor, chooseObstacle.x, chooseObstacle.y - 100, chooseObstacle.width, chooseObstacle.width, null);
		
		g2.setColor(Variables.BG1Color);
		g2.fillRect(chooseBG1.x, chooseBG1.y - 100, chooseBG1.width, chooseBG1.width);
		g2.setColor(Variables.BG2Color);
		g2.fillRect(chooseBG2.x, chooseBG2.y - 100, chooseBG2.width, chooseBG2.width);
		
		g2.setFont(new Font("Impact", Font.BOLD, 35));
        String game = "SETTINGS";
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
		if(x > homeButton.x && 
		   x < homeButton.x + homeButton.width && 
		   y > homeButton.y && 
		   y < homeButton.y + homeButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.ballColor = game.ball.color;
			Variables.BG1Color = background.startColor;
			Variables.BG2Color = background.endColor;
			ImageColorChanger.colorImage(image.blockOne, Variables.originalObstacleColor, Variables.newObstacleColor);
			Variables.newObstacleColor = game.tileColor1;
			Variables.originalObstacleColor = game.tileColor2;
			Variables.selectedPanel = 2;
		}
		if(x > confirmButton.x && 
		   x < confirmButton.x + confirmButton.width && 
		   y > confirmButton.y && 
		   y < confirmButton.y + confirmButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 2;
			game.ball.color = Variables.ballColor;
			background.startColor = Variables.BG1Color;
			background.endColor = Variables.BG2Color;
			background.gradient = new GradientPaint(background.startX, background.startY, background.startColor, background.endX, background.endY, background.endColor);
			game.tileColor1 = Variables.newObstacleColor;
			game.tileColor2 = Variables.originalObstacleColor;
			saveResources.saveResources();
		}
		if(x > chooseBall.x && 
		   x < chooseBall.x + chooseBall.width && 
		   y > chooseBall.y && 
		   y < chooseBall.y + chooseBall.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.ballColor = JColorChooser.showDialog(this, "Select Ball Color", Color.RED);
			if (Variables.ballColor == null) {
				Variables.ballColor = Color.RED;
			}
		}
		if(x > chooseBG1.x && 
		   x < chooseBG1.x + chooseBG1.width && 
		   y > chooseBG1.y && 
		   y < chooseBG1.y + chooseBG1.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.BG1Color = JColorChooser.showDialog(this, "Select Background Color 1", Color.RED);
			if (Variables.BG1Color == null) {
				Variables.BG1Color = Color.RED;
			}
		}
		if(x > chooseBG2.x && 
		   x < chooseBG2.x + chooseBG2.width && 
		   y > chooseBG2.y && 
		   y < chooseBG2.y + chooseBG2.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.BG2Color = JColorChooser.showDialog(this, "Select Background Color 2", Color.RED);
			if (Variables.BG2Color == null) {
				Variables.BG2Color = Color.RED;
			}
		}
		if(x > chooseObstacle.x && 
		   x < chooseObstacle.x + chooseObstacle.width && 
		   y > chooseObstacle.y && 
		   y < chooseObstacle.y + chooseObstacle.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			previewColor = ImageColorChanger.colorImage(image.blockOne, Variables.originalObstacleColor, Variables.newObstacleColor);
			Variables.newObstacleColor = JColorChooser.showDialog(this, "Select Obstacle Color", Color.RED);
			if (Variables.newObstacleColor == null) {
				Variables.newObstacleColor = Color.RED;
			}
			previewColor = ImageColorChanger.colorImage(image.blockOne, Variables.newObstacleColor, Variables.originalObstacleColor);
		}
	}
}
