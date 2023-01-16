package appl;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MathFrame extends JFrame {
	
	static private class ButtonPlusAdapter implements ActionListener {
		final MathFrame mathFrame;
		public ButtonPlusAdapter(MathFrame mathFrame) {
			this.mathFrame = mathFrame;
		}
		public void actionPerformed(ActionEvent e) {
			this.mathFrame.onPlus();
		}
	}

	static private class ButtonMinusAdapter implements ActionListener {
		final MathFrame mathFrame;
		public ButtonMinusAdapter(MathFrame mathFrame) {
			this.mathFrame = mathFrame;
		}
		public void actionPerformed(ActionEvent e) {
			this.mathFrame.onMinus();
		}
	}
	
	private final JTextField textFieldX = new JTextField(10);
	private final JTextField textFieldY = new JTextField(10);
	private final JButton buttonPlus = new JButton("Plus");
	private final JButton buttonMinus = new JButton("Minus");
	private final JTextField textFieldResult = new JTextField(10);
	
	public MathFrame() {
		this.setLayout(new FlowLayout());
		this.add(this.textFieldX);
		this.add(this.textFieldY);
		this.add(this.buttonPlus);
		this.add(this.buttonMinus);
		this.add(this.textFieldResult);
		this.registerListeners();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private void registerListeners() {
		this.buttonPlus.addActionListener(new ButtonPlusAdapter(this));
		this.buttonMinus.addActionListener(new ButtonMinusAdapter(this));
	}

	private void onPlus() {
		try {
			int x = Integer.parseInt(this.textFieldX.getText());
			int y = Integer.parseInt(this.textFieldY.getText());
			int result = x + y;
			this.textFieldResult.setText(String.valueOf(result));
		}
		catch(NumberFormatException e) {
			this.textFieldResult.setText("Illegal input");
		}
	}
	
	private void onMinus() {
		try {
			int x = Integer.parseInt(this.textFieldX.getText());
			int y = Integer.parseInt(this.textFieldY.getText());
			int result = x - y;
			this.textFieldResult.setText(String.valueOf(result));
		}
		catch(NumberFormatException e) {
			this.textFieldResult.setText("Illegal input");
		}
	}

}
