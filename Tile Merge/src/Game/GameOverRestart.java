package Game;

import Util.Variables;

public class GameOverRestart {
	public int x, y, width, height, arc;

	public GameOverRestart() {
		this.width = 200;
		this.height = 75;
		this.x = (Variables.WINDOW_WIDTH - this.width) / 2;
		this.y = (Variables.WINDOW_HEIGHT - this.height) / 2 + 50;
		this.arc = 10;
	}
}
