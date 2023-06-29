package Main;

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
import Util.SaveResources;
import Util.Variables;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	public SaveResources saveResources;
	public Insets insets;
	public Image image;
	public Background background;
	public Timer timer;
	public Game game;
	public Menu menu;
	public Settings settings;
	public Pause pause;
	public Audio gameSound;
	public Audio menuSound;

	public Window() {
		this.setSize(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT);
		this.saveResources = new SaveResources();
		this.saveResources.fetchResources();
		this.image = new Image();
		this.background = new Background();
		this.game = new Game(this.image, this.background, this.saveResources);
		this.menu = new Menu(this.image, this.background);
		this.settings = new Settings(this.image, this.background, game.COLOR_CONSTANTS, this.saveResources);
		this.pause = new Pause(this.image, this.background, this.saveResources);
		this.menuSound = new Audio("Assets/Audio/menu_sound.wav");
		this.gameSound = new Audio("Assets/Audio/game_sound.wav");
		this.setResizable(false);
		this.setTitle("Block Shooter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
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
	                Variables.continued = true;
	                Variables.switchToGamePanel = false;
	            }
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToMenuPanel = true;
		        game.update();
		        game.repaint();
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
	            Variables.switchToPausePanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            settings.repaint();
	            break;
			}
		});
		
		game.setTimer(timer);
		
		timer.start();
	}
}
