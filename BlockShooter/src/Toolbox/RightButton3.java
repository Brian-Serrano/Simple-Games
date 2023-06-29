package Toolbox;

import Util.Variables;

public class RightButton3 {
	public int x, y, width, height;
	
	public RightButton3() {
		this.width = 123 / 2;
		this.height = 55 / 2;
		this.x = Variables.WINDOW_WIDTH - this.width - 10;
		this.y = (this.height * 2) + 30;
	}
}
