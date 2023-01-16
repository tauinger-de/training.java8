package db.util.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length < 1)
			throw new RuntimeException("no properties-file specified");

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		final Properties props = new Properties();
		props.load(ClassLoader.getSystemResourceAsStream(args[0]));
		
		final MainPanel mainPanel = new MainPanel();
		final Controller controller = new Controller(mainPanel, props);
		
		final JFrame frame = new JFrame("db.util.gui (jn - 2009)");
		frame.setPreferredSize(new Dimension(900, 600));
		frame.add(mainPanel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				controller.exit();
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}