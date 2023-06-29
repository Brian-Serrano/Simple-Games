package UserInput;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Util.Variables;

public class PaddleMouseAdapter extends MouseAdapter {
	public int x;
	
	public PaddleMouseAdapter(int x) {
		this.x = x;
	}
	
	public void mouseDragged(MouseEvent e) {
		if(Variables.selectedPanel == 1) {
			x = e.getX();
		}
	}
}
