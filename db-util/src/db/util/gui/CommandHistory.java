package db.util.gui;

import java.util.ArrayList;
import java.util.List;

class CommandHistory {

	private List<String> list = new ArrayList<String>();
	private int historyIndex = -1;

	public void add(String text) {
		if (this.list.size() == 0 || this.historyIndex == this.list.size() - 1
				&& !text.equals(this.list.get(this.list.size() - 1))) {
			if (this.list.size() == 100)
				this.list.remove(0);
			this.list.add(text);
			this.historyIndex = this.list.size() - 1;
		}
	}
	
	public boolean hasPrev() {
		return this.historyIndex > 0;
	}

	public boolean hasNext() {
		return this.historyIndex < this.list.size() - 1;
	}

	public String prev() {
		if (! this.hasPrev())
			throw new IllegalStateException();
		this.historyIndex--;
		return this.list.get(this.historyIndex);
	}

	public String next() {
		if (! this.hasNext())
			throw new IllegalStateException();
		this.historyIndex++;
		return this.list.get(this.historyIndex);
	}
}
