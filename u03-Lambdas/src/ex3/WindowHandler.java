package ex3;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;
import java.util.function.Consumer;

public class WindowHandler implements WindowListener {

    private Consumer<WindowEvent> opened = e -> {
    };
    private Consumer<WindowEvent> closing = e -> {
    };
    private Consumer<WindowEvent> closed = e -> {
    };
    private Consumer<WindowEvent> iconified = e -> {
    };
    private Consumer<WindowEvent> deiconified = e -> {
    };
    private Consumer<WindowEvent> activated = e -> {
    };
    private Consumer<WindowEvent> deactivated = e -> {
    };

    public static WindowHandler windowListener() {
        return new WindowHandler();
    }

    // -----------------------------------------------
    public void windowOpened(WindowEvent e) {
        this.opened.accept(e);
    }

    public void windowClosing(WindowEvent e) {
        this.closing.accept(e);
    }

    public void windowClosed(WindowEvent e) {
        this.closed.accept(e);
    }

    public void windowIconified(WindowEvent e) {
        this.iconified.accept(e);
    }

    public void windowDeiconified(WindowEvent e) {
        this.deiconified.accept(e);
    }

    public void windowActivated(WindowEvent e) {
        this.activated.accept(e);
    }

    public void windowDeactivated(WindowEvent e) {
        this.deactivated.accept(e);
    }

    // -----------------------------------------------
    public WindowHandler opened(Consumer<WindowEvent> consumer) {
        Objects.requireNonNull(consumer);
        this.opened = consumer;
        return this;
    }

    public WindowHandler closing(Consumer<WindowEvent> consumer) {
        Objects.requireNonNull(consumer);
        this.closing = consumer;
        return this;
    }

    public WindowHandler closed(Consumer<WindowEvent> consumer) {
        Objects.requireNonNull(consumer);
        this.closed = consumer;
        return this;
    }

    public WindowHandler iconified(Consumer<WindowEvent> consumer) {
        Objects.requireNonNull(consumer);
        this.iconified = consumer;
        return this;
    }

    public WindowHandler deiconified(Consumer<WindowEvent> consumer) {
        Objects.requireNonNull(consumer);
        this.deiconified = consumer;
        return this;
    }

    public WindowHandler activated(Consumer<WindowEvent> consumer) {
        Objects.requireNonNull(consumer);
        this.activated = consumer;
        return this;
    }

    public WindowHandler deactivated(Consumer<WindowEvent> consumer) {
        Objects.requireNonNull(consumer);
        this.deactivated = consumer;
        return this;
    }
}
