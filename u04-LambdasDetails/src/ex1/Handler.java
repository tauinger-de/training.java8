package ex1;

import java.awt.Component;
import java.io.Serializable;

public interface Handler<T extends Component> extends Serializable {
    public abstract void handle(T c);
}
