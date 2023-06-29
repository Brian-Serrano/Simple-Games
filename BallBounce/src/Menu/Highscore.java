package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Database.FetchHighscore;
import Main.Window;
import Toolbox.HighscoreBoard;
import Toolbox.LeftButton1;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Image;
import Util.Variables;

public class Highscore extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Window window;
	public Image image;
	public HighscoreBoard highscoreBoard;
	public LeftButton1 backButton;
	public FetchHighscore fetchHighscore;
	
	public Highscore(Window window, Image image) {
		this.window = window;
		this.image = image;
		this.highscoreBoard = new HighscoreBoard(window);
		this.backButton = new LeftButton1();
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
	}
	
	public void render(FetchHighscore fetchHighscore) {
		this.fetchHighscore = fetchHighscore;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.backgroundImage, 0, 0, window.getWidth(), window.getHeight(), null);
        g2.drawImage(image.highscoreBoard, highscoreBoard.x, highscoreBoard.y, highscoreBoard.width, highscoreBoard.height, null);
        g2.drawImage(image.previousButton, backButton.x, backButton.y, backButton.width, backButton.height, null);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Impact", Font.PLAIN, 15));
        
        for(int i = 0; i < 5; i++) {
        	g2.drawString(fetchHighscore.data[i][0].toString().toUpperCase(), 180, i * 59 + 160);
        	g2.drawString(fetchHighscore.data[i][1].toString().toUpperCase(), 450, i * 59 + 160);
        }
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > backButton.x && 
		   x < backButton.x + backButton.width && 
		   y > backButton.y && 
		   y < backButton.y + backButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 2;
		}
	}
}
