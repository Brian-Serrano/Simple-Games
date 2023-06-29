package Toolbox;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class FormattedTextField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;
	
	public FormattedTextField(String placeholder, String defaultPlaceholder) {
        super(createFormatter(placeholder));
		this.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setValue(defaultPlaceholder);
	}
	
	private static MaskFormatter createFormatter(String placeholder) {
		MaskFormatter formatter = null;
		try {
            formatter = new MaskFormatter(placeholder);
            formatter.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return formatter;
	}
}
