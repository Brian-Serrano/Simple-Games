package UserInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Game.Game;
import Menu.Menu;
import Menu.Pause;
import Menu.Settings;
import Util.Audio;
import Util.Variables;

public class ButtonMouseListener implements MouseListener {
	public Game game;
	public Menu menu;
	public Pause pause;
	public Settings settings;
	
	public Audio buttonClick = new Audio("Assets/Audio/button_click.wav");
	
	public ButtonMouseListener(Game game) {
		this.game = game;
	}
	
	public ButtonMouseListener(Menu menu) {
		this.menu = menu;
	}
	
	public ButtonMouseListener(Pause pause) {
		this.pause = pause;
	}
	
	public ButtonMouseListener(Settings settings) {
		this.settings = settings;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		switch(Variables.selectedPanel) {
			case 1:
				if(!Variables.switchToGamePanel) {
					game.handleClick(x, y, buttonClick);
				}
				break;
			case 2:
				if(!Variables.switchToMenuPanel) {
					menu.handleClick(x, y, buttonClick);
				}
				break;
			case 3:
				if(!Variables.switchToPausePanel) {
					pause.handleClick(x, y, buttonClick);
				}
				break;
			case 4:
				if(!Variables.switchToSettingsPanel) {
					settings.handleClick(x, y, buttonClick);
				}
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
    public void mouseReleased(MouseEvent e) {
    	int x = e.getX();
		int y = e.getY();
    	if(Variables.selectedPanel == 1) {
    		if(game.cannon.balls.isEmpty()) {
    			game.createBall(x, y);
    		}
    	}
    }
}
