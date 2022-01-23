package election_view;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class FormsStack extends StackPane {
	
	public void addForm(FormView form) {
		form.setVisible(false);
		getChildren().add(form);
	}
	
	public void addForms(FormView... forms) {
		for (FormView form: forms) {
			addForm(form);
		}
	}
	
	public void show(FormView form) {
		clear();
		form.setVisible(true);
	}
	
	public void clear() {
		for (Node other_form: getChildren()) {
			other_form.setVisible(false);
		}
	}
}
