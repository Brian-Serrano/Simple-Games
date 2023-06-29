package Editor;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Toolbox.CheckBox;
import Toolbox.ComboBox;
import Toolbox.FormattedTextField;
import Toolbox.Label;
import Toolbox.Spinner;

public class TextTab extends JPanel {
	private static final long serialVersionUID = 1L;
	public Label boldLabel, italicLabel, textShadowLabel, fontFamilyLabel, fontSizeLabel, fontColorLabel,
			textColorTypeLabel, textChooseColorLabel, textColorLabel2, textColorLabel3, textColorLabel4;
	public ComboBox fontFamilyInput, fontColorInput, textColorTypeInput, textChooseColorInput, textColorInput2;
	public CheckBox boldCheck, italicCheck, textShadowCheck;
	public FormattedTextField textColorInput3, textColorInput4;
	public Spinner fontSizeInput;

	public TextTab(String[] COLOR, String[] FONT, String[] COLOR_1, String[] COLOR_2) {
		GridLayout gridLayout = new GridLayout(4, 8, 10, 10);
		this.setLayout(gridLayout);
		this.boldLabel = new Label("Bold:");
		this.boldCheck = new CheckBox();
		this.italicLabel = new Label("Italic:");
		this.italicCheck = new CheckBox();
		this.textShadowLabel = new Label("Text Shadow:");
		this.textShadowCheck = new CheckBox();
		this.fontFamilyLabel = new Label("Font Family:");
		this.fontFamilyInput = new ComboBox(FONT, false, "Arial");
		this.fontSizeLabel = new Label("Font Size:");
		this.fontSizeInput = new Spinner(new int[] { 25, 10, 40, 1 });
		this.fontColorLabel = new Label("Text Color 1:");
		this.fontColorInput = new ComboBox(COLOR, true, "black");
		this.textColorTypeLabel = new Label("Gradient Type:");
		this.textColorTypeInput = new ComboBox(COLOR_1, false, "Uni");
		this.textChooseColorLabel = new Label("Color Model:");
		this.textChooseColorInput = new ComboBox(COLOR_2, false, "Constants");
		this.textColorLabel2 = new Label("Text Color 2:");
		this.textColorInput2 = new ComboBox(COLOR, true, "dark gray");
		this.textColorLabel3 = new Label("Text Color 1:");
		this.textColorInput3 = new FormattedTextField("###:###:###", "000:000:000");
		this.textColorLabel4 = new Label("Text Color 2:");
		this.textColorInput4 = new FormattedTextField("###:###:###", "128:128:128");

		this.add(fontColorLabel);
		this.add(fontColorInput);
		this.add(fontFamilyLabel);
		this.add(fontFamilyInput);
		this.add(italicLabel);
		this.add(italicCheck);
		this.add(textColorLabel2);
		this.add(textColorInput2);
		this.add(fontSizeLabel);
		this.add(fontSizeInput);
		this.add(boldLabel);
		this.add(boldCheck);
		this.add(textColorLabel3);
		this.add(textColorInput3);
		this.add(textColorTypeLabel);
		this.add(textColorTypeInput);
		this.add(textShadowLabel);
		this.add(textShadowCheck);
		this.add(textColorLabel4);
		this.add(textColorInput4);
		this.add(textChooseColorLabel);
		this.add(textChooseColorInput);
	}
}
