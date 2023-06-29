package UserInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Game.Game;
import Menu.Highscore;
import Menu.LogIn;
import Menu.Menu;
import Menu.Pause;
import Menu.Settings;
import Menu.SignUp;
import Util.Audio;
import Util.Variables;

public class ButtonMouseListener implements MouseListener {
	public Game game;
	public Menu menu;
	public Settings settings;
	public Highscore highscore;
	public Pause pause;
	public SignUp signUp;
	public LogIn logIn;

	public Audio buttonClick = new Audio("assets/Audio/button_click.wav");
	
	public ButtonMouseListener(Game game) {
		this.game = game;
	}
	
	public ButtonMouseListener(Menu menu) {
		this.menu = menu;
	}
	
	public ButtonMouseListener(Settings settings) {
		this.settings = settings;
	}
	
	public ButtonMouseListener(Highscore highscore) {
		this.highscore = highscore;
	}
	
	public ButtonMouseListener(Pause pause) {
		this.pause = pause;
	}
	
	public ButtonMouseListener(SignUp signUp) {
		this.signUp = signUp;
	}
	
	public ButtonMouseListener(LogIn logIn) {
		this.logIn = logIn;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		switch(Variables.selectedPanel) {
			case 1:
				game.handleClick(x, y, buttonClick);
				break;
			case 2:
				menu.handleClick(x, y, buttonClick);
				break;
			case 3:
				settings.handleClick(x, y, buttonClick);
				break;
			case 4:
				highscore.handleClick(x, y, buttonClick);
				break;
			case 5:
				pause.handleClick(x, y, buttonClick);
				break;
			case 6:
				signUp.handleClick(x, y, buttonClick);
				break;
			case 7:
				logIn.handleClick(x, y, buttonClick);
				break;
		}
	}
	
	@Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
