package util.workviewer;

import java.awt.Color;

public class Work {

	public interface Viewer {
		public abstract void start();

		public abstract void beginWork(String text, Color color);

		public abstract void endWork();

		public abstract void stop();

		public abstract void terminate();
	}

	private static Viewer nullViewer = new Viewer() {
		public void start() {
		}

		public void beginWork(String text, Color color) {
		}

		public void endWork() {
		}

		public void stop() {
		}

		public void terminate() {
		}
	};

	public static Viewer viewer = nullViewer;

	public static void useViewer() {
		Work.viewer = new GuiViewer();
	}

	public static Viewer getViewer() {
		return Work.viewer;
	}
}
