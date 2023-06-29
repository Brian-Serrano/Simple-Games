package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Database.LogInSave;
import Main.Window;
import Toolbox.BottomButton1;
import Toolbox.Input;
import Toolbox.LeftButton1;
import UserInput.ButtonMouseListener;
import Util.Audio;
import Util.Image;
import Util.Variables;

public class LogIn extends JPanel {
	private static final long serialVersionUID = 1L;
	public ButtonMouseListener buttonMouseListener;
	public Window window;
	public Input username;
	public Input password;
	public LeftButton1 backButton;
	public BottomButton1 confirmButton;
	public Image image;
	public LogInSave logInSave;
	
	public LogIn(Window window, Image image) {
		this.window = window;
		this.image = image;
		this.username = new Input("ENTER USERNAME", -50, window);
		this.password = new Input("ENTER PASSWORD", 50, window);
		this.backButton = new LeftButton1();
		this.confirmButton = new BottomButton1(window);
		this.logInSave = new LogInSave(window);
		this.buttonMouseListener = new ButtonMouseListener(this);
		this.addMouseListener(buttonMouseListener);
		this.setLayout(null);
		this.add(username);
		this.add(password);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image.backgroundImage, 0, 0, window.getWidth(), window.getHeight(), null);
		g2.drawImage(image.previousButton, backButton.x, backButton.y, backButton.width, backButton.height, null);
		g2.drawImage(image.confirmButton, confirmButton.x, confirmButton.y, confirmButton.width, confirmButton.height, null);
		
		g2.setFont(new Font("Impact", Font.BOLD, 50));
        String signUp = "LOG IN";
        FontMetrics fontMetrics = g2.getFontMetrics();
        int signX = (window.getWidth() - fontMetrics.stringWidth(signUp)) / 2;
    	int signY = 80;
        g2.setColor(new Color(179, 89, 0));
    	g2.drawString(signUp, signX, signY);
    	g2.setColor(new Color(255, 128, 0));
    	g2.drawString(signUp, signX - 3, signY - 1);
	}
	
	public void handleClick(int x, int y, Audio buttonClick) {
		if(x > backButton.x && 
           x < backButton.x + backButton.width && 
           y > backButton.y && 
           y < backButton.y + backButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			Variables.selectedPanel = 6;
		}
		if(x > confirmButton.x && 
		   x < confirmButton.x + confirmButton.width && 
		   y > confirmButton.y && 
		   y < confirmButton.y + confirmButton.height) {
			buttonClick.pause();
			buttonClick.setTime(0);
			buttonClick.play();
			logInSave.logInSave(username.getText(), password.getText());
		}
	}
}
