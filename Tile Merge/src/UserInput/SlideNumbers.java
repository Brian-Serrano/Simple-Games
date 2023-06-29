package UserInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.Game;

public class SlideNumbers implements KeyListener {
	public Game game;

	public SlideNumbers(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (!game.gameOver) {
			if (keyCode == KeyEvent.VK_W) {
				game.SlideTop();
			} else if (keyCode == KeyEvent.VK_A) {
				game.SlideLeft();
			} else if (keyCode == KeyEvent.VK_S) {
				game.SlideBottom();
			} else if (keyCode == KeyEvent.VK_D) {
				game.SlideRight();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
