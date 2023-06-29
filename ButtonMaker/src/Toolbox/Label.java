package Toolbox;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {
	private static final long serialVersionUID = 1L;

	public Label(String text) {
		this.setText(text);
		this.setFont(new Font("Algerian", Font.PLAIN, 20));
        this.setHorizontalAlignment(JLabel.RIGHT);
	}
}
