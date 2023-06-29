package Main;

import java.awt.Insets;

import javax.swing.JFrame;

import Game.Game;
import Util.Variables;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	public Game gamePanel;
	public Insets insets;

	public Window() {
		this.gamePanel = new Game();
		this.setSize(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT);
		this.setResizable(false);
		this.setTitle("Tile Merge");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(gamePanel);
		this.setVisible(true);
		this.insets = this.getInsets();
		this.setSize(this.getWidth() + insets.left + insets.right, this.getHeight() + insets.top + insets.bottom);
	}
}
