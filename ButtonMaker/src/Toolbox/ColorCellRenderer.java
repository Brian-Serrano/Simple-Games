package Toolbox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class ColorCellRenderer extends JPanel implements ListCellRenderer<String> {
	private static final long serialVersionUID = 1L;

	private static final Map<String, Color> colorMap = new HashMap<String, Color>() {
		private static final long serialVersionUID = 1L;

		{
			put("red", Color.RED);
			put("green", Color.GREEN);
			put("blue", Color.BLUE);
			put("magenta", Color.MAGENTA);
			put("cyan", Color.CYAN);
			put("yellow", Color.YELLOW);
			put("black", Color.BLACK);
			put("white", Color.WHITE);
			put("gray", Color.GRAY);
			put("dark gray", Color.DARK_GRAY);
			put("light gray", Color.LIGHT_GRAY);
			put("orange", Color.ORANGE);
			put("pink", Color.PINK);
		}
	};

	private JLabel colorLabel;
	private JLabel nameLabel;

	public ColorCellRenderer() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		colorLabel = new JLabel();
		colorLabel.setOpaque(true);
		colorLabel.setPreferredSize(new Dimension(20, 20));

		nameLabel = new JLabel();

		add(colorLabel);
		add(nameLabel);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			boolean isSelected, boolean cellHasFocus) {

		if (isSelected) {
			setBackground(colorMap.get(value));
		} else {
			setBackground(Color.WHITE);
		}

		colorLabel.setBackground(colorMap.get(value));
		nameLabel.setText(value);

		return this;
	}
}