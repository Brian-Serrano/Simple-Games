package Toolbox;

import Util.Variables;

public class BottomLeftButton1 {
	public int x, y, width, height;
	
	public BottomLeftButton1() {
		this.width = 50;
		this.height = 50;
		this.x = 10;
		this.y = Variables.WINDOW_HEIGHT - this.height - 10;
	}
}
