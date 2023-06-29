package Toolbox;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Textfield extends JTextField {
	private static final long serialVersionUID = 1L;

	public Textfield(String placeholder) {
		this.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setText(placeholder);
        this.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
