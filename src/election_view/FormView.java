package election_view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FormView extends GridPane {
	
	private int rowCount = 0;
	
	
	public FormView() {
		setPadding(new Insets(10));
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
	}

	public TextField addField(String label_string) {
		Label label = new Label(label_string);
		TextField textField = new TextField();
		add(label, 0,rowCount);
		add(textField, 2, rowCount);
		rowCount ++;
		return textField;
	}
	
    public Label addLabel(String labelString) {
    	Label label = new Label(labelString);
    	add(label,0,0);
    	return label;
    }
    
    
	public ComboBox<String> addComboBox(String text, String... options) {
		Label label = new Label(text);
		ComboBox<String> comboBox = new ComboBox<>();
		add(label, 0, rowCount);
		add(comboBox, 2, rowCount);
		comboBox.getItems().addAll(options);
		rowCount ++;
		return comboBox;
	}
	
	public ComboBox<String> addComboBox(String text, int defult, String... options) {
		ComboBox<String> combobox = addComboBox(text, options);
		combobox.getSelectionModel().select(defult);
		return combobox;
	}
	
	public Button addOkButton(String text) {
		Button button = new Button(text);
		add(button, 2, rowCount +1);
		rowCount = rowCount + 2;
		return button;
	}
	
	public void clear() {
		getChildren().clear();
			}
	
}
