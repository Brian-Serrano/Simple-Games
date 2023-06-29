package Editor;

import javax.swing.JPanel;

import Toolbox.Button;
import Util.Variables;

public class ViewTab extends JPanel {
	private static final long serialVersionUID = 1L;
	public Button apply, save;

	public ViewTab() {
		this.setBounds(0, Variables.WINDOW_HEIGHT - 50, Variables.WINDOW_WIDTH, 50);

		this.apply = new Button("APPLY");
		this.save = new Button("SAVE");

		this.add(apply);
		this.add(save);
	}
}
