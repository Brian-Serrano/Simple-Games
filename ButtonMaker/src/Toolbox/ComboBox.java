package Toolbox;

import java.awt.Font;

import javax.swing.JComboBox;

public class ComboBox extends JComboBox<String> {
	private static final long serialVersionUID = 1L;

	public ComboBox(String[] array, boolean renderer, String selected) {
		super(array);
		isThereRenderer(renderer);
		this.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setSelectedItem(selected);
	}
	
	public void isThereRenderer(boolean renderer) {
		if(renderer) {
			this.setRenderer(new ColorCellRenderer());
		}
	}
}
