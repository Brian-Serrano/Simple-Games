package Toolbox;

import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton {
	private static final long serialVersionUID = 1L;

	public Button(String text) {
		this.setText(text);
		this.setFont(new Font("Algerian", Font.PLAIN, 20));
	}
}
