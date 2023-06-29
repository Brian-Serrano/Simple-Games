package Toolbox;

import Main.Window;

public class RightButton1 {
	public int x, y, width, height;
	public Window window;
	
	public RightButton1(Window window){
		this.window = window;
		this.width = 50;
		this.height = 50;
		this.x = window.getWidth() - this.width - 10;
		this.y = 10;
	}
}
