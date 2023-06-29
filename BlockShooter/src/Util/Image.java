package Util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	public BufferedImage cannon, backButton, exitButton, menuButton, nextButton, playButton, restartButton, resumeButton, settingsBoard, settingsButton, fastForwardButton;
	
	public Image() {
		this.insertImage();
	}
	
	public void insertImage() {
		try {
			
			cannon = ImageIO.read(getClass().getResourceAsStream("/Images/cannon.png"));
			backButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/back_button.png"));
			exitButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/exit_button.png"));
			menuButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/menu_button.png"));
			nextButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/next_button.png"));
			playButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/play_button.png"));
			restartButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/restart_button.png"));
			resumeButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/resume_button.png"));
			settingsBoard = ImageIO.read(getClass().getResourceAsStream("/Buttons/settings_board.png"));
			settingsButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/settings_button.png"));
			fastForwardButton = ImageIO.read(getClass().getResourceAsStream("/Buttons/fast_forward_button.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
