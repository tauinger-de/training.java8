package ex4;

import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MyFrame extends JFrame {
	private final TextField textField = new TextField(10);

	private int counter;
	
	public MyFrame() {
		this.setLayout(new FlowLayout());
		this.add(this.textField);
		this.setBounds(100, 100, 200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Thread progressThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					}
					catch(Exception e) {
						throw new RuntimeException(e);
					}
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							MyFrame.this.textField.setText(String.valueOf(++MyFrame.this.counter));
						}
					});
				} 
			}
		};
		
		progressThread.setDaemon(true);
		progressThread.start();
		this.setVisible(true);
	}
}
