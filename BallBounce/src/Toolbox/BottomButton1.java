package Toolbox;

import Main.Window;

public class BottomButton1 {
	public int x, y, width, height;
	public Window window;
	
	public BottomButton1(Window window){
		this.window = window;
		this.width = 50;
		this.height = 50;
		this.x = (window.getWidth() - this.width) / 2;
		this.y = window.getHeight() - this.height - 30;
	}
}
