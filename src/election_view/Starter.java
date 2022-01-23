package election_view;

import election_controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model_election.ElectionModel;


public class Starter extends Application {



		public static void main(String[] args) {
			launch(args);
		}
		public void start(Stage primaryStage) throws Exception {
			ElectionModel theModel=new ElectionModel();
			ClassView theView=new ClassView(primaryStage);
			Controller controller=new Controller(theModel, theView);
		}

	}


