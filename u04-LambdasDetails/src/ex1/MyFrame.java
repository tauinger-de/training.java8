package ex1;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ex1.Handler;
import ex1.Traverser;

public class MyFrame extends Frame {
	private final Panel panelLeft = new Panel();
	private final Panel panelRight = new Panel();
	private final Button buttonHello = new Button("Hello");
	private final Button buttonWorld = new Button("World");
	private final TextField textFieldFoo = new TextField("Foo", 10);
	private final TextArea textAreaBar = new TextArea("World", 2, 10);

	public MyFrame() {
		this.setLayout(new FlowLayout());
		this.panelLeft.setLayout(new FlowLayout());
		this.panelRight.setLayout(new FlowLayout());
		this.add(this.panelLeft);
		this.add(this.panelRight);
		this.panelLeft.add(this.buttonHello);
		this.panelLeft.add(this.textFieldFoo);
		this.panelRight.add(this.buttonWorld);
		this.panelRight.add(this.textAreaBar);
		this.pack();
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MyFrame.this.dispose();
			}
		});
		
		this.buttonHello.addActionListener(e -> textComponentsToUpperCase());
		this.buttonWorld.addActionListener(e -> textComponentsToLowerCase());
	}

	private void textComponentsToUpperCase() {
		Traverser.traverse(this, new Handler<TextComponent>() {
			public void handle(TextComponent c) {
				c.setText(c.getText().toUpperCase());
			}
		});
	}

	private void textComponentsToLowerCase() {
		Traverser.traverse(this,
				(TextComponent tc) -> tc.setText(tc.getText().toLowerCase()));
		//Traverser.<TextComponent> traverse(this, tc -> tc.setText(tc.getText().toLowerCase()));
	}

}
