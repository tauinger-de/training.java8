package ex3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {

    private final TextField textFieldFoo = new TextField("Foo", 10);

    private final TextField textFieldBar = new TextField("World", 10);

    public MyFrame() {
        this.setLayout(new FlowLayout());
        this.add(this.textFieldFoo);
        this.add(this.textFieldBar);
        this.pack();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                MyFrame.this.dispose();
            }
        });

        this.textFieldFoo.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                MyFrame.this.textFieldFoo.setBackground(Color.yellow);
            }

            public void focusLost(FocusEvent e) {
                MyFrame.this.textFieldFoo.setBackground(Color.white);
            }
        });

        this.setVisible(true);
    }
}
