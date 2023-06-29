package Toolbox;

import Util.Variables;

public class Button1 {
	public int x, y, width, height;
	
	public Button1(int y) {
		this.width = 300;
		this.height = 75;
		this.x = (Variables.WINDOW_WIDTH - this.width) / 2;
		this.y = y;
	}
}
