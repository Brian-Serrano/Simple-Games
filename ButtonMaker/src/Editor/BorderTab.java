package Editor;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Toolbox.ComboBox;
import Toolbox.FormattedTextField;
import Toolbox.Label;
import Toolbox.Spinner;

public class BorderTab extends JPanel {
	private static final long serialVersionUID = 1L;
	public Label borderRadiusLabel, borderWidthLabel, borderColorLabel, borderColorTypeLabel, borderChooseColorLabel,
			borderColorLabel2, borderColorLabel3, borderColorLabel4;
	public ComboBox borderColorInput, borderColorTypeInput, borderChooseColorInput, borderColorInput2;
	public FormattedTextField borderColorInput3, borderColorInput4;
	public Spinner borderRadiusInput, borderWidthInput;

	public BorderTab(String[] COLOR, String[] COLOR_1, String[] COLOR_2) {
		GridLayout gridLayout = new GridLayout(4, 8, 10, 10);
		this.setLayout(gridLayout);
		this.borderRadiusLabel = new Label("Border Radius:");
		this.borderRadiusInput = new Spinner(new int[] { 15, 0, 50, 1 });
		this.borderWidthLabel = new Label("Border Width:");
		this.borderWidthInput = new Spinner(new int[] { 3, 0, 15, 1 });
		this.borderColorLabel = new Label("Border Color 1:");
		this.borderColorInput = new ComboBox(COLOR, true, "dark gray");
		this.borderColorTypeLabel = new Label("Gradient Type:");
		this.borderColorTypeInput = new ComboBox(COLOR_1, false, "Uni");
		this.borderChooseColorLabel = new Label("Color Model:");
		this.borderChooseColorInput = new ComboBox(COLOR_2, false, "Constants");
		this.borderColorLabel2 = new Label("Border Color 2:");
		this.borderColorInput2 = new ComboBox(COLOR, true, "light gray");
		this.borderColorLabel3 = new Label("Border Color 1:");
		this.borderColorInput3 = new FormattedTextField("###:###:###", "128:128:128");
		this.borderColorLabel4 = new Label("Border Color 2:");
		this.borderColorInput4 = new FormattedTextField("###:###:###", "128:128:128");

		this.add(borderRadiusLabel);
		this.add(borderRadiusInput);
		this.add(borderColorLabel);
		this.add(borderColorInput);
		this.add(borderWidthLabel);
		this.add(borderWidthInput);
		this.add(borderColorLabel2);
		this.add(borderColorInput2);
		this.add(borderColorTypeLabel);
		this.add(borderColorTypeInput);
		this.add(borderColorLabel3);
		this.add(borderColorInput3);
		this.add(borderChooseColorLabel);
		this.add(borderChooseColorInput);
		this.add(borderColorLabel4);
		this.add(borderColorInput4);
	}
}
