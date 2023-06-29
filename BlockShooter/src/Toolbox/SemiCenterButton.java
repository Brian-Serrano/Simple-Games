package Toolbox;

import Util.Variables;

public class SemiCenterButton {
	public int x, y, width, height;
	
	public SemiCenterButton(int x, int y) {
		this.width = 123 / 2;
		this.height = 55 / 2;
		this.x = (Variables.WINDOW_WIDTH - this.width) / 2 + x;
		this.y = (Variables.WINDOW_HEIGHT - this.height) / 2 + y;
	}
}
