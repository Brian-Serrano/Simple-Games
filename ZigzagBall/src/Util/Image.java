package Util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	public BufferedImage slope, blockOne, blockTwo, playButton, settingsButton, confirmButton, pauseButton, restartButton, homeButton, continueButton, chooseButton;
	
	public Image() {
		this.insertImage();
	}
	
	public void insertImage() {
		try {
			blockTwo = ImageIO.read(getClass().getResourceAsStream("/Tiles/CrossBlock10.png"));
			blockOne = ImageIO.read(getClass().getResourceAsStream("/Tiles/CrossBlock11.png"));
			slope = ImageIO.read(getClass().getResourceAsStream("/Tiles/CrossSlope01.png"));
			playButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_play.png"));
			settingsButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_settings.png"));
			confirmButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_confirm.png"));
			pauseButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_pause.png"));
			restartButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_restart.png"));
			homeButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_home.png"));
			continueButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_continue.png"));
			chooseButton = ImageIO.read(getClass().getResourceAsStream("/Button/button_c.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
