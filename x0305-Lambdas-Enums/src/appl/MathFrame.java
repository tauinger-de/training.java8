package appl;

import java.awt.FlowLayout;
import java.util.function.BinaryOperator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MathFrame extends JFrame {
	
	private final JTextField textFieldX = new JTextField(10);
	private final JTextField textFieldY = new JTextField(10);
	private final JTextField textFieldResult = new JTextField(10);
	
	public MathFrame() {
		this.setLayout(new FlowLayout());
		this.add(this.textFieldX);
		this.add(this.textFieldY);
		this.addButtons();
		this.add(this.textFieldResult);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private void addButtons() {
		for (Action action : Action.values()) {
			JButton button = new JButton(action.displayName());
			button.addActionListener(e -> onCalc(action.getOperator()));
			this.add(button);
		}
	}
	
	private void onCalc(BinaryOperator<Integer> op) {
		try {
			int x = Integer.parseInt(this.textFieldX.getText());
			int y = Integer.parseInt(this.textFieldY.getText());
			int result = op.apply(x, y);
			this.textFieldResult.setText(String.valueOf(result));
		}
		catch(Exception e) {
			this.textFieldResult.setText("Illegal input");
		}
	}
}
