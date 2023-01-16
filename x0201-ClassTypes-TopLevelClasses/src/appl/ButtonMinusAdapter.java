package appl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonMinusAdapter implements ActionListener {
	final MathFrame mathFrame;
	public ButtonMinusAdapter(MathFrame mathFrame) {
		this.mathFrame = mathFrame;
	}
	public void actionPerformed(ActionEvent e) {
		this.mathFrame.onMinus();
	}
	
}
