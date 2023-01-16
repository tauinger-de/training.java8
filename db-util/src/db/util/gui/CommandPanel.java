package db.util.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

class CommandPanel extends JToolBar {

    private static final long serialVersionUID = 1L;

    public void initialize(final BeforeAfter beforeAfter, final Object target, String... names) {
        for (final String name : names) {
            if (name == null) {
                this.addSeparator(new Dimension(20, 0));
                continue;
            }
            JButton button = new JButton(name);
            this.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    beforeAfter.before(name);
                    try {
                        target.getClass().getMethod(name, new Class[]{}).invoke(target, new Object[]{});
                        beforeAfter.after(name);
                    } catch (InvocationTargetException ex) {
                        beforeAfter.after(name, ex.getTargetException());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }

    public JButton getButton(String name) {
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (!(this.getComponent(i) instanceof JButton))
                continue;
            JButton button = (JButton) this.getComponent(i);
            if (button.getText().equals(name))
                return button;
        }
        throw new RuntimeException();
    }
}
