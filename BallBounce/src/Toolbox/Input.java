package Toolbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Main.Window;

public class Input extends JTextField {
	private static final long serialVersionUID = 1L;
	public int x, y, width, height;
	public Window window;
	
	public Input(String placeholder, int move, Window window) {
		this.window = window;
		this.width = 350;
		this.height = 75;
		this.x = (window.getWidth() - this.width) / 2;
		this.y = (window.getHeight() - this.height) / 2 + move;
		this.setFont(new Font("Impact", Font.PLAIN, 20));
        this.setForeground(Color.LIGHT_GRAY);
        this.setText(placeholder);
        this.setBorder(BorderFactory.createLineBorder(new Color(184, 184, 184), 2));
        this.setBounds(x, y, width, height);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.addFocusListener(new FocusListener() {
        	
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.LIGHT_GRAY);
                }
            }
        });
	}
}
