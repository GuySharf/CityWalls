import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title extends JPanel{	
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private Font font;

	public Title() {
		// Create Font
		font = new Font("Times New Roman", Font.PLAIN, 52);
		// Create Label
		label = new JLabel("City Walls");
		label.setFont(font);
		label.setForeground(Color.white);
		// Create Panel
		this.setBounds(100, 100, 600, 150);
		this.setBackground(Color.black);
		this.add(label);
	}
}