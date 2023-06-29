package Main;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.Timer;

import Game.Game;
import Menu.Menu;
import Menu.Pause;
import Menu.Settings;
import Util.Audio;
import Util.Background;
import Util.Image;
import Util.ImageColorChanger;
import Util.SaveResources;
import Util.Variables;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	public Background background;
	public SaveResources saveResources;
	public Image image;
	public Game game;
	public Menu menu;
	public Pause pause;
	public Settings settings;
	public Timer timer;
	public Insets insets;
	public Audio menuSound;
	public Audio gameSound;
	
	public Window() {
		this.setSize(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT);
		this.setTitle("Zigzag Ball");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.saveResources = new SaveResources();
		this.saveResources.fetchResources();
		this.image = new Image();
		ImageColorChanger.colorImage(image.slope, Variables.newObstacleColor, Color.WHITE);
		ImageColorChanger.colorImage(image.blockOne,Variables.newObstacleColor, Color.WHITE);
		ImageColorChanger.colorImage(image.blockTwo, Variables.newObstacleColor, Color.WHITE);
		this.background = new Background();
		this.game = new Game(image, background, saveResources);
		this.menu = new Menu(image, background);
		this.pause = new Pause(image, background, saveResources);
		this.settings = new Settings(image, background, game, saveResources);
		this.menuSound = new Audio("Assets/Audio/menu_sound.wav");
		this.gameSound = new Audio("Assets/Audio/game_sound.wav");
		this.setVisible(true);
		this.insets = this.getInsets();
		this.setSize(this.getWidth() + insets.left + insets.right, this.getHeight() + insets.top + insets.bottom);
	}
	
	public void animate() {
		
		timer = new Timer(16, e -> {
			switch (Variables.selectedPanel) {
	        case 1:
	            if (Variables.switchToGamePanel) {
	            	if(Variables.continued) {
		            	game.resetGame();
	            	}
	            	menuSound.pause();
	            	menuSound.setTime(0);
	            	gameSound.playInfinitely();
	                getContentPane().removeAll();
	                getContentPane().add(game);
	                validate();
	                game.update();
		            game.repaint();
	                Variables.continued = true;
	                Variables.switchToGamePanel = false;
	            }
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToMenuPanel = true;
	            if(game.startMoving) {
		            game.update();
		            game.repaint();
	            }
	            break;
	        case 2:
	            if (Variables.switchToMenuPanel) {
	            	gameSound.pause();
	            	gameSound.setTime(0);
	            	menuSound.playInfinitely();
	                getContentPane().removeAll();
	                getContentPane().add(menu);
	                validate();
	                Variables.switchToMenuPanel = false;
	            }
	            game.startMoving = false;
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToGamePanel = true;
	            menu.repaint();
	            break;
	        case 3:
	            if (Variables.switchToPausePanel) {
	                getContentPane().removeAll();
	                getContentPane().add(pause);
	                validate();
	                Variables.switchToPausePanel = false;
	            }
	            game.startMoving = false;
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            pause.repaint();
	            break;
	        case 4:
	            if (Variables.switchToSettingsPanel) {
	                getContentPane().removeAll();
	                getContentPane().add(settings);
	                validate();
	                Variables.switchToSettingsPanel = false;
	            }
	            game.startMoving = false;
	            Variables.switchToPausePanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            settings.repaint();
	            break;
			}
		});
		
		timer.start();
	}
}
