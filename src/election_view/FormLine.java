package election_view;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormLine {
	public Label label;
	public TextField textField;
	public ComboBox<String> comboBox;
	
	public FormLine(Label label, TextField textField, ComboBox<String> comboBox) {
		this.label = label;
		this.textField = textField;
		this.comboBox = comboBox;
	}
	
	public void setEnabled(boolean bool) {
		if (label != null) {
			label.setDisable(!bool);
		}
		if (textField != null) {
			textField.setEditable(bool);
		}
		if (comboBox != null) {
			comboBox.setEditable(bool);
		}
	}
}
