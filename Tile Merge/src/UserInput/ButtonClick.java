package UserInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Game.Game;

public class ButtonClick implements MouseListener {
	public Game game;

	public ButtonClick(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.HandleClick(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
