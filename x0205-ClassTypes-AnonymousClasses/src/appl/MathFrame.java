package appl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("Convert2Lambda")
public class MathFrame extends JFrame {

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
        // mit "Zwischenvariable"
        ActionListener plusListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPlus();
            }
        };
        this.buttonPlus.addActionListener(plusListener);

        // direkt als Argument
        this.buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMinus();
            }
        });
    }

    private void onPlus() {
        try {
            int x = Integer.parseInt(this.textFieldX.getText());
            int y = Integer.parseInt(this.textFieldY.getText());
            int result = x + y;
            this.textFieldResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            this.textFieldResult.setText("Illegal input");
        }
    }

    private void onMinus() {
        try {
            int x = Integer.parseInt(this.textFieldX.getText());
            int y = Integer.parseInt(this.textFieldY.getText());
            int result = x - y;
            this.textFieldResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            this.textFieldResult.setText("Illegal input");
        }
    }

}
