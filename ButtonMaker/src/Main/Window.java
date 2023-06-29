package Main;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import Editor.ButtonImage;
import Editor.Editor;
import Editor.ViewTab;
import Toolbox.ComboBox;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	public Editor editor;
	public ButtonImage buttonImage;
	public ViewTab viewTab;
	public Insets insets;

	public Window() {
		this.setLayout(null);
		this.setSize(1280, 720);
		this.setTitle("Button Editor");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.editor = new Editor();
		this.buttonImage = new ButtonImage();
		this.viewTab = new ViewTab();
		this.setButton();
		this.setText();
		this.setBorder();
		this.setEdit();
		this.buttonImage.repaint();
		this.add(editor);
		this.add(buttonImage);
		this.add(viewTab);
		this.setVisible(true);
		this.insets = this.getInsets();
		this.setSize(this.getWidth() + insets.left + insets.right, this.getHeight() + insets.top + insets.bottom);

		this.viewTab.apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButton();
				setText();
				setBorder();

				buttonImage.repaint();
			}
		});

		this.viewTab.save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonImage.save();
			}
		});

		this.editor.buttonTab.buttonChooseColorInput.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switchOption(editor.buttonTab.buttonChooseColorInput.getSelectedItem().toString(),
							editor.buttonTab.buttonColorInput, editor.buttonTab.buttonColorInput2,
							editor.buttonTab.buttonColorInput3, editor.buttonTab.buttonColorInput4);
				}
			}
		});

		this.editor.textTab.textChooseColorInput.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switchOption(editor.textTab.textChooseColorInput.getSelectedItem().toString(),
							editor.textTab.fontColorInput, editor.textTab.textColorInput2,
							editor.textTab.textColorInput3, editor.textTab.textColorInput4);
				}
			}
		});

		this.editor.borderTab.borderChooseColorInput.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switchOption(editor.borderTab.borderChooseColorInput.getSelectedItem().toString(),
							editor.borderTab.borderColorInput, editor.borderTab.borderColorInput2,
							editor.borderTab.borderColorInput3, editor.borderTab.borderColorInput4);
				}
			}
		});

		this.editor.buttonTab.buttonSizeFormatInput.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switch (editor.buttonTab.buttonSizeFormatInput.getSelectedItem().toString()) {
					case "Fixed":
						editor.buttonTab.buttonSizeInput.setEditable(true);
						editor.buttonTab.horizontalPaddingInput.setEnabled(false);
						editor.buttonTab.verticalPaddingInput.setEnabled(false);
						break;
					case "Fit to Text":
						editor.buttonTab.buttonSizeInput.setEditable(false);
						editor.buttonTab.horizontalPaddingInput.setEnabled(true);
						editor.buttonTab.verticalPaddingInput.setEnabled(true);
						break;
					}
				}
			}
		});
	}
	
	private void setEdit() {
		editor.buttonTab.buttonSizeInput.setEditable(true);
		editor.buttonTab.horizontalPaddingInput.setEnabled(false);
		editor.buttonTab.verticalPaddingInput.setEnabled(false);
		editor.buttonTab.buttonColorInput.setEnabled(true);
		editor.buttonTab.buttonColorInput2.setEnabled(true);
		editor.buttonTab.buttonColorInput3.setEditable(false);
		editor.buttonTab.buttonColorInput4.setEditable(false);
		editor.textTab.fontColorInput.setEnabled(true);
		editor.textTab.textColorInput2.setEnabled(true);
		editor.textTab.textColorInput3.setEditable(false);
		editor.textTab.textColorInput4.setEditable(false);
		editor.borderTab.borderColorInput.setEnabled(true);
		editor.borderTab.borderColorInput2.setEnabled(true);
		editor.borderTab.borderColorInput3.setEditable(false);
		editor.borderTab.borderColorInput4.setEditable(false);
	}

	private void setButton() {
		String[] buttonSize = editor.buttonTab.buttonSizeInput.getText().toString().split(":");
		buttonImage.buttonWidth = Integer.parseInt(buttonSize[0]);
		buttonImage.buttonHeight = Integer.parseInt(buttonSize[1]);
		buttonImage.buttonText = editor.buttonTab.buttonTextInput.getText();
		buttonImage.buttonShadow = editor.buttonTab.buttonShadowCheck.isSelected();
		buttonImage.buttonFormat = editor.buttonTab.buttonSizeFormatInput.getSelectedItem().toString();
		buttonImage.horizontalPadding = (int) editor.buttonTab.horizontalPaddingInput.getValue();
		buttonImage.verticalPadding = (int) editor.buttonTab.verticalPaddingInput.getValue();
		buttonImage.buttonGradient = editor.buttonTab.buttonColorTypeInput.getSelectedItem().toString();

		Color[] colorArr = checkColor(editor.buttonTab.buttonChooseColorInput.getSelectedItem().toString(),
				editor.buttonTab.buttonColorInput, editor.buttonTab.buttonColorInput2,
				editor.buttonTab.buttonColorInput3, editor.buttonTab.buttonColorInput4);
		buttonImage.buttonColor = colorArr[0];
		buttonImage.buttonColor2 = colorArr[1];

	}

	private void setText() {
		buttonImage.bold = editor.textTab.boldCheck.isSelected();
		buttonImage.italic = editor.textTab.italicCheck.isSelected();
		buttonImage.textShadow = editor.textTab.textShadowCheck.isSelected();
		buttonImage.fontFamily = editor.textTab.fontFamilyInput.getSelectedItem().toString();
		buttonImage.fontSize = (int) editor.textTab.fontSizeInput.getValue();
		buttonImage.textColor = buttonImage.COLOR_CONSTANTS[(int) Arrays.asList(editor.COLOR)
				.indexOf(editor.textTab.fontColorInput.getSelectedItem())];
		buttonImage.textGradient = editor.textTab.textColorTypeInput.getSelectedItem().toString();

		Color[] colorArr = checkColor(editor.textTab.textChooseColorInput.getSelectedItem().toString(),
				editor.textTab.fontColorInput, editor.textTab.textColorInput2, editor.textTab.textColorInput3,
				editor.textTab.textColorInput4);
		buttonImage.textColor = colorArr[0];
		buttonImage.textColor2 = colorArr[1];
	}

	private void setBorder() {
		buttonImage.borderRadius = (int) editor.borderTab.borderRadiusInput.getValue();
		buttonImage.borderWidth = (int) editor.borderTab.borderWidthInput.getValue();
		buttonImage.borderColor = buttonImage.COLOR_CONSTANTS[(int) Arrays.asList(editor.COLOR)
				.indexOf(editor.borderTab.borderColorInput.getSelectedItem())];
		buttonImage.borderGradient = editor.borderTab.borderColorTypeInput.getSelectedItem().toString();

		Color[] colorArr = checkColor(editor.borderTab.borderChooseColorInput.getSelectedItem().toString(),
				editor.borderTab.borderColorInput, editor.borderTab.borderColorInput2,
				editor.borderTab.borderColorInput3, editor.borderTab.borderColorInput4);
		buttonImage.borderColor = colorArr[0];
		buttonImage.borderColor2 = colorArr[1];
	}

	private int[] checkValue(String[] RGB) {
		int r = Integer.parseInt(RGB[0]);
		int g = Integer.parseInt(RGB[1]);
		int b = Integer.parseInt(RGB[2]);
		if (r > 255) {
			r = 255;
		}
		if (g > 255) {
			g = 255;
		}
		if (b > 255) {
			b = 255;
		}
		return new int[] { r, g, b };
	}

	private Color[] checkColor(String selectedModel, ComboBox color1, ComboBox color2, JFormattedTextField color3,
			JFormattedTextField color4) {
		Color firstColor = null;
		Color secondColor = null;
		switch (selectedModel) {
		case "Constants":
			firstColor = buttonImage.COLOR_CONSTANTS[(int) Arrays.asList(editor.COLOR)
					.indexOf(color1.getSelectedItem())];
			secondColor = buttonImage.COLOR_CONSTANTS[(int) Arrays.asList(editor.COLOR)
					.indexOf(color2.getSelectedItem())];
			break;

		case "RGB":
			String[] RGB = color3.getText().split(":");
			firstColor = new Color(checkValue(RGB)[0], checkValue(RGB)[1], checkValue(RGB)[2]);

			String[] RGB2 = color4.getText().split(":");
			secondColor = new Color(checkValue(RGB2)[0], checkValue(RGB2)[1], checkValue(RGB2)[2]);
			break;

		case "HSB":
			String[] HSB = color3.getText().split(":");
			firstColor = Color.getHSBColor((float) checkValue(HSB)[0] / 255, (float) checkValue(HSB)[1] / 255,
					(float) checkValue(HSB)[0] / 255);

			String[] HSB2 = color3.getText().split(":");
			secondColor = Color.getHSBColor((float) checkValue(HSB2)[0] / 255, (float) checkValue(HSB2)[1] / 255,
					(float) checkValue(HSB2)[0] / 255);
			break;
		}

		return new Color[] { firstColor, secondColor };
	}

	private void switchOption(String selectedOption, ComboBox color1, ComboBox color2, JFormattedTextField color3,
			JFormattedTextField color4) {
		switch (selectedOption) {
		case "Constants":
			color1.setEnabled(true);
			color2.setEnabled(true);
			color3.setEditable(false);
			color4.setEditable(false);
			break;

		case "RGB":
			color1.setEnabled(false);
			color2.setEnabled(false);
			color3.setEditable(true);
			color4.setEditable(true);
			break;

		case "HSB":
			color1.setEnabled(false);
			color2.setEnabled(false);
			color3.setEditable(true);
			color4.setEditable(true);
			break;
		}
	}
}
