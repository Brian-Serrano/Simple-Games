package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Database.SyncData;
import Main.Window;
import Toolbox.PlayButton;
import Toolbox.RightBottomButton1;
import Toolbox.RightButton1;
import Toolbox.RightButton2;
import Toolbox.RightButton3;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Image;
import Util.ToastMessage;
import Util.Variables;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Window window;
	public PlayButton startButton;
	public RightButton1 settingsButton;
	public Image image;
	public RightButton2 highscoreButton;
	public RightButton3 signButton;
	public RightBottomButton1 syncButton;
	public SyncData syncData;
	
	public Menu(Window window, Image image) {
		this.window  = window;
		this.image = image;
		this.startButton = new PlayButton(window);
		this.settingsButton = new RightButton1(window);
		this.highscoreButton = new RightButton2(window);
		this.signButton = new RightButton3(window);
		this.syncButton = new RightBottomButton1(window);
		this.syncData = new SyncData();
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.backgroundImage, 0, 0, window.getWidth(), window.getHeight(), null);
        g2.drawImage(image.playButton, startButton.x, startButton.y, startButton.width, startButton.height, null);
        g2.drawImage(image.settingsButton, settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height, null);
        g2.drawImage(image.highscoreButton, highscoreButton.x, highscoreButton.y, highscoreButton.width, highscoreButton.height, null);
        g2.drawImage(image.signButton, signButton.x, signButton.y, signButton.width, signButton.height, null);
        g2.drawImage(image.syncButton, syncButton.x, syncButton.y, syncButton.width, syncButton.height, null);
        
        g2.setFont(new Font("Impact", Font.BOLD, 50));
        String game = "BALL BOUNCE";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int gameX = (window.getWidth() - fontMetrics.stringWidth(game)) / 2;
    	int gameY = 80;
        g2.setColor(new Color(179, 89, 0));
    	g2.drawString(game, gameX, gameY);
    	g2.setColor(new Color(255, 128, 0));
    	g2.drawString(game, gameX - 3, gameY - 1);
    	
    	g2.setFont(new Font("Impact", Font.PLAIN, 25));
    	g2.setColor(new Color(128, 96, 0));
    	g2.drawString(Variables.username.toUpperCase(), 10, window.getHeight() - 15);
    	g2.drawString("HIGHSCORE: " + Variables.highscore, 10, window.getHeight() - 40);
    	g2.setColor(new Color(204, 153, 0));
    	g2.drawString(Variables.username.toUpperCase(), 10 - 3, window.getHeight() - 15 - 1);
    	g2.drawString("HIGHSCORE: " + Variables.highscore, 10 - 3, window.getHeight() - 40 - 1);
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > startButton.x && 
           x < startButton.x + startButton.width && 
           y > startButton.y && 
           y < startButton.y + startButton.height) {
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
			Variables.selectedPanel = 3;
		}
		if(x > highscoreButton.x && 
		   x < highscoreButton.x + highscoreButton.width && 
		   y > highscoreButton.y && 
		   y < highscoreButton.y + highscoreButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 4;
		}
		if(x > signButton.x && 
		   x < signButton.x + signButton.width && 
		   y > signButton.y && 
		   y < signButton.y + signButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			if(!Variables.isLoggedIn) {
				Variables.selectedPanel = 6;
			} else {
				ToastMessage.showToastMessage(window, "You already logged in", 2000);
			}
			
		}
		if(x > syncButton.x && 
		   x < syncButton.x + syncButton.width && 
		   y > syncButton.y && 
		   y < syncButton.y + syncButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			if(Variables.isLoggedIn) {
				syncData.syncData(window);
			} else {
				ToastMessage.showToastMessage(window, "You did not logged in", 2000);
			}
		}
	}
}
