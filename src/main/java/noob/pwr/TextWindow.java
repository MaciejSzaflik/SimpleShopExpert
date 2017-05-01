package noob.pwr;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class TextWindow extends JFrame {
	public TextWindow(String text) {
		this.setBounds(0,0,500,600);
		JTextArea textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.CENTER);
		
		textArea.setText(text);
	}

}
