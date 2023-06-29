package Toolbox;

import Main.Window;

public class HighscoreBoard {
	public int x, y, width, height;
	public Window window;
	
	public HighscoreBoard(Window window) {
		this.window = window;
		this.width = window.getHeight();
		this.height = window.getHeight();
		this.x = (window.getWidth() - this.width) / 2;
		this.y = 0;
	}
}
