package Main;

import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.Timer;

import Database.FetchHighscore;
import Game.Game;
import Menu.Highscore;
import Menu.LogIn;
import Menu.Menu;
import Menu.Pause;
import Menu.Settings;
import Menu.SignUp;
import Util.Audio;
import Util.Image;
import Util.SaveResources;
import Util.Variables;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	public SaveResources saveResources;
	public Image image;
	public Game game;
	public Menu menu;
	public Settings settings;
	public Highscore highscore;
	public Pause pause;
	public SignUp signUp;
	public LogIn logIn;
	public FetchHighscore fetchHighscore;
	public Timer timer;
	public Insets insets;
	public Audio menuSound;
	public Audio gameSound;
	public int originalWidth = 700;
	public int originalHeight = 500;
	
	public Window() {
		this.setSize(originalWidth, originalHeight);
		this.image = new Image();
		this.fetchHighscore = new FetchHighscore();
		this.saveResources = new SaveResources(this);
		this.saveResources.fetchResources();
		this.game = new Game(this, image, saveResources);
		this.menu = new Menu(this, image);
		this.settings = new Settings(this, image, saveResources);
		this.highscore = new Highscore(this, image);
		this.pause = new Pause(this, image, saveResources);
		this.signUp = new SignUp(this, image);
		this.logIn = new LogIn(this, image);
		this.menuSound = new Audio("assets/Audio/menu_sound.wav");
		this.gameSound = new Audio("assets/Audio/game_sound.wav");
		this.setResizable(false);
		this.setTitle("Ball Bounce");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.insets = this.getInsets();
		this.setSize(this.getWidth() + insets.left + insets.right, this.getHeight() + insets.top + insets.bottom);
	}
	
	@Override
    public int getWidth() {
        return originalWidth;
    }
    
    @Override
    public int getHeight() {
        return originalHeight;
    }
	
	public void animate() {
		timer = new Timer(16, e -> {
			switch (Variables.selectedPanel) {
	        case 1:
	            if (Variables.switchToGamePanel) {
	            	if (Variables.switchToPausePanel) {
	            		game.resetGame();
	            	}
	            	menuSound.pause();
	            	menuSound.setTime(0);
	            	gameSound.playInfinitely();
	                getContentPane().removeAll();
	                getContentPane().add(game);
	                validate();
	                Variables.switchToGamePanel = false;
	            }
	            Variables.switchToSignUpPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToHighscorePanel = true;
	            Variables.switchToSettingsPanel = true;
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
	            Variables.switchToLogInPanel = true;
	            Variables.switchToSignUpPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToHighscorePanel = true;
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToGamePanel = true;
	            menu.repaint();
	            break;
	        case 3:
	            if (Variables.switchToSettingsPanel) {
	                getContentPane().removeAll();
	                getContentPane().add(settings);
	                validate();
	                Variables.switchToSettingsPanel = false;
	            }
	            Variables.switchToLogInPanel = true;
	            Variables.switchToSignUpPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToHighscorePanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            settings.repaint();
	            break;
	        case 4:
	            if (Variables.switchToHighscorePanel) {
	            	fetchHighscore.fetchHighscore(this);
	                getContentPane().removeAll();
	                getContentPane().add(highscore);
	                validate();
	                Variables.switchToHighscorePanel = false;
	            }
	            Variables.switchToLogInPanel = true;
	            Variables.switchToSignUpPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            highscore.render(fetchHighscore);
	            break;
	        case 5:
	            if (Variables.switchToPausePanel) {
	            	gameSound.pause();
	                getContentPane().removeAll();
	                getContentPane().add(pause);
	                validate();
	                Variables.switchToPausePanel = false;
	            }
	            Variables.switchToLogInPanel = true;
	            Variables.switchToSignUpPanel = true;
	            Variables.switchToHighscorePanel = true;
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            pause.repaint();
	            break;
	        case 6:
	            if (Variables.switchToSignUpPanel) {
	                getContentPane().removeAll();
	                getContentPane().add(signUp);
	                validate();
	                Variables.switchToSignUpPanel = false;
	                signUp.repaint();
	            }
	            Variables.switchToLogInPanel = true;
	            Variables.switchToPausePanel = true;
	            Variables.switchToHighscorePanel = true;
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            break;
	        case 7:
	            if (Variables.switchToLogInPanel) {
	                getContentPane().removeAll();
	                getContentPane().add(logIn);
	                validate();
	                Variables.switchToLogInPanel = false;
	                logIn.repaint();
	            }
	            Variables.switchToPausePanel = true;
	            Variables.switchToSignUpPanel = true;
	            Variables.switchToHighscorePanel = true;
	            Variables.switchToSettingsPanel = true;
	            Variables.switchToMenuPanel = true;
	            Variables.switchToGamePanel = true;
	            break;
			}
		});
		
		timer.start();
	}
}
