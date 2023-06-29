package Toolbox;

import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Spinner extends JSpinner {
	private static final long serialVersionUID = 1L;
	
	public Spinner(int[] a) {
		SpinnerNumberModel model = new SpinnerNumberModel(a[0], a[1], a[2], a[3]);
		this.setModel(model);
		this.setFont(new Font("Arial", Font.PLAIN, 20));
		
	}
}
