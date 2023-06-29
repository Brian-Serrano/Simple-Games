package Toolbox;

import Main.Window;

public class PlayButton {
	public int x, y, width, height;
	public Window window;
	
	public PlayButton(Window window){
		this.window = window;
		this.width = 200;
		this.height = 200;
		this.x = (window.getWidth() - this.width) / 2;
		this.y = (window.getHeight() - this.height) / 2;
	}
}
