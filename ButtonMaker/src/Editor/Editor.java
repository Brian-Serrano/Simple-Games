package Editor;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JTabbedPane;

import Util.Variables;

public class Editor extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	public final String[] COLOR = new String[] { "red", "green", "blue", "magenta", "cyan", "yellow", "black", "white",
			"gray", "dark gray", "light gray", "orange", "pink" };
	public final String[] BUTTON = new String[] { "Fixed", "Fit to Text" };
	public final String[] COLOR_1 = new String[] { "Linear", "Radial", "Uni" };
	public final String[] COLOR_2 = new String[] { "Constants", "RGB", "HSB" };
	public ButtonTab buttonTab;
	public TextTab textTab;
	public BorderTab borderTab;

	public Editor() {
		this.setBounds(0, 0, Variables.WINDOW_WIDTH, 200);
		this.buttonTab = new ButtonTab(COLOR, BUTTON, COLOR_1, COLOR_2);
		this.textTab = new TextTab(COLOR, getFonts(), COLOR_1, COLOR_2);
		this.borderTab = new BorderTab(COLOR, COLOR_1, COLOR_2);

		this.add("BUTTON", buttonTab);
		this.add("TEXT", textTab);
		this.add("BORDER", borderTab);
	}

	public String[] getFonts() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = ge.getAllFonts();
		String[] arr = new String[fonts.length];
		for (int i = 0; i < fonts.length; i++) {
			arr[i] = fonts[i].getFontName();
		}
		return arr;
	}
}
