package db.util.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class TextFieldPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private final int width;
	private final Map<String,JTextField> textFieldMap = new HashMap<String,JTextField>();
	private String[] names;

	public TextFieldPanel(int width) {
		this.width = width;
	}
	
	public void initialize(String... names) {
		this.names = names;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 2, 2, 2);
		c.gridy = 0;
		for (String name : names) {
			JLabel label = new JLabel(name);
			JTextField textField = new JTextField(width);
			c.gridx = 0;
			this.add(label, c);
			c.gridx = 1;
			this.add(textField, c);
			this.textFieldMap.put(name, textField);
			c.gridy++;
		}
	}

	public String getText(String field) {
		JTextField f = (JTextField) this.textFieldMap.get(field);
		return f == null ? "" : f.getText();
	}
	
	public void setText(String field, String text) {
		JTextField f = (JTextField) this.textFieldMap.get(field);
		if (f != null)
			f.setText(text);
	}

	public Properties getProperties() {
		Properties props = new Properties();
		for (Map.Entry<String, JTextField> e : this.textFieldMap.entrySet()) 
			props.put(e.getKey(), e.getValue().getText());
		return props;
	}

	public void setProperties(Properties props) {
		for (String name : names) {
			this.setText(name, (String)props.getProperty(name, ""));
		}
	}

}

