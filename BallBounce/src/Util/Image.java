package Util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Image {
	
	public ArrayList<BufferedImage> paddleImage = new ArrayList<BufferedImage>();
	public BufferedImage backgroundImage, playButton, nextButton, previousButton, settingsButton, highscoreButton, highscoreBoard, homeButton, pauseButton, confirmButton, signButton, syncButton;
	
	public Image() {
		this.insertImage();
	}
	
	public void insertImage() {
		try {
			for(int i = 0; i < 10; i++) {
				BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/Paddle/Paddle" + (i+1) + ".png"));
				paddleImage.add(img);
			}
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/Background/background.png"));
			playButton = ImageIO.read(getClass().getResourceAsStream("/Button/playbutton.png"));
			nextButton = ImageIO.read(getClass().getResourceAsStream("/Button/nextbutton.png"));
			previousButton = ImageIO.read(getClass().getResourceAsStream("/Button/previousbutton.png"));
			settingsButton = ImageIO.read(getClass().getResourceAsStream("/Button/settingsbutton.png"));
			highscoreButton = ImageIO.read(getClass().getResourceAsStream("/Button/highscorebutton.png"));
			highscoreBoard = ImageIO.read(getClass().getResourceAsStream("/Background/highscoreboard.png"));
			homeButton = ImageIO.read(getClass().getResourceAsStream("/Button/homebutton.png"));
			pauseButton = ImageIO.read(getClass().getResourceAsStream("/Button/pausebutton.png"));
			confirmButton = ImageIO.read(getClass().getResourceAsStream("/Button/confirmbutton.png"));
			signButton = ImageIO.read(getClass().getResourceAsStream("/Button/signbutton.png"));
			syncButton = ImageIO.read(getClass().getResourceAsStream("/Button/syncbutton.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
