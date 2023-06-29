package Toolbox;

import Util.Variables;

public class CenterButton {
	public int x, y, width, height;
	
	public CenterButton() {
		this.width = 123;
		this.height = 55;
		this.x = (Variables.WINDOW_WIDTH - this.width) / 2;
		this.y = (Variables.WINDOW_HEIGHT - this.height) / 2;
	}
}
