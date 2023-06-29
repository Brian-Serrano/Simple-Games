package UserInput;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game.Cannon;

public class CannonMouseAdapter extends MouseAdapter {
	public Cannon cannon;
	
	public CannonMouseAdapter(Cannon cannon) {
		this.cannon = cannon;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(cannon.balls.isEmpty()) {
			int x = e.getX();
			int y = e.getY();
			cannon.rotateCannon(x, y);
		}
	}
}
