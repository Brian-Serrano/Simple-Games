package Toolbox;

import Util.Variables;

public class Button2 {
	public int x, y, width, height;
	
	public Button2(int x, int y) {
		this.width = 90;
		this.height = 80;
		this.x = ((Variables.WINDOW_WIDTH - this.width) / 2) + x;
		this.y = ((Variables.WINDOW_HEIGHT - this.height) / 2) + y;
	}
}
