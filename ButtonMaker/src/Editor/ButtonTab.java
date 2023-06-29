package Editor;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Toolbox.CheckBox;
import Toolbox.ComboBox;
import Toolbox.FormattedTextField;
import Toolbox.Label;
import Toolbox.Spinner;
import Toolbox.Textfield;

public class ButtonTab extends JPanel {
	private static final long serialVersionUID = 1L;
	public Label buttonTextLabel, buttonSizeLabel, buttonSizeFormatLabel, horizontalPaddingLabel, verticalPaddingLabel,
			buttonColorLabel, buttonShadowLabel, buttonColorTypeLabel, buttonChooseColorLabel, buttonColorLabel2,
			buttonColorLabel3, buttonColorLabel4;
	public ComboBox buttonSizeFormatInput, buttonColorInput, buttonColorTypeInput, buttonChooseColorInput,
			buttonColorInput2;
	public Spinner horizontalPaddingInput, verticalPaddingInput;
	public FormattedTextField buttonSizeInput, buttonColorInput3, buttonColorInput4;
	public CheckBox buttonShadowCheck;
	public Textfield buttonTextInput;

	public ButtonTab(String[] COLOR, String[] BUTTON, String[] COLOR_1, String[] COLOR_2) {
		GridLayout gridLayout = new GridLayout(4, 8, 10, 10);
		this.setLayout(gridLayout);
		this.buttonTextLabel = new Label("Button Text:");
		this.buttonTextInput = new Textfield("Sample Text");
		this.buttonSizeLabel = new Label("Button Size:");
		this.buttonSizeInput = new FormattedTextField("###:###", "200:050");
		this.buttonSizeFormatLabel = new Label("Sizing Type:");
		this.buttonSizeFormatInput = new ComboBox(BUTTON, false, "Fixed");
		this.horizontalPaddingLabel = new Label("Hor Padding:");
		this.horizontalPaddingInput = new Spinner(new int[] { 35, 20, 50, 1 });
		this.verticalPaddingLabel = new Label("Ver Padding:");
		this.verticalPaddingInput = new Spinner(new int[] { 20, 10, 40, 1 });
		this.buttonColorLabel = new Label("Button Color 1:");
		this.buttonColorInput = new ComboBox(COLOR, true, "blue");
		this.buttonShadowLabel = new Label("Button Shadow:");
		this.buttonShadowCheck = new CheckBox();
		this.buttonColorTypeLabel = new Label("Gradient Type:");
		this.buttonColorTypeInput = new ComboBox(COLOR_1, false, "Uni");
		this.buttonChooseColorLabel = new Label("Color Model:");
		this.buttonChooseColorInput = new ComboBox(COLOR_2, false, "Constants");
		this.buttonColorLabel2 = new Label("Button Color 2:");
		this.buttonColorInput2 = new ComboBox(COLOR, true, "cyan");
		this.buttonColorLabel3 = new Label("Button Color 1:");
		this.buttonColorInput3 = new FormattedTextField("###:###:###", "000:000:255");
		this.buttonColorLabel4 = new Label("Button Color 2:");
		this.buttonColorInput4 = new FormattedTextField("###:###:###", "000:255:255");

		this.add(buttonTextLabel);
		this.add(buttonTextInput);
		this.add(verticalPaddingLabel);
		this.add(verticalPaddingInput);
		this.add(buttonColorLabel);
		this.add(buttonColorInput);
		this.add(buttonSizeFormatLabel);
		this.add(buttonSizeFormatInput);
		this.add(horizontalPaddingLabel);
		this.add(horizontalPaddingInput);
		this.add(buttonColorLabel2);
		this.add(buttonColorInput2);
		this.add(buttonSizeLabel);
		this.add(buttonSizeInput);
		this.add(buttonColorTypeLabel);
		this.add(buttonColorTypeInput);
		this.add(buttonColorLabel3);
		this.add(buttonColorInput3);
		this.add(buttonShadowLabel);
		this.add(buttonShadowCheck);
		this.add(buttonChooseColorLabel);
		this.add(buttonChooseColorInput);
		this.add(buttonColorLabel4);
		this.add(buttonColorInput4);
	}
}
