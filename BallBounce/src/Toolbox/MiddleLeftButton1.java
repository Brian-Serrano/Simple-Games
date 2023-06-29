package Toolbox;

import Main.Window;

public class MiddleLeftButton1 {
	public int x, y, width, height;
	public Window window;
	
	public MiddleLeftButton1(int move, Window window) {
		this.window = window;
		this.width = 50;
		this.height = 50;
		this.x = ((window.getWidth() - this.width) / 2) - 100;
		this.y = ((window.getHeight() - this.height) / 2) + move;
	}
}
