package ex3;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Consumer;

public class FocusHandler implements FocusListener {
	
	private Consumer<FocusEvent> gained;
	private Consumer<FocusEvent> lost;
	
	public static FocusHandler focusListener() {
		return new FocusHandler();
	}

	public void focusGained(FocusEvent e) {
		if (this.gained != null)
			this.gained.accept(e);
	}
	public void focusLost(FocusEvent e) {
		if (this.lost != null)
			this.lost.accept(e);
	}

	public FocusHandler gained(Consumer<FocusEvent> consumer) {
		this.gained = consumer;
		return this;
	}
	public FocusHandler lost(Consumer<FocusEvent> consumer) {
		this.lost = consumer;
		return this;
	}
}
