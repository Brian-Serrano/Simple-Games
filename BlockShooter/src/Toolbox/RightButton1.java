package Toolbox;

import Util.Variables;

public class RightButton1 {
	public int x, y, width, height;
	
	public RightButton1() {
		this.width = 123 / 2;
		this.height = 55 / 2;
		this.x = Variables.WINDOW_WIDTH - this.width - 10;
		this.y = 10;
	}
}
