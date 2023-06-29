package Game;

import Util.Variables;

public class Camera {
	public int y;
	
	public Camera() {
		this.y = 0;
	}
	
	public void follow(Ball ball) {
        y = ball.y - Variables.WINDOW_HEIGHT + 100;
	}
}
